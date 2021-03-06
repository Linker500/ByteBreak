package byteBreak.pc;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.*; //Game Utils
import byteBreak.service.*;
import byteBreak.Network;
import java.util.TreeMap;
import java.util.ArrayList;

public abstract class PC implements java.io.Serializable
{
   public String os;
   public String host;
   public String dns;
   
   public ArrayList<Login> login;
   public ArrayList<String> prompt;
   public Disk disk;   
   public Network internet;
   
   public TreeMap<Integer,Service> servers = new TreeMap<Integer,Service>(); //TODO: associate with config files or something? Even if it is super hacky allow someone to nuke a server by deleting the system file.
   
   public PC(Network newInternet)
   {
      internet = newInternet;
   }
   
   public void updateConfig()
   {
      //Update hostname
      host = disk.get("/sys/hostname/").body;
      
      //Update logins
      ArrayList<String[]> accounts = new ArrayList<String[]>();
      {
         String loginRaw = disk.get("/sys/logins/").body;
         //Takes loginRaw, and splits it into accounts (ends with ";") and then those accounts into usernames, passwords, and permission level respectively (ends with ",")
         //TODO: make this do stuff with invalid login file syntax?
         boolean loop = true;
         while(loop)
         {
            int index = loginRaw.indexOf(';');
            if(index == -1)
               loop = false;
            else
            {
               String newLogin = loginRaw.substring(0,index);  
               String[] account = new String[3];
               
               for(int i=0; i<3; i++) //TODO: hardcoded, how does this handle an invalid config?
               {
                  int index2 = newLogin.indexOf(',');
                  account[i] = newLogin.substring(0,index2);
                  newLogin = newLogin.substring(index2+1,newLogin.length());
               }
               accounts.add(account);  
            }
            loginRaw = loginRaw.substring(index+1,loginRaw.length());
         }
      }
      
      login = new ArrayList<Login>();

      for(int i=0; i<accounts.size(); i++)
      {
         String[] account = accounts.get(i);
         login.add(new Login(account[0],account[1],Integer.parseInt(account[2])));
      }      
      
      //Update netconfig (DNS and whatnot)
      dns = disk.get("/sys/netconfig").body;
      
      //Update system name (sysKernel) //TODO: perhaps have certain kernels lock/unlock features? Or maybe one upload a malicious kernel to anothers computer that is vulnerable?
      /*unimplemented*/
   }
   
   public int login(String logUser, String logPass)
   {
      int session = -1;
      for(int i=0; i<login.size(); i++)
      {
         if(logUser.equals(login.get(i).user) && logPass.equals(login.get(i).pass))
            session = i;
      }
      return session;
   }
   
      
   public Data serve(int port, Data request)
   {
      Data reply;
      if(servers.get(port) == null)
         reply = new File("packet","Port access denied");
      else
         reply = servers.get(port).serve(request);
      
      return reply;
   }
}


/*

public void dos(String user, String pass)
   {
      //System Info
      os = "MacroSoft Dos 3.2";     
      host = "dos";
      //ipv4 = new int[]{192,168,1,1};
      
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]# ");
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]$ ");
      
      //Initialize Disk
      disk = new Disk();
      disk.add("/","bin",new Directory("bin",1,0));
      disk.add("/","boot",new Directory("boot",0,0));
      disk.add("/","home",new Directory("home",1,0));
      disk.add("/","sys",new Directory("sys",0,0));
      
      disk.add("/bin/","dir",new Command("dir",0));
      disk.add("/bin/","cd",new Command("cd",1)); 
      disk.add("/bin/","show",new Command("show",2));
      disk.add("/bin/","new",new Command("new",3));
      disk.add("/bin/","del",new Command("del",4));
      disk.add("/bin/","move",new Command("move",5));
      disk.add("/bin/","mod",new Command("mod",6));
      disk.add("/bin/","copy",new Command("copy",7));
      disk.add("/bin/","remote",new Command("remote",8));
      disk.add("/bin/","network",new Command("network",9));
      disk.add("/bin/","netsv",new Command("netsv",10));
      disk.add("/bin/","netexp",new Command("netexp",11));
      
      //TODO: make these actually do things. Should be seperate class(es) that extend File
      disk.add("/sys/","hostname",new File("hostname"));
      disk.add("/sys/","logins",new File("logins"));
      disk.add("/sys/","netconfig",new File("netconfig"));
      disk.add("/sys/","sysKernel",new File("sysKernel"));
      
      //User accounts
      login = new ArrayList<Login>();
      login.add(new Login("admin","pass",0)); //Default root account
      login.add(new Login(user,pass,1)); //User account
      
      //Services
      servers = new TreeMap<Integer,Service>();
      servers.put(7,new S_Ping()); //Ping server
   }


public void nixDns(TreeMap<String,String> domains)
   {
      //System Info
      os = "nix";     
      host = "cloudflare-dns";
      //ipv4 = new int[]{192,168,1,1};
      
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]# ");
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]$ ");
      
      disk = new Disk();
      disk.add("/","bin",new Directory("bin",1,0));
      disk.add("/","boot",new Directory("boot",0,0));
      disk.add("/","home",new Directory("home",1,0));
      disk.add("/","sys",new Directory("sys",0,0));
      
      disk.add("/bin/","ls",new Command("ls",0));
      disk.add("/bin/","cd",new Command("cd",1)); 
      disk.add("/bin/","cat",new Command("cat",2));
      disk.add("/bin/","mk",new Command("mk",3));
      disk.add("/bin/","rm",new Command("rm",4));
      disk.add("/bin/","mv",new Command("mv",5));
      disk.add("/bin/","ed",new Command("ed",6));
      disk.add("/bin/","cp",new Command("cp",7));
      disk.add("/bin/","ssh",new Command("ssh",8));
      disk.add("/bin/","net",new Command("net",9));
      disk.add("/bin/","netsv",new Command("netsv",10));
      disk.add("/bin/","netexp",new Command("netexp",11));
            
      //TODO: make these actually do things. Should be seperate class(es) that extend File
      disk.add("/sys/","hostname",new File("hostname"));
      disk.add("/sys/","logins",new File("logins"));
      disk.add("/sys/","netconfig",new File("netconfig"));
      disk.add("/sys/","sysKernel",new File("sysKernel"));

      
      //User accounts
      login = new ArrayList<Login>();
      login.add(new Login("root","toor",0)); //Default root account
      
      //Services
      servers = new TreeMap<Integer,Service>();
      servers.put(7,new S_Ping()); //Ping server
      servers.put(53,new S_DNS(domains)); //DNS Service
      
   }
   
   
   */
import java.util.TreeMap;
import java.util.*;

public class PC implements java.io.Serializable
{
   String os;
   String host;
   ArrayList<Login> login;
   ArrayList<String> prompt;
   
   //TreeMap<String,Data> disk;
   Data disk;
   
   TreeMap<Integer,Server> servers; //TODO: associate with config files or something? Even if it is super hacky allow someone to nuke a server by deleting the system file.
   
   public PC(String type, String user, String pass, TreeMap<String,String> domains) //TODO this is a hack. AAAAAAAAAAAAAAH. Let dataGen customize PCs
   {
      if(type.equals("bytebox"))
         bytebox(user,pass);
      if(type.equals("dos"))
         dos(user,pass);
      if(type.equals("nixDns"))
         nixDns(domains);
   }
   
   public void bytebox(String user, String pass)
   {  
      //System Info
      os = "Byte-Box 0.9.2";     
      host = "bytebox";
      //ipv4 = new int[]{192,168,1,1};
      
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]# ");
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]$ ");
      
      //Initialize Disk
      disk = new Directory("root",1,0);
      //disk = new TreeMap<String,Data>();
      disk.getData().put("bin",new Directory("bin",1,0));
      disk.getData().put("boot",new Directory("boot",0,0));
      disk.getData().put("home",new Directory("home",1,0));
      disk.getData().put("sys",new Directory("sys",0,0));
      
      disk.getData().get("bin").getData().put("ls",new Command("ls",0)); //ls
      disk.getData().get("bin").getData().put("cd",new Command("cd",1)); //cd
      disk.getData().get("bin").getData().put("cat",new Command("cat",2)); //cat
      disk.getData().get("bin").getData().put("mk",new Command("mk",3)); //mk
      disk.getData().get("bin").getData().put("rm",new Command("rm",4)); //rm
      disk.getData().get("bin").getData().put("mv",new Command("mv",5)); //mv
      disk.getData().get("bin").getData().put("ed",new Command("ed",6)); //ed
      disk.getData().get("bin").getData().put("cp",new Command("cp",7)); //cp
      disk.getData().get("bin").getData().put("ssh",new Command("ssh",8)); //ssh
      disk.getData().get("bin").getData().put("net",new Command("net",9)); //net
      disk.getData().get("bin").getData().put("netsv",new Command("netsv",10)); //netsv
      disk.getData().get("bin").getData().put("netexp",new Command("netexp",11)); //netexp
      
      //TODO: make these actually do things. Should be seperate class(es) that extend File
      disk.getData().get("sys").getData().put("hostname",new File("hostname"));
      disk.getData().get("sys").getData().put("logins",new File("logins"));
      disk.getData().get("sys").getData().put("netconfig",new File("netconfig"));
      disk.getData().get("sys").getData().put("sysKernel",new File("sysKernel"));
      
      //User accounts
      login = new ArrayList<Login>();
      login.add(new Login("root","toor",0)); //Default root account
      login.add(new Login(user,pass,1)); //User account
      
      //Servers
      servers = new TreeMap<Integer,Server>();
      servers.put(7,new S_Ping()); //Ping server
   }
   
   public void dos(String user, String pass)
   {
      //System Info
      os = "MacroSoft Dos 3.2";     
      host = "dos";
      //ipv4 = new int[]{192,168,1,1};
      
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]# ");
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]$ ");
      
      //Initialize Disk
      disk = new Directory("root",1,0);
      //disk = new TreeMap<String,Data>();
      disk.getData().put("bin",new Directory("bin"));
      disk.getData().put("boot",new Directory("boot"));
      disk.getData().put("home",new Directory("home"));
      disk.getData().put("sys",new Directory("sys"));
      
      disk.getData().get("bin").getData().put("dir",new Command("dir",0)); //ls
      disk.getData().get("bin").getData().put("cd",new Command("cd",1)); //cd
      disk.getData().get("bin").getData().put("show",new Command("show",2)); //cat
      disk.getData().get("bin").getData().put("new",new Command("new",3)); //mk
      disk.getData().get("bin").getData().put("del",new Command("del",4)); //rm
      disk.getData().get("bin").getData().put("move",new Command("move",5)); //mv
      disk.getData().get("bin").getData().put("mod",new Command("mod",6)); //ed
      disk.getData().get("bin").getData().put("copy",new Command("copy",7)); //cp
      disk.getData().get("bin").getData().put("remote",new Command("remote",8)); //ssh
      disk.getData().get("bin").getData().put("net",new Command("network",9));
      disk.getData().get("bin").getData().put("netsv",new Command("netsv",10)); //netsv
      
      //TODO: make these actually do things. Should be seperate class(es) that extend File
      disk.getData().get("sys").getData().put("hostname",new File("hostname"));
      disk.getData().get("sys").getData().put("logins",new File("logins"));
      disk.getData().get("sys").getData().put("netconfig",new File("netconfig"));
      disk.getData().get("sys").getData().put("sysKernel",new File("sysKernel"));
      
      //User accounts
      login = new ArrayList<Login>();
      login.add(new Login("admin","pass",0)); //Default root account
      login.add(new Login(user,pass,1)); //User account
      
      //Servers
      servers = new TreeMap<Integer,Server>();
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
      
      //Initialize Disk
      disk = new Directory("root",1,0);
      disk.getData().put("bin",new Directory("bin",1,0));
      disk.getData().put("boot",new Directory("boot",0,0));
      disk.getData().put("home",new Directory("home",1,0));
      disk.getData().put("sys",new Directory("sys",0,0));
      
      disk.getData().get("bin").getData().put("ls",new Command("ls",0)); //ls
      disk.getData().get("bin").getData().put("cd",new Command("cd",1)); //cd
      disk.getData().get("bin").getData().put("cat",new Command("cat",2)); //cat
      disk.getData().get("bin").getData().put("mk",new Command("mk",3)); //mk
      disk.getData().get("bin").getData().put("rm",new Command("rm",4)); //rm
      disk.getData().get("bin").getData().put("mv",new Command("mv",5)); //mv
      disk.getData().get("bin").getData().put("ed",new Command("ed",6)); //ed
      disk.getData().get("bin").getData().put("cp",new Command("cp",7)); //cp
      disk.getData().get("bin").getData().put("ssh",new Command("ssh",8)); //ssh
      disk.getData().get("bin").getData().put("net",new Command("net",9)); //net
      disk.getData().get("bin").getData().put("netsv",new Command("netsv",10)); //netsv
      
      //TODO: make these actually do things. Should be seperate class(es) that extend File
      disk.getData().get("sys").getData().put("hostname",new File("hostname"));
      disk.getData().get("sys").getData().put("logins",new File("logins"));
      disk.getData().get("sys").getData().put("netconfig",new File("netconfig"));
      disk.getData().get("sys").getData().put("sysKernel",new File("sysKernel"));
      
      //User accounts
      login = new ArrayList<Login>();
      login.add(new Login("root","toor",0)); //Default root account
      
      //Servers
      servers = new TreeMap<Integer,Server>();
      servers.put(7,new S_Ping()); //Ping server
      servers.put(53,new S_DNS(domains)); //DNS Server
      
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
      
   public TreeMap<String,Data> serve(int port, TreeMap<String,Data> request)
   {
      TreeMap<String,Data> reply;
      if(servers.get(port) == null)
      {
         reply = new TreeMap<String,Data>();
         reply.put("packet",new File("packet","Port access denied"));
      }
      else
         reply = servers.get(port).serve(request);
      
      return reply;
   }
}
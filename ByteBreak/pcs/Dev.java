package ByteBreak.pcs;

import java.util.TreeMap;

import ByteBreak.Disk;
import ByteBreak.Data;
import ByteBreak.File;
import ByteBreak.Directory;
import ByteBreak.Command;

import ByteBreak.servers.*;

public class Dev extends PC
{
   public Dev()
   {
      generate();
   }
   
   public Dev(String user, String pass)
   {
      generate();
      disk.get("/sys/logins/").setBody("root,toor,0,;"+user+","+pass+",1,;");
   }
   
   private void generate()
   {
      //System Info
      os = "ByteBreak Developer System v1.3.3.7";
      //ipv4 = new int[]{192,168,1,1};
      
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]# ");
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]$ ");
      
      //Initialize Disk  
      //TODO: should files have their names in them?? NO!
      disk = new Disk();
      
      disk.add("/","bin",new Directory("bin",1,0));
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
      
      disk.add("/","home",new Directory("home",1,1));
         disk.add("/home/","Downloads",new Directory("Downloads",1,1));
      
      disk.add("/","sys",new Directory("sys",0,0));
         disk.add("/sys/","hostname",new File("hostname","dev"));
         disk.add("/sys/","logins",new File("logins","root,toor,0,;user,pass,1,;"));
         disk.add("/sys/","netconfig",new File("netconfig"));
         disk.add("/sys/","sysKernel",new File("sysKernel"));
      
      disk.add("/","srv",new Directory("srv",0,0));
         disk.add("/srv/","http",new Directory("http",0,0));
      
      //Servers
      servers = new TreeMap<Integer,Server>();
      servers.put(7,new Ping()); //Ping server
      servers.put(80,new HTTP(disk.get("/sys/"))); //HTTP server
      
      updateConfig();

   }
}
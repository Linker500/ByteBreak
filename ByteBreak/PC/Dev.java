package ByteBreak.PC;

import java.util.TreeMap;

import ByteBreak.Data.File.Executable.*;
import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.File;
import ByteBreak.Data.Directory;

import ByteBreak.Service.*;

public class Dev extends PC
{
   public Dev()
   {
      generate();
   }
   
   public Dev(String user, String pass)
   {
      generate();
      disk.get("/sys/logins/").body = "root,toor,0,;"+user+","+pass+",1,;";
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
         disk.add("/bin/","ls",new List("ls",1,0));
         disk.add("/bin/","cd",new ChangeDirectory("cd",1,0)); 
         disk.add("/bin/","cat",new Concat("cat",1,0));
         disk.add("/bin/","mk",new Make("mk",1,0));
         disk.add("/bin/","rm",new Remove("rm",1,0));
         disk.add("/bin/","mv",new Move("mv",1,0));
         disk.add("/bin/","ed",new Edit("ed",1,0));
         disk.add("/bin/","cp",new Copy("cp",1,0));
         disk.add("/bin/","ssh",new Remote("ssh",1,0));
         //disk.add("/bin/","net",new Net("net",1,0));
         disk.add("/bin/","netexp",new NetExplorer("netexp",1,0));
      
      disk.add("/","home",new Directory("home",1,1));
         disk.add("/home/","Downloads",new Directory("Downloads",1,1));
      
      disk.add("/","sys",new Directory("sys",0,0));
         disk.add("/sys/","hostname",new File("hostname","dev"));
         disk.add("/sys/","logins",new File("logins","root,toor,0,;user,pass,1,;"));
         disk.add("/sys/","netconfig",new File("netconfig"));
         disk.add("/sys/","sysKernel",new File("sysKernel"));
      
      disk.add("/","srv",new Directory("srv",0,0));
         disk.add("/srv/","http",new Directory("http",0,0));
      
      //Services
      servers = new TreeMap<Integer,Service>();
      servers.put(7,new Ping()); //Ping server
      servers.put(80,new HTTP(disk.get("/sys/"))); //HTTP server
      
      updateConfig();

   }
}
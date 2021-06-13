package byteBreak.pc;

import byteBreak.data.file.executable.*;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.data.Directory;
import byteBreak.Network;
import byteBreak.service.*;

import java.util.TreeMap;

public class Dev extends Onyx
{
   public Dev(Network newInternet)
   {
      super(newInternet);
      generate();
   }
   
   private void generate()
   {
      //System Info
      //ipv4 = new int[]{192,168,1,1};
      
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]# ");
      //prompt.add("["+pc.login.get(sess.get(0)).user+"@"+pc.host+"]$ ");
      
      //Initialize Disk  
      //TODO: should files have their names in them?? NO!
      
      disk.add("/bin/","net",new Net("net",1,0));
      disk.add("/bin/","netexp",new NetExplorer("netexp",1,0));
   
      disk.add("/","home",new Directory("home",1,1));
         disk.add("/home/","Downloads",new Directory("Downloads",1,1));
      
      disk.get("/sys/logins/").body = "root,toor,0,;user,pass,1,;";
      disk.get("/sys/hostname/").body = "Dev";
      disk.add("/sys/","netconfig",new File("netconfig"));
      disk.add("/sys/","sysKernel",new File("sysKernel"));
      
      disk.add("/","srv",new Directory("srv",0,0));
         disk.add("/srv/","http",new Directory("http",0,0));
      
      //Services
      servers.put(80,new HTTP(disk.get("/sys/"))); //HTTP server
      
      updateConfig();

   }
}
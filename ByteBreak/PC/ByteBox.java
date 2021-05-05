package ByteBreak.PC;

import java.util.TreeMap;

import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.Text;
import ByteBreak.Data.Directory;
import ByteBreak.Command;

import ByteBreak.Service.*;

public class ByteBox extends PC
{
   public ByteBox()
   {
      generate();
   }
   
   public ByteBox(String user, String pass)
   {
      generate();
      disk.get("/sys/logins/").setBody("root,toor,0,;"+user+","+pass+",1,;");
   }
   
   private void generate()
   {
      os = "ByteBox OS V1.2.7";
      
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
         disk.add("/sys/","hostname",new Text("hostname","bytebox"));
         disk.add("/sys/","logins",new Text("logins","root,toor,0,;user,pass,1,;"));
      
      disk.add("/","srv",new Directory("srv",0,0));
      
      servers = new TreeMap<Integer,Service>();
      servers.put(7,new Ping());
      
      updateConfig();
   }
}
package byteBreak.pc;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.data.Directory;
import byteBreak.data.file.executable.*;
import byteBreak.Network;
import byteBreak.service.*;

import java.util.TreeMap;

//DEFAULT UNIX BOX
public class Onyx extends PC
{   
   public Onyx(Network newInternet)
   {
      super(newInternet);
      generate();
   }
         
   private void generate()
   {      
      disk = new Disk();
      disk.add("/","bin",new Directory("bin",1,0));
         disk.add("/bin/","bash", new Bash("bash",1,0));
         disk.add("/bin/","ls",new List("ls",1,0));
         disk.add("/bin/","cd",new ChangeDirectory("cd",1,0));
         disk.add("/bin/","clear",new Clear("clear",1,0));
         disk.add("/bin/","cat",new Concat("cat",1,0));
         disk.add("/bin/","mk",new Make("mk",1,0));
         disk.add("/bin/","rm",new Remove("rm",1,0));
         disk.add("/bin/","mv",new Move("mv",1,0));
         disk.add("/bin/","ed",new Edit("ed",1,0));
         disk.add("/bin/","cp",new Copy("cp",1,0));
         disk.add("/bin/","ssh",new Remote("ssh",1,0));
      
      disk.add("/","sys",new Directory("sys",0,0));
         disk.add("/sys/","hostname",new File("hostname","Onyx"));
         disk.add("/sys/","logins",new File("logins","root,toor,0,;"));
   
      servers.put(7,new Ping());
      
      updateConfig();
   }

}
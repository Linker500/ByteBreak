package byteBreak.pc;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.data.Directory;
import byteBreak.data.file.executable.*;
import byteBreak.Network;
import byteBreak.service.*;

import java.util.TreeMap;

public class ByteBox extends Onyx
{
   public ByteBox(Network newInternet)
   {
      super(newInternet);
      generate();
   }
   
   public ByteBox(Network newInternet, String user, String pass)
   {
      super(newInternet);
      generate();
      disk.get("/sys/logins/").body = "root,toor,0,;"+user+","+pass+",1,;";
   }
   
   private void generate()
   {
      os = "ByteBox OS V1.2.7";
      
      disk.add("/bin/","netexp",new NetExplorer("netexp",1,0));
         
      disk.add("/","home",new Directory("home",1,1));
            disk.add("/home/","Downloads",new Directory("Downloads",1,1));
      
      servers = new TreeMap<Integer,Service>(); //TODO: have remote management tools w/ master password or something
      servers.put(7,new Ping());
      
      updateConfig();
   }
}
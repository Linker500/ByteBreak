package byteBreak.pc;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.data.Directory;
import byteBreak.data.file.executable.*;
import byteBreak.Network;
import byteBreak.service.*;

import java.util.TreeMap;

public abstract class WebHost extends Onyx
{   
   public WebHost(Network newInternet)
   {
      super(newInternet);
      generate();
   }
         
   private void generate() //TODO: make http config file work
   {  
      disk.add("/","srv",new Directory("srv",0,0));
         disk.add("/srv/","http",new Directory("srv",0,0));
         
      disk.add("/sys/","http.conf",new File("http.conf",0,0));
      
      servers.put(80, new HTTP(disk.get("/srv/http/")));
      
      updateConfig();
   }
}
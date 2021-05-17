package byteBreak.pc;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.data.Directory;
import byteBreak.data.file.executable.*;
import byteBreak.Network;
import byteBreak.service.*;

import java.util.TreeMap;

public class Example extends WebHost
{   
   public Example(Network newInternet)
   {
      super(newInternet);
      generate();
   }
         
   private void generate()
   {      
      disk.add("/srv/http/","index.txt",new File("index.txt","Example Domain\nThis domain is for use in illustrative examples in documents.\nYou may use this domain in literature without prior coordination or asking for permission."));  
      servers.put(80,new HTTP(disk.get("/srv/http/")));
      
      updateConfig();
   }
}
package byteBreak.pc;

import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.data.Directory;
import byteBreak.data.file.executable.*;
import byteBreak.Network;
import byteBreak.service.*;

import java.util.TreeMap;

public class DnsHost extends Onyx
{
   TreeMap<String,String> DnsData;
   
   public DnsHost(Network newInternet)
   {
      super(newInternet);
      generate();
   }
   
   public DnsHost(Network newInternet, TreeMap<String,String> newDnsData)
   {
      super(newInternet);
      DnsData = newDnsData;
      generate();
   }
      
   private void generate()
   {  
      disk.add("/","srv",new Directory("srv",0,0));
         disk.add("/srv/","dns.conf",new File("dns.conf")); //TODO: implement DNS as a file that can be edited and configured
   
      servers.put(52, new DNS(DnsData));
      
      updateConfig();
   }
}
package ByteBreak.PC;

import java.util.TreeMap;

import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.File;
import ByteBreak.Data.Directory;
import ByteBreak.Data.File.Executable.*;
import ByteBreak.Network;

import ByteBreak.Service.*;

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
package ByteBreak.pcs;

import java.util.TreeMap;

import ByteBreak.Disk;
import ByteBreak.Data;
import ByteBreak.File;
import ByteBreak.Directory;
import ByteBreak.Command;

import ByteBreak.servers.*;

public class DnsHost extends PC
{
   TreeMap<String,String> DnsData;
   
   public DnsHost()
   {
      generate();
   }
   
   public DnsHost(TreeMap<String,String> newDnsData)
   {
      DnsData = newDnsData;
      generate();
   }
      
   private void generate()
   {
      os = "Nix";
      
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
      
      disk.add("/","sys",new Directory("sys",0,0));
         disk.add("/sys/","hostname",new File("hostname","bytebox"));
         disk.add("/sys/","logins",new File("logins","root,toor,0,;"));
      
      disk.add("/","srv",new Directory("srv",0,0));
         disk.add("/srv/","dns.conf",new File("dns.conf")); //TODO: implement DNS as a file that can be edited and configured
   
      servers = new TreeMap<Integer,Server>();
      servers.put(7,new Ping());
      servers.put(52, new DNS(DnsData));
      
      updateConfig();
   }
}
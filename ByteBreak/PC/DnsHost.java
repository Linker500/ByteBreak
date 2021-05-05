package ByteBreak.PC;

import java.util.TreeMap;

import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.File;
import ByteBreak.Data.Directory;
import ByteBreak.Data.File.Executable.*;

import ByteBreak.Service.*;

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
         disk.add("/bin/","ls",new List("ls",1,0));
         disk.add("/bin/","cd",new ChangeDirectory("cd",1,0)); 
         disk.add("/bin/","cat",new Concat("cat",1,0));
         disk.add("/bin/","mk",new Make("mk",1,0));
         disk.add("/bin/","rm",new Remove("rm",1,0));
         disk.add("/bin/","mv",new Move("mv",1,0));
         disk.add("/bin/","ed",new Edit("ed",1,0));
         disk.add("/bin/","cp",new Copy("cp",1,0));
         disk.add("/bin/","ssh",new Remote("ssh",1,0));
         disk.add("/bin/","net",new Net("net",1,0));
         disk.add("/bin/","netexp",new NetExplorer("netexp",1,0));
      
      disk.add("/","sys",new Directory("sys",0,0));
         disk.add("/sys/","hostname",new File("hostname","bytebox"));
         disk.add("/sys/","logins",new File("logins","root,toor,0,;"));
      
      disk.add("/","srv",new Directory("srv",0,0));
         disk.add("/srv/","dns.conf",new File("dns.conf")); //TODO: implement DNS as a file that can be edited and configured
   
      servers = new TreeMap<Integer,Service>();
      servers.put(7,new Ping());
      servers.put(52, new DNS(DnsData));
      
      updateConfig();
   }
}
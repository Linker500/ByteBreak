package ByteBreak.Data.File.Executable;

import ByteBreak.PC.PC;
import ByteBreak.Network;
import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.File;
import ByteBreak.PC.PC;

import java.util.ArrayList;

public class Net extends Executable
{
   public Net()
   {
      super();
   }
   
   public Net(String newName)
   {
      super(newName);
   }
   
   public Net(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, Network inter, int sess) //TODO: save returned file
   {
      Disk disk = pc.disk;
      
      Data workDir;
      workDir = disk.get(dir);

      int userPerm = pc.login.get(sess).perm;

      if(args.size() < 3)
      {
         if(args.size() == 0)
            return "net: Missing target argument\n";
         if(args.size() == 1)
            return "net: Missing port argument\n";
         if(args.size() == 2)
            return "net: Missing request argument\n";
      }
      
      if(workDir.permWrite < userPerm)
         return "net: Write access to " + workDir.name + " denied\n";

      Data request = new File("packet",args.get(2));
      
      String address = args.get(0);
      
      PC target = inter.net.get(args.get(0));
      if(target == null)
         return "net: host " + address + " not found\n";
      
      int port;
      try{port = Integer.parseInt(args.get(1));}
      catch(Exception e){return "net: " + args.get(1) + " not a valid port\n";}
      
      Data reply = inter.net.get(address).serve(port,request);
      reply.permRead = userPerm;
      reply.permWrite = userPerm;
      workDir.data.put(reply.name,reply);
      return reply.body+"\n";
   }
}
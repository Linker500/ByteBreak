package ByteBreak.Data.File.Executable;

import ByteBreak.PC.PC;
import ByteBreak.Network;
import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Shell;

import java.util.ArrayList;

public class Remote extends Executable
{
   public Remote()
   {
      super();
   }
   
   public Remote(String newName)
   {
      super(newName);
   }
   
   public Remote(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public String run(ArrayList<String> dir, PC pc, ArrayList<String> args, int sess)
   {
      if(args.size() == 0)
         return "ssh: Missing target system argument";
      
      Shell shell = new Shell(pc.internet);
      shell.start(args.get(0));
      return "Connection closed\n";
   }
}
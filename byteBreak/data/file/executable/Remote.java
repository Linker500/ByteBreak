package byteBreak.data.file.executable;

import byteBreak.Shell;
import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;

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
   
   public void run(Shell shell, ArrayList<String> args)
   {
      if(args.size() == 0)
         shell.output("ssh: Missing target system argument"); //TODO: hard coded name bad
      
      else
      {
         Shell newShell = new Shell(shell.internet, args.get(0), false);
         newShell.start();
         shell.output("Connection closed\n");
      }
   }
}
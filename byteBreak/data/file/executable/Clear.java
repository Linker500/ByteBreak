package byteBreak.data.file.executable;

import byteBreak.Shell;
import byteBreak.data.file.File;
import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Util;

import java.util.ArrayList;

public class Clear extends Executable
{
   public Clear()
   {
      super();
   }
   
   public Clear(String newName)
   {
      super(newName);
   }
   
   public Clear(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public Clear(String newName, String newBody)
   {
      super(newName);
      body = newBody;
   }
   
   public void run(Shell shell, ArrayList<String> args)
   {
      shell.output(shell.clear);
   }
}
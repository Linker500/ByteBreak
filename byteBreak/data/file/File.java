package byteBreak.data.file;
import byteBreak.pc.PC;
import byteBreak.data.Data;
import byteBreak.Network;
import java.util.TreeMap;
import java.util.ArrayList;
import byteBreak.Shell;

public class File extends Data
{
   public File()
   {
      super();
   }
   
   public File(String newName)
   {
      super(newName);
   }
   
   public File(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public File(String newName, String newBody)
   {
      super(newName);
      body = newBody;
   }
   
   public File(String newName, String newBody, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
      body = newBody;
   }
   
   public void run(Shell shell, ArrayList<String> args)
   {
      shell.output(": Is a file\n");
   }
}
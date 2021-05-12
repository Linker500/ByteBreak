package ByteBreak.Data.File;
import ByteBreak.PC.PC;
import ByteBreak.Data.Data;
import ByteBreak.Network;
import java.util.TreeMap;
import java.util.ArrayList;

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
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, int sess)
   {
      return ": Is a file\n";
   }
}
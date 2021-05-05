package ByteBreak;
import ByteBreak.*;
import ByteBreak.Data.File.Text;
import ByteBreak.PC.PC;
import java.util.*;
public class Command extends Text
{
   int command;
   
   public Command()
   {
      super.name = "ls";
      super.permRead = 0;
      super.permWrite = 0;
      command = 0;
   }
   
   public Command(String newName, int newCommand)
   {
      super.name = newName;
      super.permRead = 0;
      super.permWrite = 0;
      command = newCommand;
   }
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, Network inter, int sess)
   {
      return script.run(dir, pc, command, args, inter, sess);
   }
}
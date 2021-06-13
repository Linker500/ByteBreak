package byteBreak.data.file.executable;

import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;

import java.util.ArrayList;

public class Remove extends Executable
{
   public Remove()
   {
      super();
   }
   
   public Remove(String newName)
   {
      super(newName);
   }
   
   public Remove(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public String run(String dir, PC pc,ArrayList<String> args, int sess)
   {
      Disk disk = pc.disk;
      
      Data workDir;
      workDir = disk.get(dir);

      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;

      if(args.size() == 0)
         return "rm: Missing target parameter\n";
      
      String output = "";
      for(int i=0; i<args.size(); i++)
      {
         if(workDir.data.get(args.get(i)).permWrite < userPerm)
            output += "rm: Write access to " + args.get(i) + " denied\n";
         else
            workDir.data.remove(args.get(i));
      }
      
      return output;
   }
}
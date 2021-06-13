package byteBreak.data.file.executable;

import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;

import java.util.ArrayList;

public class Move extends Executable
{
   public Move()
   {
      super();
   }
   
   public Move(String newName)
   {
      super(newName);
   }
   
   public Move(String newName, int newPermRead, int newPermWrite)
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
         return "cat: Missing target argument\n";
      
      if(workDir.data.get(args.get(0)).permRead < userPerm)
         return "mv: Read access to " + args.get(0) + " denied\n";
      
      if(workDir.data.get(args.get(0)).permWrite < userPerm)
         return "mv: Write access to " + args.get(0) + " denied\n";
      
      if(workDir.permWrite < userPerm)
         return "mv: Write access to " + workDir.name + " denied\n";
      
      if(args.size() <2)
      {
         if(args.size() == 1)
            return "mv: Missing destination file argument.\n";
         if(args.size() == 0)
            return "mv: Missing file argument.\n";
      }
      workDir.data.put(args.get(1),workDir.data.get(args.get(0)));
      workDir.data.remove(args.get(0));
      return "";
   }
}
package byteBreak.data.file.executable;

import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;

import java.util.ArrayList;

public class Copy extends Executable
{
   public Copy()
   {
      super();
   }
   
   public Copy(String newName)
   {
      super(newName);
   }
   
   public Copy(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, int sess)
   {
      Disk disk = pc.disk;
      
      Data workDir;
      workDir = disk.get(dir);

      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;

      if(workDir.data.get(args.get(0)).permRead < userPerm)
         return "mv: Read access to " + args.get(0) + " denied\n";
      
      if(workDir.permWrite < userPerm)
         return "mv: Write access to " + workDir.name + " denied\n";
      
      if(args.size() <2)
      {
         if(args.size() == 1)
            return "cp: Missing target file argument\n";
         if(args.size() == 0)
            return "cp: Missing destination file argument\n";
      }
      
      workDir.data.put(args.get(1),workDir.data.get(args.get(0)));
      return "";
   }
}
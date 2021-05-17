package byteBreak.data.file.executable;

import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.Directory;

import java.util.ArrayList;

public class ChangeDirectory extends Executable
{
   public ChangeDirectory()
   {
      super();
   }
   
   public ChangeDirectory(String newName)
   {
      super(newName);
   }
   
   public ChangeDirectory(String newName, int newPermRead, int newPermWrite)
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
      
      if(args.size() == 0)
      {
         if(dir.size() > 0)
            dir.remove(dir.size()-1);
         return "";
      }
      else if(args.size() > 0)
      {
         if(workDir.data.get(args.get(0)) == null)
            return "cd: No such directory\n";
         if(!(workDir.data.get(args.get(0)) instanceof Directory))
            return "cd: " + args.get(0) + ": Not a directory\n";
         if(workDir.data.get(args.get(0)).permRead < userPerm)
            return "cd: Read access to " + args.get(0) + " denied\n";
         
         dir.add(args.get(0));
         return "";
      }
      return "cd: Unknown Error\n";
   }
}
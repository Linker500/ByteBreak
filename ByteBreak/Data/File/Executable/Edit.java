package ByteBreak.Data.File.Executable;

import ByteBreak.PC.PC;
import ByteBreak.Network;
import ByteBreak.Disk;
import ByteBreak.Data.Data;

import java.util.ArrayList;

public class Edit extends Executable
{
   public Edit()
   {
      super();
   }
   
   public Edit(String newName)
   {
      super(newName);
   }
   
   public Edit(String newName, int newPermRead, int newPermWrite)
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
         return "ed: Missing target file argument.";
     
      if(workDir.data.get(args.get(0)).permRead < userPerm)
         return "ed: Write access to " + args.get(0) + " denied";
      
      if(args.size() == 1)
         workDir.data.get(args.get(0)).body = "";
      else
      {
         String edit = args.get(1);
         for(int j=2; j<args.size(); j++)
            edit+=(" "+args.get(j));
         workDir.data.get(args.get(0)).body = edit;
      }          
      return "";
   }
}
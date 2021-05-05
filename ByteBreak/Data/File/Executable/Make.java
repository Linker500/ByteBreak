package ByteBreak.Data.File.Executable;

import ByteBreak.PC.PC;
import ByteBreak.Network;
import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.File;

import java.util.ArrayList;

public class Make extends Executable
{
   public Make()
   {
      super();
   }
   
   public Make(String newName)
   {
      super(newName);
   }
   
   public Make(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, Network inter, int sess)
   {
      Disk disk = pc.disk;
      
      Data workDir;
      workDir = disk.get(dir);

      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;

      if(args.size() == 0)
         return "cat: Missing target argument\n";
      
      if(permWrite < userPerm)
         return "mk: Write access to " + workDir.name + " denied\n";
      
      if(args.size() == 0)
         workDir.data.put("new_file",new File());
      else if(args.size() == 1)
         workDir.data.put(args.get(0),new File(args.get(0)));
      else if(args.size() == 2)
         workDir.data.put(args.get(0),new File(args.get(0),args.get(1)));
      else if(args.size() > 2)
      {
         String edit = args.get(1);
         for(int j=2; j<args.size(); j++)
            edit+=(" "+args.get(j));
         workDir.data.get(args.get(0)).body = edit;
      }
      return "";
   }
}
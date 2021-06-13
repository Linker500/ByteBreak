package byteBreak.data.file.executable;

import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;

import java.util.ArrayList;

public class Concat extends Executable
{
   public Concat()
   {
      super();
   }
   
   public Concat(String newName)
   {
      super(newName);
   }
   
   public Concat(String newName, int newPermRead, int newPermWrite)
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
         return "cat: Missing target argument\n";
      
      String output = "";
      for(int i=0; i<args.size(); i++)
      {
         if(workDir.data.get(args.get(i)).permRead < userPerm)
            output += "cat: Read access to " + args.get(i) + " denied\n";
         else
            output += workDir.data.get(args.get(i)).body+"\n";
      }
      return output;
   }
}
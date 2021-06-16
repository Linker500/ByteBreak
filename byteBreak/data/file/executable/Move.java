package byteBreak.data.file.executable;

import byteBreak.Shell;
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
   
   public void run(Shell shell, ArrayList<String> args)
   {
      PC pc = shell.pc;
      Disk disk = shell.pc.disk;
      ArrayList<String> dir = Shell.dir;
      int sess = Shell.sess;
      
      Data workDir;
      workDir = disk.get(dir);

      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;

      if(args.size() == 0)
         shell.output("cat: Missing target argument\n");
      
      if(workDir.data.get(args.get(0)).permRead < userPerm)
         shell.output("mv: Read access to " + args.get(0) + " denied\n");
      
      else if(workDir.data.get(args.get(0)).permWrite < userPerm)
         shell.output("mv: Write access to " + args.get(0) + " denied\n");
      
      else if(workDir.permWrite < userPerm)
         shell.output("mv: Write access to " + workDir.name + " denied\n");
      
      else if(args.size() <2)
      {
         if(args.size() == 1)
            shell.output("mv: Missing destination file argument.\n");
         else if(args.size() == 0)
            shell.output("mv: Missing file argument.\n");
      }
      else
      {
         workDir.data.put(args.get(1),workDir.data.get(args.get(0)));
         workDir.data.remove(args.get(0));
      }
   }
}
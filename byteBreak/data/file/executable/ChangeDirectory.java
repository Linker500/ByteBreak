package byteBreak.data.file.executable;

import byteBreak.Shell;
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
      {
         if(dir.size() > 0)
            dir.remove(dir.size()-1);
      }
      else if(args.size() > 0)
      {
         if(workDir.data.get(args.get(0)) == null)
            shell.output("cd: No such directory\n");
         else if(!(workDir.data.get(args.get(0)) instanceof Directory))
            shell.output("cd: " + args.get(0) + ": Not a directory\n");
         else if(workDir.data.get(args.get(0)).permRead < userPerm)
            shell.output("cd: Read access to " + args.get(0) + " denied\n");
         else
            dir.add(args.get(0));
      }
   }
}
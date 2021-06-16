package byteBreak.data.file.executable;

import byteBreak.Shell;
import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;

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
         shell.output("ed: Missing target file argument.");
     
      else if(workDir.data.get(args.get(0)).permRead < userPerm)
         shell.output("ed: Write access to " + args.get(0) + " denied");
      
      else if(args.size() == 1)
         workDir.data.get(args.get(0)).body = "";
      else
      {
         String edit = args.get(1);
         for(int j=2; j<args.size(); j++)
            edit+=(" "+args.get(j));
         workDir.data.get(args.get(0)).body = edit;
      }          
   }
}
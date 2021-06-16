package byteBreak.data.file.executable;

import byteBreak.Shell;
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
      else
      {
         String output = "";
         for(int i=0; i<args.size(); i++)
         {
            if(workDir.data.get(args.get(i)).permRead < userPerm)
               output += "cat: Read access to " + args.get(i) + " denied\n";
            else
               output += workDir.data.get(args.get(i)).body+"\n";
         }
         shell.output(output);
      }
   }
}
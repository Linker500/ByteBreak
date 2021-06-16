package byteBreak.data.file.executable;

import byteBreak.Shell;
import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;

import java.util.ArrayList;

public class List extends Executable
{ 
   public List()
   {
      super();
   }
   
   public List(String newName)
   {
      super(newName);
   }
   
   public List(String newName, int newPermRead, int newPermWrite)
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
      workDir = disk.get(Shell.dir);
      
      int userPerm = pc.login.get(Shell.sess).perm;
      int permRead =  pc.disk.get(Shell.dir).permRead;
      int permWrite = pc.disk.get(Shell.dir).permWrite;
      
      String output = "";
      if(workDir.data.size() == 0)
         shell.output(output);
      
      else
      {
         for (String i : workDir.data.keySet())
            output += i + " ";
      
         shell.output(output+"\n");
      }
   }
}
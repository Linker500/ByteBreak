package byteBreak.data.file.executable;

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
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, int sess)
   {
      Disk disk = pc.disk;
      
      Data workDir;
      workDir = disk.get(dir);
      
      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;
      
      String output = "";
      if(workDir.data.size() == 0)
         return output;
      
      for (String i : workDir.data.keySet())
         output += i + " ";
      
      return output+"\n";
   }
}
package byteBreak.data.file.executable;

import byteBreak.data.file.File;
import byteBreak.pc.PC;
import byteBreak.Network;

import java.util.TreeMap;
import java.util.ArrayList;

public abstract class Executable extends File
{
   public Executable()
   {
      super();
   }
   
   public Executable(String newName)
   {
      super(newName);
   }
   
   public Executable(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public Executable(String newName, String newBody)
   {
      super(newName);
      body = newBody;
   }
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, int sess)
   {
      return "";
   }
}
package byteBreak.data;
import byteBreak.pc.PC;
import java.util.TreeMap;
import java.util.ArrayList;
import byteBreak.Network;
public class Directory extends Data
{
   //TODO be able to access folders by their string names instead of index!!!
   
   public Directory()
   {
      super(); //Is this important??
      data = new TreeMap<String,Data>();  
   }
   
   public Directory(String newName)
   {
      super(newName);
      data = new TreeMap<String,Data>();
   }
   
   public Directory(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
      data = new TreeMap<String,Data>();
   }
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, int sess)
   {
      return ": Is a directory\n";
   }
}
//TODO find minimal importing needed.
package byteBreak.data;
import byteBreak.Network;
import byteBreak.pc.PC;
import java.util.TreeMap;
import java.util.ArrayList;
public abstract class Data implements java.io.Serializable
{
   public String name;
   public String body;
   
   public int permRead;
   public int permWrite;
   
   public TreeMap<String,Data> data;
   
   public Data()
   {
      name = "file";
      body = "";
      permRead = 0;
      permWrite = 0;
   }
   
   public Data(String newName)
   {
      name = newName;
      body = "";
      permRead = 0;
      permWrite = 0;
   }
   
   public Data(String newName, int newPermRead, int newPermWrite)
   {
      name = newName;
      body = "";
      permRead = newPermRead;
      permWrite = newPermWrite;
   }
   
   public String run(String dir, PC pc, ArrayList<String> args, int sess)
   {
      return "\n";
   }
}
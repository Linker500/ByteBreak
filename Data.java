//TODO find minimal importing needed.
import java.util.TreeMap;
import java.util.*;
public interface Data extends java.io.Serializable
{
   public int identify();
   public String run(ArrayList<String> dir, PC pc, ArrayList<String> args, Network inter, int sess); //TODO: Clean this up with "dir" array, when I actually clean up the "main" class
   public String getName();
   public String getBody();
   public void setName(String newName);
   public void setBody(String newBody);
   public TreeMap<String,Data> getData();
   public int getPermRead();
   public int getPermWrite();
}
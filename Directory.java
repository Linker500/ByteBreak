import java.util.TreeMap;
import java.util.*;
public class Directory implements Data, java.io.Serializable
{
   //TODO be able to access folders by their string names instead of index!!!
   private String name;
   private int permRead;
   private int permWrite;
   TreeMap<String,Data> data;
   
   public Directory()
   {
      name = "new_folder";
      permRead = 0;
      permWrite = 0;
      data = new TreeMap<String,Data>();
      
   }
   
   public Directory(String newName)
   {
      name = newName;
      permRead = 0;
      permWrite = 0;
      data = new TreeMap<String,Data>();
      
   }
   
   public Directory(int newPermRead, int newPermWrite)
   {
      name = "new_folder";
      permRead = newPermRead;
      permWrite = newPermWrite;
      data = new TreeMap<String,Data>();
   }
   
   public Directory(String newName, int newPermRead, int newPermWrite)
   {
      name = newName;
      permRead = newPermRead;
      permWrite = newPermWrite;
      data = new TreeMap<String,Data>();
   }
   
   public int identify(){return 0;}
   public String run(ArrayList<String> dir, PC pc, ArrayList<String> args, Network inter, int sess){return name+": is a directory";}
   public String getName(){return name;}
   public String getBody(){return name+": is a directory";}
   public void setName(String newName){name = newName;}
   public void setBody(String newBody){}
   public TreeMap<String,Data> getData(){return data;}
   public int getPermRead(){return permRead;}   
   public int getPermWrite(){return permWrite;}
}
package ByteBreak;
import ByteBreak.pcs.PC;
import java.util.TreeMap;
import java.util.*;
public class File implements Data
{
   String name;
   int permRead;
   int permWrite;
   String body;
   
   public File()
   {
      name = "new_file";
      body = "";
      permWrite = 0;
      permRead = 0;
   }
   
   public File(String newName)
   {
      name = newName;
      permRead = 0;
      permWrite = 0;
   }
   
   public File(String newName, int newPermRead, int newPermWrite)
   {
      name = newName;
      permRead = newPermRead;
      permWrite = newPermWrite;
   }
   
   public File(String newName, String newBody)
   {
      name = newName;
      body = newBody;
      permWrite = 0;
      permRead = 0;
   }
   
   public File(String newName, String newBody, int newPermRead, int newPermWrite)
   {
      name = newName;
      body = newBody;
      permRead = newPermRead;
      permWrite = newPermWrite;
   }
   
   public int identify(){return 1;}
   public String run(ArrayList<String> dir, PC pc, ArrayList<String> args, Network inter, int sess){return name+": is not an executable file\n";}
   public String getName(){return name;}
   public String getBody(){return body;}
   public void setName(String newName){name = newName;}
   public void setBody(String newBody){body = newBody;}

   public TreeMap<String,Data> getData(){return null;}
   public int getPermRead(){return permRead;}   
   public int getPermWrite(){return permWrite;}
}
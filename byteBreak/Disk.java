package byteBreak;
import java.util.ArrayList;
import byteBreak.data.Directory;
import byteBreak.data.Data;
public class Disk implements java.io.Serializable
{
   Data disk;
   
//    public static void main(String[] args)
//    {
//       /*
//       String[] test = parsePath("/home/stuart/Desktop/pr0n/");
//       
//       for(int i=0; i<test.length; i++)
//          System.out.print("o "+test[i]+" C");
//       */
//       //PC test = new PC("bytebox","user","pass",null);
//       //Disk test = new Disk();
//       //test.add("/","test",new File("test"));
//       
//       String[] test = parsePath("/test/cap/");
//       
//       for(int i=0; i<test.length; i++)
//          System.out.println(test[i]);
//    }
   
   public Disk()
   {
      disk = new Directory("root",1,0);
   }
   
   public Disk(Data newDisk)
   {
      disk = newDisk;
   }
   
   //Add files
   public void add(String pathString, String name, Data newData)
   { //TODO: safety check if file!!!!
      Data directory = get(pathString);
      directory.data.put(name,newData);
   }
   
   public void add(String[] path, String name, Data newData) //Directory string version
   { //TODO: safety check if file!!!!
      Data directory = get(path);
      directory.data.put(name,newData);
   }
   
   public void add(ArrayList<String> path, String name, Data newData) //Directory string version
   { //TODO: safety check if file!!!!
      Data directory = get(path);
      directory.data.put(name,newData);
   }

   
   //Retrive files
   public Data get(String[] path)
   {
      Data directory = disk;
      

      for(int i=0; i<path.length; i++) //TODO: add safety if directory does not exist or is a file!!!!
      {
         directory = directory.data.get(path[i]);
      }
      
      return directory;
   }
   
   public Data get(String pathString) //Directory string version
   {
      String[] path = parsePath(pathString);
      return get(path);
   }
   
   public Data get(ArrayList<String> path) //TODO: hopefully temperary when I clean up the run() command in script.java
   {
      Data directory = disk;

      for(int i=0; i<path.size(); i++)
      {
         directory = directory.data.get(path.get(i));
      }
      
      return directory;
   }
   
   //public show displayDir //Displays specific directory
   
   //TODO: this probably sucks and could be more efficient   
   private static String[] parsePath(String input)
   {
      String input2 = input; //this is a copy to be destroy/changed //TODO: tihs name sucks
      boolean loop = true; //TODO: surely there is a way to integrate this into the while loop?
      ArrayList<Integer> indexes = new ArrayList<Integer>();
      while(loop)
      {
         int index = input2.indexOf('/');
         if(index == -1)
            loop = false;
         else
         {
            indexes.add(index);
            //input2 = input2.substring(index+1,input2.length());
            input2 = input2.substring(0,index) + "\\" + input2.substring(index+1,input2.length());
         }
      }
      
      String[] output = new String[indexes.size()-1];
      for(int i=0; i<indexes.size()-1; i++)
         output[i] = input.substring(indexes.get(i)+1,indexes.get(i+1));
      
      return output;
   }
}
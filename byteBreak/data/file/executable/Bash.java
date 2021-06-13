package byteBreak.data.file.executable;

import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.Directory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Bash extends Executable
{
   public Bash()
   {
      super();
   }
   
   public Bash(String newName)
   {
      super(newName);
   }
   
   public Bash(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public String run(String dir, PC pc,ArrayList<String> args, int sess)
   {
      Scanner in = new Scanner(System.in);
      boolean loop = true;
      while(loop)
      {
         System.out.print("["+pc.login.get(sess).user+"@"+pc.host+"]# "); //TODO: this should be a variable per PC.
         
         //Parsing
         String command;
         ArrayList<String> args2 = new ArrayList<String>();
         {
            String input = in.nextLine();
            Collections.addAll(args2,input.split(" "));
         }
         command = args2.get(0);
         args2.remove(0);
         
         //Check if command is special case "exit". (No system binaries required)
         if(command.equals("exit"))
         {
            sess = -1;
            loop = false;
            break;
         }
         
         //Check if /bin exist.
         if(pc.disk.get("/bin/") == null) //TODO: base this off of kernel file or something instead of hard coding
            System.out.println("Error: Directory \"/bin/\" not found");
         
         //Command Validity and Execution
         else
         {
            if(pc.disk.get("/bin/"+command+"/") == null)
               System.out.println(command+": command not found");
            else
               System.out.print(pc.disk.get("/bin/"+command+"/").run(dir,pc,args2,sess));
         }
      }
      return "";
   }
}
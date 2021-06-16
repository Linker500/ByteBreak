package byteBreak.data.file.executable;

import byteBreak.Shell;
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
   
   public void run(Shell shell, ArrayList<String> args)
   {
      Scanner in = new Scanner(System.in);
      boolean loop = true;
      PC pc = shell.pc;
      int sess = Shell.sess;
      
      while(loop)
      {
         System.out.print("["+pc.login.get(Shell.sess).user+"@"+pc.host+"]# "); //TODO: this should be a variable per PC.
         
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
            shell.output("Error: Directory \"/bin/\" not found\n");
         
         //Command Validity and Execution
         else
         {
            if(pc.disk.get("/bin/"+command+"/") == null)
               shell.output(command+": command not found");
            else
               pc.disk.get("/bin/"+command+"/").run(shell,args2);
         }
      }
   }
}
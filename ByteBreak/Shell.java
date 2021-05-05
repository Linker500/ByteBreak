package ByteBreak;
import ByteBreak.*;
import ByteBreak.pcs.*;
import java.util.*;
public class Shell
{
   Network inter;
   int sess = -1;
   ArrayList<String> dir = new ArrayList<String>();
   
   public Shell(Network newInter)
   {
      inter = newInter;
   }
   
   public void start(String target)
   {
      PC pc = inter.net.get(target);
      pc.updateConfig();
      if(login(pc))
         input(pc);
   }
   
   public boolean login(PC pc)
   {
      boolean loop = true;
      Scanner in = new Scanner(System.in);
      System.out.println(pc.os);
      System.out.print("\n"+pc.host+" login: ");
      String user = in.nextLine();
      System.out.print("Password: ");
      String pass = in.nextLine();
      
      int newSess = pc.login(user,pass);
      if(newSess > -1)
      {
         System.out.println();
         sess = newSess;
         return true;
      }
      else
      {
         System.out.println("Login incorrect");
         return false;
      }
   }
   
   public void input(PC pc)
   {
      Scanner in = new Scanner(System.in);
      boolean loop = true;
      while(loop)
      {
         System.out.print("["+pc.login.get(sess).user+"@"+pc.host+"]# "); //TODO: this should be a variable per PC.
         
         //Parsing
         String command;
         ArrayList<String> args = new ArrayList<String>();
         {
            String input = in.nextLine();
            Collections.addAll(args,input.split(" "));
         }
         command = args.get(0);
         args.remove(0);
         
         //Check if command is special case "exit". (No system binaries required)
         if(command.equals("exit"))
         {
            sess = -1;
            loop = false;
            break;
         }
         
         //Check if /bin exist.
         if(pc.disk.get("/bin/") == null)
            System.out.println("Error: Directory \"/bin/\" not found");
         
         //Command Validity and Execution
         else
         {
            if(pc.disk.get("/bin/"+command+"/") == null)
               System.out.println(command+": command not found");
            else
               System.out.print(pc.disk.get("/bin/"+command+"/").run(dir,pc,args,inter,sess));
         }
      }
   }
}
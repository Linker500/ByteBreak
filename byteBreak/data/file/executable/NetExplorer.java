package byteBreak.data.file.executable;

import byteBreak.Shell;
import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.Util;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class NetExplorer extends Executable
{
   public NetExplorer()
   {
      super();
   }
   
   public NetExplorer(String newName)
   {
      super(newName);
   }
   
   public NetExplorer(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public void run(Shell shell, ArrayList<String> args)
   {
      PC pc = shell.pc;
      Disk disk = shell.pc.disk;
      ArrayList<String> dir = Shell.dir;
      int sess = Shell.sess;

      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;

      //TODO: this cannot be run silently unlike other programs. If not used by a human, it will still output to the human player! 
      //Perhaps add a "silent" flag?
      //Perhaps move this code somewhere else.
      
      Scanner in = new Scanner(System.in);
      String address = "0.0.0.1";
      int port = 80;
      File packet = new File("packet","/index.txt/");
      boolean loop = true;
      while(loop)
      {
         Data reply;
         if(pc.internet.get(address) == null) //TODO make PC method to get network stuff? pc.internet.get(0 and pc.lan.get() vs pc.lan() and pc.internet() [wan?]
            reply = new File("packet","Connection timed out.");
         else
            reply = pc.internet.get(address).serve(port,packet);
            
         Util.clear();
         shell.output("\033[1m\033[4m"+address+packet.body+"\033[0m\n\n"); //TODO: ANSI sequences must be in shell
         shell.output(reply.body+"\n\n");
         shell.output(">");
            
         
         //Command parsing //TODO: only single directory works atm. Add support for "vertical" movement in the filesystem. dns.com/abc/bcd/gay.txt Commands like "up" are needed.
         ArrayList<String> args2 = new ArrayList<String>();
         {
            String input = in.nextLine();
            Collections.addAll(args2,input.split(" "));
         }
         String command = args2.get(0);
         args2.remove(0);
         
         if(command.equals("goto"))
         {
            if(args2.size() > 0) //TODO: hardcoded for DNS
            {
               //address = pc.internet.get(pc.dns).serve(52,args2.get(0)).body; //DNS?
               address = args2.get(0); //ip address
               packet.body = "/index.txt/";
            }
         }
         else if(command.equals("save")) //TODO: save as?
         {            
            if(args2.size() > 0)
               pc.disk.add(dir,args2.get(0),reply);
            else
               pc.disk.add(dir,reply.name,reply); //TODO: permission checking at disk
         }
         else if(command.equals("help"))
         {
            
         }
         else if(command.equals("exit"))
            loop = false;
         else
            packet.body = "/" + command + "/"; //TODO: this is a quick fix to not have the program die on misinput.
      }      
      
      //return pc.internet.get(address).serve(port, );
      
      //TODO, implement DNS when added
      
      /*
      try
      {
         PC target = pc.internet.get(address);
         
         try{return target.serve(port);}
         catch(Exception e){return "Error: Access to port " + port + " denied!";}
      }
      catch(Exception e){
         return "Error: Invalid Address.";}*/
   }
}
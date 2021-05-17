package byteBreak.data.file.executable;

import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.util;

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
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, int sess)
   {
      Disk disk = pc.disk;

      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;

      //TODO: this cannot be run silently unlike other programs. If not used by a human, it will still output to the human player! 
      //Perhaps add a "silent" flag?
      //Perhaps move this code somewhere else.
      
      Scanner in = new Scanner(System.in);
      String address = "example.com";
      int port = 80;
      File packet = new File("packet","/index.txt/");
      boolean loop = true;
      while(loop)
      {
         Data reply = pc.internet.get(address).serve(port,packet);
         util.clear();
         System.out.println("\033[1m\033[4m"+address+packet.body+"\033[0m\n");
         System.out.println(reply.body+"\n");
         System.out.print(">");
         
         //Command parsing
         ArrayList<String> args2 = new ArrayList<String>();
         {
            String input = in.nextLine();
            Collections.addAll(args2,input.split(" "));
         }
         String command = args2.get(0);
         args2.remove(0);
         
         if(command.equals("goto"))
         {
            if(args2.size() > 0)
            {
               address = args2.get(0);
               packet.body = "/index.txt/";
            }
         }
         else if(command.equals("save"))
         {            
            if(args2.size() > 0)
               pc.disk.add(dir,args2.get(0),reply);
            else
               pc.disk.add(dir,reply.name,reply); //TODO: permission checking at disk
         }
         else if(command.equals("exit"))
            loop = false;
         else
            packet.body = command;
      }
      
      return "";
      
      
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
package ByteBreak.Data.File.Executable;

import ByteBreak.PC.PC;
import ByteBreak.Network;
import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.util;

import java.util.ArrayList;
import java.util.Scanner;

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
   
   public String run(ArrayList<String> dir, PC pc,ArrayList<String> args, Network inter, int sess)
   {
      Disk disk = pc.disk;
      
      Data workDir;
      workDir = disk.get(dir);

      int userPerm = pc.login.get(sess).perm;
      int permRead =  pc.disk.get(dir).permRead;
      int permWrite = pc.disk.get(dir).permWrite;

      //TODO: this cannot be run silently unlike other programs. If not used by a human, it will still output to the human player! 
      //Perhaps add a "silent" flag?
      //Perhaps move this code somewhere else.
      String address = "";
      int port = 80;
      
      if(args.size() == 0) //w/o arguments
      {
         util.clear();
         Scanner in = new Scanner(System.in);
         System.out.println("Welcome to Net Explorer!");
         util.stop(500);
         System.out.println("Please enter the address you wish to vist");   
         address = in.nextLine();
      }
      else if(args.size() >= 2) //domain
      {
         address = args.get(0);
         if(args.size() >= 2) //if port is specified
            port = Integer.parseInt(args.get(1));
         
         
      }
      
      return "\n";
      
      //TODO, implement DNS when added
      
      /*
      try
      {
         PC target = inter.net.get(address);
         
         try{return target.serve(port);}
         catch(Exception e){return "Error: Access to port " + port + " denied!";}
      }
      catch(Exception e){
         return "Error: Invalid Address.";}*/
   }
}
package ByteBreak;
import ByteBreak.*;
import ByteBreak.PC.*;
import ByteBreak.Data.File.Executable.*;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collections;

public class Shell
{
   Network internet;
   int sess = -1;
   ArrayList<String> dir = new ArrayList<String>();
   
   public Shell(Network newInternet)
   {
      internet = newInternet;
   }
   
   public void start(String target)
   {
      PC pc = internet.get(target);
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
      //TODO: holy crap this is sucky please make better
      //Read sys config file or something for default shell
      ArrayList<String> args = new ArrayList<String>(); 
      pc.disk.get("/bin/bash/").run(dir,pc,args,sess);
   }
}
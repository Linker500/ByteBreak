package byteBreak;
import byteBreak.*;
import byteBreak.pc.*;
import byteBreak.data.file.executable.*;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collections;

public class Shell
{
   public boolean quiet; //Controls whether shell output is ignored or not.
   public String clear = "\033[H\033[2J"; //TODO: perhaps set this per ANSI support; 
   public static int sess;
   public Network internet;
   public PC pc = null;
   public static ArrayList<String> dir = new ArrayList<String>();
   
   public Shell(Network newInternet, String target, boolean newQuiet) //TODO: can we reduce or simplify the paramets passed here?
   {
      internet = newInternet;
      quiet = newQuiet;
      try
      {
         pc = internet.get(target);
         pc.updateConfig();
      }
      catch (NullPointerException t) {} //TODO: what to do on failure?
   }
   
   public void start()
   {
      if(login(pc))
         input(pc);
   }
   
   private boolean login(PC pc)
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
   
   private void input(PC pc) //TODO: headless ("quiet") inputs?
   {
      //TODO: holy crap this is sucky please make better
      //Read sys config file or something for default shell
      ArrayList<String> args = new ArrayList<String>(); 
      pc.disk.get("/bin/bash/").run(this, args);
   }
   
   public void output(String string)
   {
      if(!quiet)
         System.out.print(string);
   } 
}
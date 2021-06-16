package byteBreak;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import byteBreak.pc.*;
import byteBreak.data.*;

public class ByteBreak
{   
   static Network internet;
   static ByteBoxAccount userAcc;
   
   public static void main(String[] args)
   {
      load();
      boolean loop = true;
      while(loop)
      {
         if(userAcc.firstTime == true)
         setup();
         
         Scanner in = new Scanner(System.in);
         Util.clear();
         System.out.println("Welcome to ByteBox! What would you like to do?");
         Util.stop(500);
         System.out.println("1. Connect to my ByteBox");
         System.out.println("2. Manage my ByteBox");
         System.out.println("3. Exit");
         System.out.print(">");
         String input = in.nextLine();
         
         if(input.equals("1")) //TODO: add an if statement for a delay on failure to show access denied or something
            connect();
         
         else if(input.equals("2"))
            manage();
         
         else if(input.equals("3"))
            loop = false;
         
         else
         {
            System.out.print("\nInvalid option!");
            Util.stop(1000);
         }
      }
      System.out.println("\nThank you for using ByteBox! See you soon.");
   }
   
   private static void connect()
   {
      Util.clear();
      Shell shell = new Shell(internet, userAcc.ip, false);
      shell.start();
      save();
      return;
   }
   
   private static void manage()
   {
      while(true)
      {
         Util.clear();
         Scanner in = new Scanner(System.in);
         Util.clear();
         System.out.println("How would you like to Manage your ByteBox?");
         Util.stop(500);
         System.out.println("1. Recover my ByteBox login.");
         System.out.println("2. Format my ByteBox.");
         System.out.println("3. Change my ByteBox subscription.");
         System.out.println("4. Delete my ByteBox account.");
         System.out.print(">");
         String input = in.nextLine();
         
         if(input.equals("1")) //Passwords are stored in plain text... kek //Perhaps limit how many times passwords can be recovered before format? "Live" system.
         {
            Util.clear();
            System.out.println("We are fetching your login. Please wait a moment.");
            Util.stop(10000);
            System.out.println("Sorry for the wait, your logins are:");
            Util.stop(1000);
            ArrayList<Login> login = internet.get(userAcc.ip).login;
            for(int i=0; i<login.size(); i++)
            {
               System.out.println(i+": "+login.get(i).user+" "+login.get(i).pass);
            }
            Util.stop(1000);
            System.out.println("Write this down!");
            Util.stop(1000);
            System.out.println("\n(Press enter when you are finished).");
            in.nextLine();
            return;
         }
         else if(input.equals("2"))
         {
            Util.clear();
            System.out.println("Are you ABSOLUTELY SURE you want to format your ByteBox?");
            Util.stop(2000);
            System.out.println("ALL DATA WILL BE LOST.");
            Util.stop(2000);
            System.out.println("We DO NOT KEEP BACKUPS.");
            Util.stop(2000);
            System.out.println("This action is IRREVERSIBLE.");
            Util.stop(2000);
            System.out.println("y/n");
            System.out.print(">");
            input = in.nextLine();
            
            if(input.equals("y"))
            {
               System.out.println("Will you regret this?");
               System.out.println("y/n");
               System.out.print(">");
               input = in.nextLine();
               if(input.equals("n"))
               {
                  System.out.println("Alright, just remember we warned you...");
                  Util.stop(2000);
                  System.out.println("Formating ByteBox... This will take a moment.");
                  internet.del(userAcc.ip);
                  userAcc.firstTime = true;
                  save();
                  Util.stop(20000);
                  System.out.println("ByteBox deleted!");
                  Util.stop(1000);
                  return;
               }
            }
            System.out.println("ByteBox format cancelled. Phew.");
            Util.stop(1000);
            return;
         }
         else
         {
            System.out.print("\nInvalid option!");
            Util.stop(1000);
         }
      }
   }
   
   private static void setup()
   {
      Scanner in = new Scanner(System.in);
      Util.clear();
      System.out.println("Congratulations on subscribing to your new ByteBox cloud computer!");
      Util.stop(2500);
      System.out.println("This setup wizard will help you get your new system up and running.");
      Util.stop(2500);
      System.out.println("What should the user account be called?");
      Util.stop(250);
      System.out.print(">");
      String user = in.nextLine();
      Util.stop(1000);
      System.out.println("And what should the password be?");
      Util.stop(250);
      System.out.print(">");
      String pass = in.nextLine();
      Util.stop(1000);
      System.out.println("Excellent! We are getting your ByteBox ready right now. Please be patient."); //TODO: Perhaps animated loading dots?
      
      String ipAddress = ipGen();
      userAcc.ip = ipAddress;
      PC pc = new ByteBox(internet); //Generate PC
      pc.disk.get("/sys/logins/").body = "root,toor,0,;"+user+","+pass+","+"1,;";
      internet.add(ipAddress,pc); //add PC
      
      
      Util.stop(30000);
      System.out.println("Done! You can connect to your machine in the next menu.");
      Util.stop(2500);
      System.out.println("Have a nice day!");
      userAcc.firstTime = false;
      save();
      Util.stop(2000);
   }
   
   //TODO: make clean error message if save file is not found.
   private static void load()
   {
      internet = null;
      try
      {
         FileInputStream fileIn = new FileInputStream("byteBreak/internet.dat");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         internet = (Network) in.readObject();
         in.close();
         fileIn.close();
      }
      catch (IOException i)
      {
         i.printStackTrace();
         return;
      } 
      catch (ClassNotFoundException c)
      {
         c.printStackTrace();
         return;
      }
      
      userAcc = null;
      try {
         FileInputStream fileIn = new FileInputStream("byteBreak/bytebox.dat");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         userAcc = (ByteBoxAccount) in.readObject();
         in.close();
         fileIn.close();
      }
      
      catch (IOException i)
      {
         i.printStackTrace();
         return;
      }
      
      catch (ClassNotFoundException c)
      {
         c.printStackTrace();
         return;
      }
   }
   private static void save()
   {
      try
      {
         FileOutputStream fileOut = 
         new FileOutputStream("byteBreak/bytebox.dat");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(userAcc);
         out.close();
         fileOut.close();
      }
      catch (IOException i)
      {
         i.printStackTrace();
      }
      
      
      try
      {
         FileOutputStream fileOut = 
         new FileOutputStream("byteBreak/internet.dat");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(internet);
         out.close();
         fileOut.close();
      }
      catch (IOException i)
      {
         i.printStackTrace();
      }
   }
   
   //TODO: should this be here? Maybe in utils?
   private static String ipGen()
   {
      int a = (int)(Math.random()*256);
      int b = (int)(Math.random()*256);
      int c = (int)(Math.random()*256);
      int d = (int)(Math.random()*256);
      return (a+"."+b+"."+c+"."+d);
   }
}
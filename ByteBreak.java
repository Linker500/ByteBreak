import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class ByteBreak
{
   static Network inter;
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
         util.clear();
         System.out.println("Welcome to ByteBox! What would you like to do?");
         util.stop(1000);
         System.out.println("1. Connect to my ByteBox");
         System.out.println("2. Manage my ByteBox");
         System.out.println("3. Exit");
         util.stop(250);
         System.out.print(">");
         String input = in.nextLine();
         
         if(input.equals("1"))
            connect();
         
         else if(input.equals("2"))
            manage();
         
         else if(input.equals("3"))
            loop = false;
         
         else
         {
            System.out.println("Invalid option!");
            util.stop(1000);
         }
      }
      System.out.println("Thank you for using ByteBox! See you soon.");
   }
   
   private static void connect()
   {
      util.clear();
      Shell shell = new Shell(inter);
      shell.start(userAcc.ip);
      save();
      return;
   }
   
   private static void manage()
   {
      while(true)
      {
         util.clear();
         Scanner in = new Scanner(System.in);
         util.clear();
         System.out.println("How would you like to Manage your ByteBox?");
         util.stop(1000);
         System.out.println("1. Recover my ByteBox login.");
         util.stop(500);
         System.out.println("2. Format my ByteBox.");
         util.stop(500);
         System.out.println("3. Change my ByteBox subscription.");
         util.stop(500);
         System.out.println("4. Delete my ByteBox account.");
         util.stop(500);
         System.out.print(">");
         String input = in.nextLine();
         
         if(input.equals("1")) //Passwords are stored in plain text... kek //Perhaps limit how many times passwords can be recovered before format? "Live" system.
         {
            util.clear();
            System.out.println("We are fetching your login. Please wait a moment.");
            util.stop(5000);
            System.out.println("Sorry for the wait, your logins are:");
            util.stop(250);
            ArrayList<Login> login = inter.net.get(userAcc.ip).login;
            for(int i=0; i<login.size(); i++)
            {
               util.stop(250);
               System.out.println(i+": "+login.get(i).user+" "+login.get(i).pass);
            }
            util.stop(1000);
            System.out.println("Write this down!");
            util.stop(1000);
            System.out.println("Press enter when you are finished.");
            in.nextLine();
            return;
         }
         else if(input.equals("2"))
         {
            util.clear();
            System.out.println("Are you ABSOLUTELY SURE you want to format your ByteBox?");
            util.stop(500);
            System.out.println("ALL DATA WILL BE LOST.");
            util.stop(500);
            System.out.println("We DO NOT KEEP BACKUPS.");
            util.stop(500);
            System.out.println("This action is IRREVERSIBLE.");
            util.stop(500);
            System.out.println("y/n");
            System.out.print(">");
            input = in.nextLine();
            
            if(input.equals("y"))
            {
               System.out.println("ARE YOU ABSOLUTELY SURE?");
               util.stop(500);
               System.out.println("y/n");
               System.out.print(">");
               input = in.nextLine();
               if(input.equals("y"))
               {
                  System.out.println("Alright, just remember we warned you...");
                  util.stop(1000);
                  System.out.println("Formating ByteBox... This will take a moment.");
                  inter.net.remove(userAcc.ip);
                  userAcc.firstTime = true;
                  save();
                  util.stop(5000);
                  System.out.println("ByteBox deleted!");
                  util.stop(1000);
                  return;
               }
            }
            System.out.println("ByteBox format cancelled. Phew.");
            util.stop(1000);
            return;
         }
         else
         {
            System.out.println("Invalid option!");
            util.stop(1000);
         }
      }
   }
   
   private static void setup()
   {
      Scanner in = new Scanner(System.in);
      util.clear();
      System.out.println("Congratulations on subscribing to your new ByteBox cloud computer!");
      util.stop(2500);
      System.out.println("This setup wizard will help you get your new system up and running.");
      util.stop(2500);
      System.out.println("What should the user account be called?");
      util.stop(250);
      System.out.print(">");
      String user = in.nextLine();
      util.stop(1000);
      System.out.println("And what should the password be?");
      util.stop(250);
      System.out.print(">");
      String pass = in.nextLine();
      util.stop(1000);
      System.out.println("Excellent! We are getting your ByteBox ready right now. Please be patient."); //TODO: Perhaps animated loading dots?
      
      String ipAddress = ipGen();
      userAcc.ip = ipAddress;
      PC pc = new PC("bytebpx",null); //Generate PC
      pc.disk.get("/sys/logins/").setBody("root,toor,0,;"+user+","+pass+","+"1,;");
      inter.net.put(ipAddress,pc); //add PC
      
      
      util.stop(10000);
      System.out.println("Done! You can connect to your machine in the next menu.");
      util.stop(2500);
      System.out.println("Have a nice day!");
      userAcc.firstTime = false;
      save();
      util.stop(1000);
   }
   
   //TODO: make clean error message if save file is not found.
   private static void load()
   {
      inter = null;
      try {
         FileInputStream fileIn = new FileInputStream("./data/inter.dat");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         inter = (Network) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         c.printStackTrace();
         return;
      }
      
      userAcc = null;
      try {
         FileInputStream fileIn = new FileInputStream("./data/bytebox.dat");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         userAcc = (ByteBoxAccount) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         c.printStackTrace();
         return;
      }
   }
   private static void save()
   {
      try
      {
         FileOutputStream fileOut = 
         new FileOutputStream("./data/bytebox.dat");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(userAcc);
         out.close();
         fileOut.close();
      }
      catch (IOException i)
      {
         i.printStackTrace();
         System.out.println("KERNEL PANIC!!!"); //A Kernel panic screen would be hilarious. 
      }
      
      
      try
      {
         FileOutputStream fileOut = 
         new FileOutputStream("./data/inter.dat");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(inter);
         out.close();
         fileOut.close();
      }
      catch (IOException i)
      {
         i.printStackTrace();
         System.out.println("KERNEL PANIC!!!");
      }
   }
   
   // private static void util.stop(long delay)
//    {
//       long end = System.currentTimeMillis() + delay;
//       
//       while(end>System.currentTimeMillis()){}
//    }
//    
//    private static void clear(){System.out.print("");}
//    // public static void clear(){System.out.print("\033[H\033[2J");}
   
   private static String ipGen()
   {
      int a = (int)(Math.random()*256);
      int b = (int)(Math.random()*256);
      int c = (int)(Math.random()*256);
      int d = (int)(Math.random()*256);
      return (a+"."+b+"."+c+"."+d);
   }
}
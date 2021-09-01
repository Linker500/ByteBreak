package byteBreak;
import byteBreak.pc.*;
import java.util.HashMap;

import java.io.*;
public class dataGen
{
   public static void main(String[] args)
   {
      ByteBoxAccount userAcc = new ByteBoxAccount(false,"1.3.3.7");
      Network internet = new Network();
      
     /*\
     
         Internet Organization (0.0.0.X)
      0.0.0.0 inter.net
      0.0.0.1 example.com
      
      1.1.1.1 Generic DNS
      
         ByteBox (3.8.X.X)
      3.8.0.0 BB central
      3.8.0.1 bytebox.net
      3.8.0.3 BB DNS (have blocked page?)
      3.8.1-9.X ByteBoxes
      
      1.3.3.7 Dev system
      
         To implement
      #.#.#.# "Mozilla website"
      #.#.#.# "Google website"
      
      #.#.#.# Email server
      #.#.#.# email website
     \*/
      
      HashMap<String,String> domains = new HashMap<String,String>();
      domains.put("inter.net","0.0.0.0");
      domains.put("example.com","0.0.0.1");
      domains.put("bytebox.net","3.8.0.3");
      //domains.put("","");
            
      internet.add("1.3.3.7",new Dev(internet));
      internet.add("1.1.1.1",new DnsHost(internet, domains));
      internet.add("0.0.0.1",new Example(internet));
      
      export(userAcc,internet);
   }
   
   private static void export(ByteBoxAccount userAcc, Network internet)
   {
      try
      {
         FileOutputStream fileOut = 
         new FileOutputStream("byteBreak/bytebox.dat");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(userAcc);
         out.close();
         fileOut.close();
         System.out.println(" AccountGen Export Success.");
      }
      catch (IOException i)
      {
         i.printStackTrace();
         System.out.println(" AccountGen Export FAILED!");
      }
      
      
      try
      {
         FileOutputStream fileOut = 
         new FileOutputStream("byteBreak/internet.dat");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(internet);
         out.close();
         fileOut.close();
         System.out.println("InternetGen Export Success.");
      }
      catch (IOException i)
      {
         i.printStackTrace();
         System.out.println("InternetGen Export FAILED!");
      }
   }
   
   private static String ipGen()
   {
      int a = (int)(Math.random()*256);
      int b = (int)(Math.random()*256);
      int c = (int)(Math.random()*256);
      int d = (int)(Math.random()*256);
      System.out.println(a+"."+b+"."+c+"."+d);
      return (a+"."+b+"."+c+"."+d);
   }
}

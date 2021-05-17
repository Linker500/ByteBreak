package byteBreak;
import byteBreak.pc.*;
import java.util.TreeMap;

import java.io.*;
public class dataGen
{
   public static void main(String[] args)
   {
      ByteBoxAccount userAcc = new ByteBoxAccount(false,"0");
      Network internet = new Network();
      
     /*\
      #.#.#.# Bad DNS
      1.1.1.1 Normal DNS
      9.9.9.9 "good" dns
      
      #.#.#.# example.com
      
      #.#.#.# website that says blocked
      
      #.#.#.# user machine
      #.#.#.# generic other machine
      
      #.#.#.# bytebox website
      #.#.#.# bytebox central
      #.#.#.# bytebox box management
      
      #.#.#.# "Mozilla website"
      #.#.#.# "Google website"
      #.#.#.# "Microsoft website"
      
      #.#.#.# Email server
      #.#.#.# email website
      
      #.#.#.# ISP website
      #.#.#.# ISP control server
     \*/
      
      TreeMap<String,String> domains = new TreeMap<String,String>();
      domains.put("example.com","2");
            
      internet.add("0",new Dev(internet));
      internet.add("1",new DnsHost(internet, domains));
      internet.add("2",new Example(internet));
      
      export(userAcc,internet);
   }
   
   private static void export(ByteBoxAccount userAcc, Network internet)
   {
      try
      {
         FileOutputStream fileOut = 
         new FileOutputStream("byteBreak/savedata/bytebox.dat");
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
         new FileOutputStream("byteBreak/savedata/internet.dat");
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

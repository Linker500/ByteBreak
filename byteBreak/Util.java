package byteBreak;
//TODO: perhaps put filepath parser here
public class Util
{
   public static final String ANSICLEAR = "\033[H\033[2J"; //TODO: this is for clearing in BASH... perhaps auto select it for other systems?

   public static void stop(long delay)
   {
      try
      {
         Thread.sleep(delay);
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }
   
   public static void clear(){System.out.print(ANSICLEAR);}
}
package ByteBreak;
//TODO: perhaps put filepath parser here
public class util
{
   public static void stop(long delay)
   {
      long end = System.currentTimeMillis() + delay;
      
      while(end>System.currentTimeMillis()){}
   }
   
   public static void clear(){System.out.print("");}
   // public static void clear(){System.out.print("\033[H\033[2J");}
}
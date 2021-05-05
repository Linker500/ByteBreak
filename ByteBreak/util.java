package ByteBreak;
//TODO: perhaps put filepath parser here
public class util
{
   static final String ANSICLEAR = "\033[H\033[2J"; //TODO: this is for clearing in BASH... perhaps auto select it for other systems?

   public static void stop(long delay)
   {
      long end = System.currentTimeMillis() + delay;
      
      while(end>System.currentTimeMillis()){}
   }
   
   public static void clear(){System.out.print(ANSICLEAR);}
}
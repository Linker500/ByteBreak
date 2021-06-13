package byteBreak;
public class ByteBoxAccount implements java.io.Serializable
{
   public boolean firstTime = true;
   public String ip = "0";
   public int accountTier = 0; //-1 = no subscription
   public long accountStart = 0; //Epoch time
   public int accountTime = 0; //Days
   
   ByteBoxAccount(boolean newFirstTime, String newIp){firstTime = newFirstTime; ip = newIp;}
}
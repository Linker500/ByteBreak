public class ByteBoxAccount implements java.io.Serializable
{
   boolean firstTime = true;
   String ip = "0";
   int accountTier = 0; //-1 = no subscription
   long accountStart = 0; //Epoch time
   int accountTime = 0; //Days
   
   ByteBoxAccount(boolean newFirstTime, String newIp){firstTime = newFirstTime; ip = newIp;}
}
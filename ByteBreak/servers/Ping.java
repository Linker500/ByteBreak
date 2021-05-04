package ByteBreak.servers;
import ByteBreak.*;
import java.util.TreeMap;
public class Ping implements Server, java.io.Serializable
{
   public Ping(){}
   
   public Data serve(Data request)
   {
      Data reply = new File("packet","ping");
      return reply;
   }
}
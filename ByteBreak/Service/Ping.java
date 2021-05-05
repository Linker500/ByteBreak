package ByteBreak.Service;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.File;
import java.util.TreeMap;
public class Ping implements Service
{
   public Ping(){}
   
   public Data serve(Data request)
   {
      Data reply = new File("packet","ping");
      return reply;
   }
}
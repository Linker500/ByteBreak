package ByteBreak.Service;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.Text;
import java.util.TreeMap;
public class Ping implements Service
{
   public Ping(){}
   
   public Data serve(Data request)
   {
      Data reply = new Text("packet","ping");
      return reply;
   }
}
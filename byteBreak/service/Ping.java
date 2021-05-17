package byteBreak.service;
import byteBreak.data.Data;
import byteBreak.data.file.File;
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
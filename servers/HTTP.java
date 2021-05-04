package ByteBreak.servers;
import ByteBreak.*;
//import java.util.TreeMap;
import java.util.ArrayList;
import java.util.TreeMap;

public class HTTP implements Server, java.io.Serializable
{
   Disk webData;
   Data directory;
   
   public HTTP(Data newDirectory)
   {
      webData = new Disk(newDirectory);
   }
   
   //TODO: IMPLEMENT SOME FORMS OF HTTP CODES?
   public Data serve(Data request)
   {      
      if(request.getName().equals("packet"))
         return webData.get(request.getBody());
      
      return new File("packet","404");
   }
}
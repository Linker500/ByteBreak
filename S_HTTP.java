//import java.util.TreeMap;
import java.util.ArrayList;

public class S_HTTP implements Server, java.io.Serializable
{
   Disk webData;
   
   public S_HTTP(Disk newWebData)
   {
      webData = newWebData;
   }
   
   //TODO: IMPLEMENT SOME FORMS OF HTTP CODES?
   public Data serve(Data request)
   {      
      if(request.getName().equals("get"))
      {
         File reply = new File("packet");
                  
         reply.setBody(webData.get(request.getBody()).getBody()); //TODO: nmake this more verbose it sucks to read
         return reply;
      }
      
      return new File();
   }
}
package ByteBreak.Service;
import ByteBreak.Data.Data;
import ByteBreak.Data.File.Text;
import java.util.TreeMap;

public class DNS implements Service
{
   TreeMap<String,String> domains = new TreeMap<String,String>();
   
   public DNS(TreeMap<String,String> newDomains)
   {
      domains = newDomains;
   }
   
   public Data serve(Data request)
   {
      Data reply = new Text("packet");
      
      reply.setBody("Error: invalid domain");
      for(String i : domains.keySet())
      {
         if(request.getBody().equals(i))
         {
            reply.setBody(domains.get(i));
            return reply;
         }
      }                        
      return reply;

   }
}
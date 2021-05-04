package ByteBreak.servers;
import ByteBreak.*;
import java.util.TreeMap;

public class DNS implements Server, java.io.Serializable
{
   TreeMap<String,String> domains = new TreeMap<String,String>();
   
   public DNS(TreeMap<String,String> newDomains)
   {
      domains = newDomains;
   }
   
   public Data serve(Data request)
   {
      Data reply = new File("packet");
      
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
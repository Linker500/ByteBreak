package byteBreak.service;
import byteBreak.data.Data;
import byteBreak.data.file.File;
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
      Data reply = new File("packet");
      
      reply.body = "Error: invalid domain";
      for(String i : domains.keySet())
      {
         if(request.body.equals(i))
         {
            reply.body = domains.get(i);
            return reply;
         }
      }                        
      return reply;

   }
}
import java.util.TreeMap;

public class S_DNS implements Server, java.io.Serializable
{
   TreeMap<String,String> domains = new TreeMap<String,String>();
   
   public S_DNS(TreeMap<String,String> newDomains)
   {
      domains = newDomains;
   }
   
   public TreeMap<String,Data> serve(TreeMap<String,Data> request)
   {
      TreeMap<String,Data> reply = new TreeMap<String,Data>();
      
      int inc = 0;
      for (String i : request.keySet())
      {
         reply.put(i, new File(i,"Error: invalid domain"));
         for (String j : domains.keySet())
         {
            if(request.get(i).getBody().equals(j))
               reply.get(i).setBody(domains.get(j));
         }
         inc++;
      }
      return reply;
   }
}
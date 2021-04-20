import java.util.TreeMap;
public class S_Ping implements Server, java.io.Serializable
{
   public S_Ping(){}
   
   public TreeMap<String,Data> serve(TreeMap<String,Data> request)
   {
      TreeMap<String,Data> reply = new TreeMap<String,Data>();
      reply.put("packet",new File("packet","ping"));
      return reply;
   }
}
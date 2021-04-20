import java.util.TreeMap;
public class S_Test implements Server, java.io.Serializable //Perhaps make other servers implement this instead of extend?
{
   public S_Test(){}
   
   public TreeMap<String,Data> serve(TreeMap<String,Data> request)  //Actual webserver content.Possibly 
   {
      TreeMap<String,Data> reply = new TreeMap<String,Data>();
      reply.put("packet",new File("packet","Your request was: " + request));
      return reply;
   }
}
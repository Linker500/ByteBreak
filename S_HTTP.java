import java.util.TreeMap;

public class S_HTTP implements Server, java.io.Serializable
{
   TreeMap<String,Data> webData;
   
   public S_HTTP(TreeMap<String,String> webData)
   {
      
   }
   
   public TreeMap<String,Data> serve(TreeMap<String,Data> request)
   {
      TreeMap<String,Data> reply = new TreeMap<String,Data>();
      
      
      
      return reply;
   }
}
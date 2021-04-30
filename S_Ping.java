import java.util.TreeMap;
public class S_Ping implements Server, java.io.Serializable
{
   public S_Ping(){}
   
   public Data serve(Data request)
   {
      Data reply = new File("packet","ping");
      return reply;
   }
}
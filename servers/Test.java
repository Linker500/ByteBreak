package ByteBreak.servers;
import ByteBreak.*;
import java.util.TreeMap;
public class Test implements Server, java.io.Serializable //Perhaps make other servers implement this instead of extend?
{
   public Test(){}
   
   public Data serve(Data request)  //Actual webserver content.Possibly 
   {
      Data reply = new File("packet","Your request was: " + request.getBody());
      return reply;
   }
}
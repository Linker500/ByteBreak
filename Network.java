import java.util.TreeMap;
public class Network implements java.io.Serializable
{
   TreeMap<String,PC> net;
   
   public Network()
   {
      net = new TreeMap<String,PC>();
   }
}
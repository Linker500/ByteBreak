package ByteBreak;
import ByteBreak.PC.PC;
import java.util.TreeMap;
public class Network implements java.io.Serializable
{
   public TreeMap<String,PC> net;
   
   public Network()
   {
      net = new TreeMap<String,PC>();
   }
}
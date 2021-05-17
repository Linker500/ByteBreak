package byteBreak;
import byteBreak.pc.PC;
import java.util.TreeMap;
public class Network implements java.io.Serializable
{
   //TODO: add things like a method to generate new ip address
   public TreeMap<String,PC> net;
   
   public Network()
   {
      net = new TreeMap<String,PC>();
   }
   
   public PC get(String address) //TODO safety check plz
   {
      return net.get(address);
   }
   
   public int add(String address, PC pc)
   {
      net.put(address, pc);
      return 0; //TODO: return error codes and safety checks if like overwriting an existing IP
   }
   
   public void del(String address)
   {
      net.remove(address);
   }
}
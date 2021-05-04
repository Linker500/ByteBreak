package ByteBreak.servers;
import ByteBreak.*;
import java.util.TreeMap;
public interface Server extends java.io.Serializable
{  
   public Data serve(Data reply);
}
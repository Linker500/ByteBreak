 package ByteBreak.Service;
import ByteBreak.Data.Data;
import java.util.TreeMap;
public interface Service extends java.io.Serializable
{  
   public Data serve(Data reply);
}
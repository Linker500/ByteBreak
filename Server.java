import java.util.TreeMap;
public interface Server extends java.io.Serializable
{  
   public TreeMap<String,Data> serve(TreeMap<String,Data> reply);
}
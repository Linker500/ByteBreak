package ByteBreak.Service;
import ByteBreak.Disk;
import ByteBreak.Data.Data;
import ByteBreak.Data.Directory;
import ByteBreak.Data.File.Text;
//import java.util.TreeMap;
import java.util.ArrayList;
import java.util.TreeMap;

public class HTTP implements Service
{
   Disk webData;
   Data directory;
   
   public HTTP(Data newDirectory)
   {
      webData = new Disk(newDirectory);
   }
   
   //TODO: IMPLEMENT SOME FORMS OF HTTP CODES?
   public Data serve(Data request)
   {      
      if(request.getName().equals("packet"))
         return webData.get(request.getBody());
      
      return new Text("packet","404");
   }
}
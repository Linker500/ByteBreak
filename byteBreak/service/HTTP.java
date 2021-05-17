package byteBreak.service;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.Directory;
import byteBreak.data.file.File;
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
      return webData.get(request.body); //TODO implement HTTP codes into body. Write parser
   }
}
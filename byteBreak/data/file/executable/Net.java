package byteBreak.data.file.executable;

import byteBreak.Shell;
import byteBreak.pc.PC;
import byteBreak.Network;
import byteBreak.Disk;
import byteBreak.data.Data;
import byteBreak.data.file.File;
import byteBreak.pc.PC;

import java.util.ArrayList;

public class Net extends Executable
{
   public Net()
   {
      super();
   }
   
   public Net(String newName)
   {
      super(newName);
   }
   
   public Net(String newName, int newPermRead, int newPermWrite)
   {
      super(newName,newPermRead,newPermWrite);
   }
   
   public void run(Shell shell, ArrayList<String> args) //TODO: save returned file
   {
      PC pc = shell.pc;
      Disk disk = shell.pc.disk;
      ArrayList<String> dir = Shell.dir;
      int sess = Shell.sess;
      
      Data workDir;
      workDir = disk.get(dir);

      int userPerm = pc.login.get(sess).perm;

      if(args.size() < 3)
      {
         if(args.size() == 0)
            shell.output("net: Missing target argument\n");
         else if(args.size() == 1)
            shell.output("net: Missing port argument\n");
         else if(args.size() == 2)
            shell.output("net: Missing request argument\n");
      }
      
      else if(workDir.permWrite < userPerm)
         shell.output("net: Write access to " + workDir.name + " denied\n");
      
      else
      {
         Data request = new File("packet",args.get(2));
         
         String address = args.get(0);
         
         PC target = pc.internet.get(args.get(0));
         if(target == null)
            shell.output("net: host " + address + " not found\n");
         
         int port;
         
         try
         {
            port = Integer.parseInt(args.get(1));
            
            Data reply = pc.internet.get(address).serve(port,request);
            reply.permRead = userPerm;
            reply.permWrite = userPerm;
            workDir.data.put(reply.name,reply);
            
            shell.output(reply.body+"\n");
         }
         catch(Exception e) {shell.output("net: " + args.get(1) + " not a valid port\n");}
      }
   }
}
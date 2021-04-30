import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Scanner;
public class script
{  
   //TODO: the data passed to this function SUCKS
   public static String run(ArrayList<String> dir, PC pc, int command, ArrayList<String> args, Network inter, int sess)
   {
      Disk disk = pc.disk;
      
      Data workDir;
      workDir = disk.get(dir);
      
      int userPerm = pc.login.get(sess).priv; //TODO:Why privlege vs perm???
      int permRead =  pc.disk.get(dir).getPermRead();
      int permWrite = pc.disk.get(dir).getPermWrite();
      
//       //TODO we have a parser for filepaths now... perhaps deprecate this?
//       for(int i=0; i<dir.size(); i++)
//       {
//          if(i == dir.size()-1)
//          {
//             permRead = workDir.getData().get(dir.get(i)).getPermRead();
//             permWrite = workDir.getData().get(dir.get(i)).getPermWrite();
//          }
//          workDir = workDir.getData().get(dir.get(i));
//       } 
      
      //TODO: enums sound cool for this
      switch(command)
      {
         case(0): //List "ls" "dir"
         { //TODO: there is an extra space at the end. Does anyone really care though?
            String output = "";
            if(workDir.getData().size() == 0)
               return output;
            
            for (String i : workDir.getData().keySet())
               output += i + " ";
            
            return output;
         }
         
         case(1): //Change Directory "cd"
         {  
            if(args.size() == 0)
            {
               if(dir.size() > 0)
                  dir.remove(dir.size()-1);
               return "";
            }
            else if(args.size() > 0)
            {
               if(workDir.getData().get(args.get(0)) == null)
                  return "cd: No such directory";
               if(workDir.getData().get(args.get(0)).identify() == 1)
                  return "cd: " + args.get(0) + ": Not a directory";
               if(workDir.getData().get(args.get(0)).getPermRead() < userPerm)
                  return "cd: Read access to " + args.get(0) + " denied";
               
               dir.add(args.get(0));
               return "";
            }
            return "cd: Unknown Error";
         }
         
         case(2): //Concatanate "cat" "show" //TODO: show "compiled" non ASCII text when showing command
         {  
            if(args.size() == 0)
               return "cat: Missing target argument";
            
            String output = "";
            for(int i=0; i<args.size(); i++)
            {
               if(workDir.getData().get(args.get(i)).getPermRead() < userPerm)
                  output += "cat: Read access to " + args.get(0) + " denied";
               else
                  output += workDir.getData().get(args.get(i)).getBody();
            }
            return output;
         }
         
         case(3): //Make "mk" "new"
         {
            if(permWrite < userPerm)
               return "mk: Write access to " + workDir.getName() + " denied";
            
            if(args.size() == 0)
               workDir.getData().put("new_file",new File());
            else if(args.size() == 1)
               workDir.getData().put(args.get(0),new File(args.get(0)));
            else if(args.size() == 2)
               workDir.getData().put(args.get(0),new File(args.get(0),args.get(1)));
            else if(args.size() > 2)
            {
               String edit = args.get(1);
               for(int j=2; j<args.size(); j++)
                  edit+=(" "+args.get(j));
               workDir.getData().get(args.get(0)).setBody(edit);
            }
            return "";
         }
         
         case(4): //Remove "rm" "del"
         {  
            if(args.size() == 0)
               return "rm: Missing target parameter";
            
            String output = "";
            for(int i=0; i<args.size(); i++)
            {
               if(workDir.getData().get(args.get(i)).getPermWrite() < userPerm)
                  output += "rm: Write access to " + args.get(0) + " denied";
               else
                  workDir.getData().remove(args.get(i));
            }
            
            return output;
         }
         
         case(5): //Move "mv" "move"
         {
            if(workDir.getData().get(args.get(0)).getPermRead() < userPerm)
               return "mv: Read access to " + args.get(0) + " denied";
            
            if(workDir.getData().get(args.get(0)).getPermWrite() < userPerm)
               return "mv: Write access to " + args.get(0) + " denied";
            
            if(workDir.getPermWrite() < userPerm)
               return "mv: Write access to " + workDir.getName() + " denied";
            
            if(args.size() <2)
            {
               if(args.size() == 1)
                  return "mv: Missing destination file argument.";
               if(args.size() == 0)
                  return "mv: Missing file argument.";
            }
            workDir.getData().put(args.get(1),workDir.getData().get(args.get(0)));
            workDir.getData().remove(args.get(0));
            return "";
         }
         
         case(6): //Edit "ed" "mod" //TODO: prevent editing of command files
         {
            if(args.size() == 0)
               return "ed: Missing target file argument.";
           
            if(workDir.getData().get(args.get(0)).getPermWrite() < userPerm)
               return "ed: Write access to " + args.get(0) + " denied";
            
            if(args.size() == 1)
               workDir.getData().get(args.get(0)).setBody("");
            else
            {
               String edit = args.get(1);
               for(int j=2; j<args.size(); j++)
                  edit+=(" "+args.get(j));
               workDir.getData().get(args.get(0)).setBody(edit);
            }          
            return "";
         }
         
         case(7): //Copy "cp" "copy"
         {
            if(workDir.getData().get(args.get(0)).getPermRead() < userPerm)
               return "mv: Read access to " + args.get(0) + " denied";
            
            if(workDir.getPermWrite() < userPerm)
               return "mv: Write access to " + workDir.getName() + " denied";
            
            if(args.size() <2)
            {
               if(args.size() == 1)
                  return "cp: Missing target file argument";
               if(args.size() == 0)
                  return "cp: Missing destination file argument";
            }
            
            workDir.getData().put(args.get(1),workDir.getData().get(args.get(0)));
            return "";
         }
         case(8): //Secure Shell "ssh" "remote"
         {
            //TODO: this is a TEMPERARY way of connecting. Use IP ADDRESSES or HOSTNAMES later!
            if(args.size() == 0)
               return "ssh: Missing target system argument";
            
            Shell shell = new Shell(inter);
            shell.start(args.get(0));
            return "Connection closed";
         }
         
         case(9): //network "net" "netcat"?
         {
            if(args.size() < 3)
            {
               if(args.size() == 0)
                  return "net: Missing target argument";
               if(args.size() == 1)
                  return "net: missiong port argument";
               if(args.size() == 2)
                  return "net: missing request argument";
            }
            Data request = new File("packet",args.get(2));
            
            String address = args.get(0);
            
            PC target = inter.net.get(args.get(0));
            if(target == null)
               return "net: host " + address + " not found";
            
            int port;
            try{port = Integer.parseInt(args.get(1));}
            catch(Exception e){return "net: " + args.get(1) + " not a valid port";}
            
            
            Data reply = inter.net.get(address).serve(port,request);
                        
            return reply.getBody();
         }
         
         case(10): //Net Save "netsv"
         {
            //TODO: what is this and why is it here
            /*
            if(args.size() < 3)
            {
               if(args.size() == 0)
                  return "net: Missing target argument";
               if(args.size() == 1)
                  return "net: missiong port argument";
               if(args.size() == 2)
                  return "net: missing request argument";
            }
            
            if(permWrite < userPerm)
               return "mk: Write access to " + workDir.getName() + " denied";
            
            TreeMap<String,Data> request = new TreeMap<String,Data>();
            for(int i=2; i<args.size(); i++)
               request.put("packet"+i, new File("packet"+i,args.get(i)));
            
            String address = args.get(0);
            
            PC target = inter.net.get(args.get(0));
            if(target == null)
               return "net: host " + address + " not found";
            
            int port;
            try{port = Integer.parseInt(args.get(1));}
            catch(Exception e){return "net: " + args.get(1) + " not a valid port";}
            
            
            Data reply = inter.net.get(address).serve(port,request);
            
            String output = "";
            
            workDir.getData().put("netsv",new Directory("netsv",userPerm,userPerm));
            
            int inc = 0;           
            for (String i : reply.keySet())
            {
               workDir.getData().get("netsv").getData().put("packet"+inc,new File("packet"+inc,reply.get(i).getBody(),userPerm,userPerm));
               inc++;
            }
            return "netsv: " +inc+ " packets saved";
            */
            return "idk";
         }
         
         /* //TODO: move and implement this somewhere!!
         case(9): //Secure Copy "scp" "netcopy"
         {
            return "scp";
         }
         */
         
         case(11): //Net Explorer "netexp"
         {
            //TODO: this cannot be run silently unlike other programs. If not used by a human, it will still output to the human player! 
            //Perhaps add a "silent" flag?
            //Perhaps move this code somewhere else.
            String address = "";
            int port = 80;
            
            if(args.size() == 0) //w/o arguments
            {
               util.clear();
               Scanner in = new Scanner(System.in);
               System.out.println("Welcome to Net Explorer!");
               util.stop(500);
               System.out.println("Please enter the address you wish to vist");   
               address = in.nextLine();
            }
            else if(args.size() >= 2) //domain
            {
               address = args.get(0);
               if(args.size() >= 2) //if port is specified
                  port = Integer.parseInt(args.get(1));
               
               
            }
            
            //TODO, implement DNS when added
            
            /*
            try
            {
               PC target = inter.net.get(address);
               
               try{return target.serve(port);}
               catch(Exception e){return "Error: Access to port " + port + " denied!";}
            }
            catch(Exception e){
               return "Error: Invalid Address.";}*/
         }
         case(12): //Firebird "firebird"
         {
            return "firebird";
         }
         case(13): //Vanadium "vana"
         {
            return "vana";
         }
         case(14): //Roots Browser "roots"
         {
            return "roots";
         }
         default:
         {
            return "Unknown Error";
         }
      }
   }
}
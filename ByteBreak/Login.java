package ByteBreak;
public class Login implements java.io.Serializable
{
   public String user;
   public String pass;
   public int perm; //This is not a boolean to allow "tiers" of users. Rights will vary per PC.
   
   public Login(String newUser, String newPass, int newPerm)
   {
      user = newUser;
      pass = newPass;
      perm = newPerm;
   }
}
public class Login implements java.io.Serializable
{
   String user;
   String pass;
   int priv; //This is not a boolean to allow "tiers" of users. Rights will vary per PC.
   
   public Login(String newUser, String newPass, int newPriv)
   {
      user = newUser;
      pass = newPass;
      priv = newPriv;
   }
}
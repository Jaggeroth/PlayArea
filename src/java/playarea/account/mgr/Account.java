package playarea.account.mgr;

import java.util.zip.Checksum;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.zip.Adler32;

/**
 * INSERT INTO cohauth.dbo.user_auth (account, password, salt, hash_type) 
 * VALUES ('test', CONVERT(BINARY(128),'46ffce3efcfe83bfa205b076d7c2084b9dcf04cdb26f9019103cde29779d26a85216b2c0f43ba1a8fb9b7fa22f05a949bf4edc314af27629e8fc23014e77a24d'), 0, 1);
 **/
public class Account {
  public static final String LINE_1 = "INSERT INTO cohauth.dbo.user_account (account, uid, forum_id, pay_stat) VALUES ('%s', %s, 1, 1014);";
  public static final String LINE_2 = "INSERT INTO cohauth.dbo.user_auth (account, password, salt, hash_type) VALUES ('%s', CONVERT(BINARY(128),'%s'), 0, 1);";
  public static final String LINE_3 = "INSERT INTO cohauth.dbo.user_data (uid, user_data) VALUES (%s, 0x0080C2E000D00B0C000000000CB40058);";
  public static final String LINE_4 = "INSERT INTO cohauth.dbo.user_server_group (uid, server_group_id) VALUES (%s, 1);";

  public static void main(String[] args) {
    String username = "Jagged";
    String password = "Jagg3d";
    Account account = new Account();
    account.generateSQL(username, password);
  }
  
  private void generateSQL(String uName, String pwd) {
    String pHash = generate(uName, pwd);
    String uid = String.valueOf(getAlder32(uName));
    System.out.format(LINE_1, uName, uid);
    System.out.println();
    System.out.format(LINE_2, uName, pHash);
    System.out.println();
    System.out.format(LINE_3, uid);
    System.out.println();
    System.out.format(LINE_4, uid);
    System.out.println();
  }
  
  private long getAlder32(String str) {
    byte bytes[] = str.getBytes();
    Checksum checksum = new Adler32();
    checksum.update(bytes, 0, bytes.length);
    return checksum.getValue();
  }
  
  private String generate(String authName, String password) {
    String n = authName.toLowerCase();
    String n1 =  Integer.toHexString(Math.toIntExact(getAlder32(n)));
    String n2 = String.format("%0"+ (8 - n1.length() )+"d%s  ",0 ,n1);
    String r1 = n2.substring(6, 8);
    String r2 = n2.substring(4, 6);
    String r3 = n2.substring(2, 4);
    String r4 = n2.substring(0, 2);
    String r5 = password+r1+r2+r3+r4;
    return getSHA512(r5);
  }
  

  private String getSHA512(String input){
    String toReturn = null;
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      //digest.reset();
      digest.update(input.getBytes());
      toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return toReturn;
  }
}

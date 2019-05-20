import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

/**
* A simple hashing library for byte blocks.
*
* @author  David Golding
* @version 1.0
* @since   2018-04-15
*/

public class Hashing {

  /** Method to produce SHA256 hash for a byte array.
   * This method is adapted from https://stackoverflow.com/questions/5531455/how
   * -to-hash-some-string-with-sha256-in-java.
   *
   * @param block where data is an array of bytes.
   * @return A byte array containing the SHA256 hash of the String given.
   */
  public static byte[] SHA256(byte[] block) {
    // try catch used to handle the checked exception in the instance that
    // "SHA-256" wasn't a valid message digest algorithm.
    try{
      // The string is converted to an array of bytes and then the bytes are
      // hashed.
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] byteHash = digest.digest(block);
      return byteHash;
    } catch(NoSuchAlgorithmException ex){
       throw new RuntimeException("Could not has block.");
    }
  }

  /** Method to produce convert a byte hash to a string hash.
   * This method is adapted from https://www.mkyong.com/java/java-sha-hashing-ex
   * ample.
   *
   * @param bytes where bytes is the hash in an array of bytes.
   * @return A String containing the SHA256 hash of the bytes given.
   */
  public static String byteToStringHash(byte[] bytes) {
    // Each byte in the array is then converted to hexadecimal and appended
    // to a String containing the hexademial version of the hash.
    String text = "";
    for (byte b : bytes)
      text+=String.format("%02x", b);
    // String version of the bytes is returned as the hash.
    return text;
  }

  /** Method to convert the blocks content to an array of bytes.
   * This method is adapted from https://stackoverflow.com/questions/2836646/jav
   * a-serializable-object-to-byte-array.
   *
   * @return An array of bytes.
   */
  public static byte[] dataToBytes(Object data) {
    try{
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(data);
      oos.flush();
      return bos.toByteArray();
    } catch (Exception e) {
      throw new RuntimeException("Could not convert the block's data to bytes.");
    }
  }

  /** Method to hash a block.
   *
   * @return An array of bytes representing the SHA256 hash of the block.
   */
  public static byte[] hashBlock(int index, Object data, byte[] prevHash) {
    // Converts data to an array of bytes.
    byte[] dataInBytes = dataToBytes(data);
    // Creates a byte array for the byte version of the data
    byte[] block = new byte[dataInBytes.length+prevHash.length+1];
    // Adds the index, previous hash and the data to the block byte array.
    block[0] = (byte) index;
    for (int i = 1; i < block.length; i++) {
      if (i-1 < prevHash.length) {
        block[i] = prevHash[i-1];
      } else {
        block[i] = dataInBytes[i-prevHash.length-1];
      }
    }
    // Returns the SHA256 hash of the block.
    return SHA256(block);
  }

  /** Method to hash a block.
   *
   * @return An array of bytes representing the SHA256 hash of the block.
   */
  public static byte[] hashGenisis(int index, Object data) {
    // Converts data to an array of bytes.
    byte[] dataInBytes = dataToBytes(data);
    // Creates a byte array for the byte version of the data
    byte[] block = new byte[dataInBytes.length+1];
    // Adds the index, previous hash and the data to the block byte array.
    block[0] = (byte) index;
    for (int i = 1; i < block.length; i++) {
        block[i] = dataInBytes[i-1];
    }
    // Returns the SHA256 hash of the block.
    return SHA256(block);
  }
}

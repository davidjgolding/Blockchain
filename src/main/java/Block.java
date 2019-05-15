/**
* A simple block.
*
* @author  David Golding
* @version 1.0
* @since   2018-05-15
*/

public class Block<T>{

  // Position of block in the blockchain.
  final int index;
  // Data contained within the block.
  final T data;
  // Hash of this block.
  final byte[] hash;

  /** Constructs an instance of a genisis block. */
  public Block(int index, T data) {
    this.index = index;
    this.data = data;
    this.hash = generateHash();
  }

  /** Constructs an instance of a normal block. */
  public Block(int index, T data, byte[] prevHash) {
    this.index = index;
    this.data = data;
    this.hash = generateHash(prevHash);
  }

  /** Method to get the position of the block within the blockchain.
   *
   * @return An integer representing the position.
   */
  public int getIndex() {
    return index;
  }

  /** Method to return the data contained within the block.
   *
   * @return Returns the data of the generic type T.
   */
  public T getData(){
    return data;
  }

  /** Method to return the hash of the block.
   *
   * @return Returns the byte array containing the hash.
   */
  public byte[] getHash() {
    return hash;
  }

  /** Method to generate a hash for a genisis block.
   *
   * @return A byte array containing the block's hash.
   */
  public byte[] generateHash() {
    return Hashing.hashGenisis(index, data);
  }

  /** Method to generate a hash for a normal block.
   *
   * @return A byte array containing the block's hash.
   */
  public byte[] generateHash(byte[] prevHash) {
    return Hashing.hashBlock(index, data, prevHash);
  }
}

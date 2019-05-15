/**
* A simple blockchain implementation.
*
* @author  David Golding
* @version 1.0
* @since   2018-05-15
*/

public class Blockchain<T> {

    // An array which stores each block in the chain.
    private Block[] blockArray;
    // Byte array to store the hash of the last block.
    private byte[] lastHash;
    // Pointer to the next free location in the Block array.
    private int index = 0;

    /** Constructs the blockchain object and sets the size of the array. */
    public Blockchain() {
      blockArray = new Block[10];
    }

    /** Method to add a new block to the blockchain.
     *
     * @param data where data is an array of bytes.
     */
    public void add(T data) {
      Block block;
      // Checks if the index is 0, if so block is the Genisis block.
      if (index == 0) {
        block = new Block<T>(index, data);
      } else {
        block = new Block<T>(index, data, lastHash);
      }
      // Sets the lastHash variable which is the hash of the new block.
      lastHash = block.getHash();
      blockArray[index++] = block;
    }

    /** Method to return a block within the blockchain.
     *
     * @param index where index is the position in the array blockArray of the
     *        desired block.
     * @return The data of the block.
     */
    public T get(int index) {
      return (T)blockArray[index].getData();
    }

}

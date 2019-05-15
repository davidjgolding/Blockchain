/**
* Entry point to blockchain.
*
* @author  David Golding
* @version 1.0
* @since   2018-04-15
*/

public class App {
    public static void main(String[] args) {
      Blockchain<String> x = new Blockchain<>();
      x.add("Hello");
      x.add("World");
      System.out.println(x.get(1));
      System.out.println(x.get(0));
    }
}

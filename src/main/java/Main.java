import bst.BST;
import test.MyHashTableTest;

public class Main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(13, "A");
        tree.put(12, "B");


        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        MyHashTableTest test = new MyHashTableTest();
        test.start();

    }
}

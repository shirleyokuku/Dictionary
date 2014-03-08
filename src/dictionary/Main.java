package dictionary;

import java.io.FileNotFoundException;

public class Main {

    private static final int MAX_SIZE = 500;
    private static final int REPITITIONS = 500;

    public static void main(String[] args) throws FileNotFoundException {

        OrderedLinkedList<Integer, String> list = new OrderedLinkedList<Integer, String>();

        list.put(0, "Hello");
        System.out.println(list.get(0));

        list.put(0, "Yo");
        list.put(1, "Yolo");
        System.out.println(list.get(0) + " " + list.get(1));

    }

}

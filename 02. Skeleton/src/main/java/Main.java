import implementations.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(0);
        arr.add(1);
        arr.add(2);

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }

        arr.add(1, 1024);
        System.out.println("--------------------");

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }

    }
}
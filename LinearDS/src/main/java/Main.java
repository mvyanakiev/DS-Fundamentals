import implementations.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];

        for (int col = 0; col < size; col++) {
            String input = scanner.nextLine();
            String[] tokens = input.split("");

            for (int row = 0; row < size; row++) {
                matrix[col][row] = tokens[row];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


//        ArrayList<Integer> arr = new ArrayList<>();
//
//        arr.add(0);
//        arr.add(1);
//        arr.add(2);
//
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//        }
//
//
//
//        System.out.println("--------------------");
//
//        System.out.println("removed " + arr.remove(2));
//
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//        }
    }
}
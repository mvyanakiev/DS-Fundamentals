import interfaces.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        ArrayList<String> tst = new ArrayList<>(Arrays.asList(input));
        Collections.sort(tst);

        System.out.println(String.join(", ", tst));
    }
}




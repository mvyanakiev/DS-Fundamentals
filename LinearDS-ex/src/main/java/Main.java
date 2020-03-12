import implementations.ArrayDeque;
import implementations.BalancedParentheses;
import implementations.ReversedList;
import interfaces.RevList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {


//        String input = "{[()]}";
//         input = "асд"; // fixme return true???
//        Boolean solve = new BalancedParentheses(input).solve();
//
//        System.out.println(solve);

        RevList<String> revList = new ReversedList<>();

        revList.add("one");
        revList.add("two");
//        System.out.println(revList.size());
        revList.add("3");
        revList.add("four");
        revList.add("five");
//        System.out.println(revList.size());
//        System.out.println(revList.capacity());

//        System.out.println(revList.get(0));
//        System.out.println(revList.get(revList.size()-1));

        System.out.println("removed: " + revList.removeAt(2));

        for (String s : revList) {
            System.out.println(s);
        }

    }
}

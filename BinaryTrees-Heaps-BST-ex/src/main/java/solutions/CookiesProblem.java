package solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {

    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {

        Queue<Integer> cookies = new PriorityQueue<>();
        int count = 0;

        if (cookiesSweetness.length > 0) {
            for (int sweetness : cookiesSweetness) {
                cookies.add(sweetness);
            }
        } else {
            count = -1;
            return count;
        }

        int currentMinSweetness = cookies.peek();

        while (currentMinSweetness < requiredSweetness && cookies.size() > 1) {
            int least = cookies.poll();
            int secondLeast = cookies.poll();
            int combinedSweetness = least + 2 * secondLeast;
            cookies.add(combinedSweetness);
            currentMinSweetness = cookies.peek();
            count++;
        }

        return currentMinSweetness > requiredSweetness ? count : -1;
    }
}
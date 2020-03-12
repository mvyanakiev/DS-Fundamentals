package implementations;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testArrayDeque(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(13);
        deque.add(14);
        deque.add(15);
        deque.add(16);

//        System.out.println(deque.remove(Integer.valueOf(14)));

//        deque.remove(2);
        System.out.println(deque.remove(Integer.valueOf(55)));

        for (Integer integer : deque) {
            System.out.println(integer);
        }


    }
}
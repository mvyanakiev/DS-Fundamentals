import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTreeTest {

    @Test
    public void testCreate(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(5);

        assertEquals(Integer.valueOf(5), bst.getRoot().getValue());
    }

    @Test
    public void testInsert(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(6);
        bst.insert(1);
        bst.insert(17);

        assertEquals(Integer.valueOf(3), bst.getRoot().getLeft().getValue());
        assertEquals(Integer.valueOf(7), bst.getRoot().getRight().getValue());
        assertEquals(Integer.valueOf(6), bst.getRoot().getRight().getLeft().getValue());
    }




}

package implementations;

import interfaces.AbstractTree;
import jdk.jshell.EvalException;

import javax.swing.tree.TreeCellRenderer;
import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;


    // https://youtu.be/vvn-Vr_1F2U?t=4624
    // 1:17:15

    public Tree(E value, Tree<E>... subtrees) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();

        for (Tree<E> subtree : subtrees) {
            this.children.add(subtree);
        }

        // други варианти за създаване
//        Collections.addAll(this.children, subtrees);
//        this.children.addAll(Arrays.asList(subtrees));
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        Deque<Tree<E>> childrenQueue = new ArrayDeque<>();
        childrenQueue.offer(this);

        while (!childrenQueue.isEmpty()) {
            Tree<E> current = childrenQueue.poll();

            result.add(current.value);

            for (Tree<E> child : current.children) {
                childrenQueue.offer(child);
            }
        }
        return result;
    }

//    public List<E> orderStackDfs(){
//        List<E> result = new ArrayList<>();
//        Deque<Tree<E>> toTraverse = new ArrayDeque<>();
//        toTraverse.push(this);
//
//        while (!toTraverse.isEmpty()) {
//            Tree<E> current = toTraverse.pop();
//
//            for (Tree<E> node : current.children) {
//                toTraverse.push(node);
//            }
//            result.add(current.value);
//        }
//        return result;
//    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        this.doDfs(this, result);
        return result;
    }

    private void doDfs(Tree<E> node, List<E> result) {
        for (Tree<E> child : node.children) {
            this.doDfs(child, result);
        }
        result.add(node.value);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> search = find(parentKey);

        if (search == null){
            throw new IllegalArgumentException();
        }

        search.children.add(child);
        child.parent = search;
    }

    private Tree<E> find(E parentKey) {
        Deque<Tree<E>> childrenQueue = new ArrayDeque<>();
        childrenQueue.offer(this);

        while (!childrenQueue.isEmpty()) {
            Tree<E> current = childrenQueue.poll();

            if (current.value.equals(parentKey)){
                return current;
            }

            for (Tree<E> child : current.children) {
                childrenQueue.offer(child);
            }
        }
        return null;
        // https://youtu.be/vvn-Vr_1F2U?t=5044
    }

    @Override
    public void removeNode(E nodeKey) {

    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }
}
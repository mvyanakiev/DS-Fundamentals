package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key) {
        this.key = key;
        this.children = new ArrayList<>();
    }

    public Tree() {
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();
        traverseTreeWithRecurrence(builder, 0, this);
        return builder.toString().trim();
    }

    public List<Tree<E>> traverseWithBFS() {

        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);


        List<Tree<E>> allNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            allNodes.add(tree);

            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
        return allNodes;
    }

    private void traverseTreeWithRecurrence(StringBuilder builder, int indent, Tree<E> tree) {
        builder
                .append(this.getPadding(indent))
                .append(tree.getKey())
                .append("\r\n");

        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(builder, indent + 2, child);
        }
    }

    private String getPadding(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public List<E> getLeafKeys() {
        return traverseWithBFS()
                .stream()
                .filter(tree -> tree.children.size() == 0)
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<E> getMiddleKeys() {

        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        List<E> middleNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();

            if (tree.parent != null && tree.children.size() > 0) {
                middleNodes.add(tree.getKey());
            }

            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
        return middleNodes;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> trees = this.traverseWithBFS();
        Tree<E> deepestLeftNode = null;

        int maxPath = 0;

        for (Tree<E> tree : trees) {
            if (tree.isLeaf()) {
                int currentPath = getStepsFromLeafToRoot(tree);
                if (currentPath > maxPath) {
                    maxPath = currentPath;
                    deepestLeftNode = tree;
                }
            }
        }
        return deepestLeftNode;
    }

    private int getStepsFromLeafToRoot(Tree<E> tree) {
        int counter = 0;
        Tree<E> current = tree;

        while (current.parent != null) {
            counter++;
            current = current.parent;
        }
        return counter;
    }

    private boolean isLeaf() {
        return this.parent != null && this.children.size() == 0;
    }

    @Override
    public List<E> getLongestPath() {
        List<Tree<E>> trees = this.traverseWithBFS();
        List<E> result = new ArrayList<>();

        int maxPath = 0;

        for (Tree<E> tree : trees) {
            if (tree.isLeaf()) {
                int currentPath = getStepsFromLeafToRoot(tree);
                if (currentPath > maxPath) {
                    maxPath = currentPath;
                    result.clear();
                    result.add(tree.getKey());

                    Tree<E> current = tree;

                    while (current.parent != null) {
                        current = current.parent;
                        result.add(current.getKey());
                    }
                }
            }
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {

        List<Tree<E>> trees = this.traverseWithBFS();
        List<List<E>> result = new ArrayList<>();

        for (Tree<E> tree : trees) {
            int currentSum = 0;

            Tree<E> current = tree;

            while (current.parent != null) {
                currentSum = currentSum + (Integer) current.getKey();
                if (currentSum == sum){

                    List<E> find = new ArrayList<>();

                    Tree<E> current1 = tree;

                    while (current1.parent != null) {
                        current1 = current1.parent;
                        find.add(current1.getKey());
                    }
                    result.add(find);
                    break;
                }
                current = current.parent;
            }
        }
        return result;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }
}
package core;

import model.Message;
import shared.DataTransferSystem;

import java.util.*;

public class MessagingSystem implements DataTransferSystem {
    private List<Message> elements;

    public MessagingSystem() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(Message message) {
        if (this.elements.contains(message)) {
            throw new IllegalArgumentException();
        }

        this.elements.add(message);
        this.heapifyUp(this.size() - 1);
    }

    @Override
    public Message getByWeight(int weight) {

        for (Message element : this.elements) {
            if (element.getWeight() == weight) {
                return element;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Message getLightest() {
        List<Message> result = new ArrayList<>();

        int startIndex = 0;

        while (this.getLeft(startIndex) != null) {
            Message message = this.getLeft(startIndex);
            result.add(message);
            startIndex = this.elements.indexOf(message);
        }

        return result.get(result.size()-1);
    }

    @Override
    public Message getHeaviest() {
        if (this.elements.size() == 0) {
            throw new IllegalStateException();
        }
        return getAt(0);
    }

    @Override
    public Message deleteLightest() {

        List<Message> result = new ArrayList<>();

        int startIndex = 0;

        while (this.getLeft(startIndex) != null) {
            Message message = this.getLeft(startIndex);
            result.add(message);
            startIndex = this.elements.indexOf(message);
        }


        Message toRemove = this
                .elements
                .remove(result.size()-1);

        this.heapifyUp(0);

        return toRemove;
    }

    @Override
    public Message deleteHeaviest() {
        if (this.elements.size() == 0) {
            throw new IllegalArgumentException();
        }

        Message toRemove = this.elements.get(0);
        this.elements.remove(0);
        this.heapifyUp(this.size() - 1);
        return toRemove;
    }

    @Override
    public Boolean contains(Message message) {
        return this.elements.contains(message);
    }

    @Override
    public List<Message> getOrderedByWeight() {
//        Queue queue = new PriorityQueue();
//
//        for (Message message : elements) {
//            queue.offer(message);
//        }

        return new ArrayList<>(this.elements);
    }

    @Override
    public List<Message> getPostOrder() {


//        List<Message> result = new ArrayList<>();
//        result.add(this.elements.get(0));
//
//        int startIndex = 0;
//
//
//        while (this.getLeft(startIndex) != null) {
//            Message message = this.getLeft(startIndex);
//            result.add(message);
//            startIndex = this.elements.indexOf(message);
//        }
//
//
//        int startR = 0;
//
//        while (this.getRight(startR) != null) {
//            Message message = this.getRight(startR);
//            result.add(message);
//            startR = this.elements.indexOf(message);
//        }

        return new ArrayList<>(this.elements);

    }

    @Override
    public List<Message> getPreOrder() {
        return new ArrayList<>(this.elements);
    }

    @Override
    public List<Message> getInOrder() {
        return new ArrayList<>(this.elements);
    }

    @Override
    public int size() {
        return this.elements.size();
    }




    public List<Message> asList(){
        return new ArrayList<>(this.elements);
    }

    private void heapifyUp(int index) {
        while (index > 0 && isLess(getParentIndex(index), index)) {
            Collections.swap(this.elements, index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private boolean isLess(int childIndex, int parentIndex) {
        return getAt(childIndex).getWeight() - (getAt(parentIndex)).getWeight() < 0;
    }

    private Message getAt(int index) {
        return this.elements.get(index);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private Message getLeft(int index) {
        int leftChildIndex = (index * 2) + 1;

        if (leftChildIndex >= 0 && leftChildIndex < this.elements.size()) {
            return this.elements.get(leftChildIndex);
        }
        return null;
    }

    private Message getRight(int index) {
        int rightChildIndex = (index * 2) + 2;

        if (rightChildIndex >= 0 && rightChildIndex < this.elements.size()) {
            return this.elements.get(rightChildIndex);
        }
        return null;
    }


}

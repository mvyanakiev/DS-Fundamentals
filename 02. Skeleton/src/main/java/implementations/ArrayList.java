package implementations;

import interfaces.List;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private final static int INITIAL_SIZE = 4;
    private Object[] elements;
    private int size;
    private int capacity;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        this.size = 0;
        this.capacity = INITIAL_SIZE;
    }

    @Override
    public boolean add(E element) {

        if (this.size == this.capacity) {
            this.grow();
        }

        this.elements[this.size] = element;
        this.size++;

        return true;
    }

    @Override
    public boolean add(int index, E element) {

        if (!validIndex(index)) {
            return false;
        }

        shiftRight(index);
        this.elements[index] = element;
        this.size++;

        return true;
    }

    @Override
    public E get(int index) {

        if (!validIndex(index)) {
            throw new IndexOutOfBoundsException("Can not get index " + index + " on array with size " + this.size);
        }

        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private void grow() {
        this.capacity *= 2;
        this.elements = Arrays.copyOf(this.elements, this.capacity);
    }

    private void shiftRight(int index) {
        for (int i = this.size-1; i > index-1; i--) {
            this.elements[i + 1] = this.elements[i];
        }
    }

    private boolean validIndex(int index) {
        return index < this.size && index >= 0;
    }
}
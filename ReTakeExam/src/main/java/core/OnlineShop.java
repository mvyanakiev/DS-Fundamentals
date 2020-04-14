package core;

import model.Order;
import shared.Shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineShop implements Shop {
    private List<Order> data;

    public OnlineShop() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(Order order) {
        this.data.add(order);
    }

    @Override
    public Order get(int index) {
        validateIndex(index);
        return this.data.get(index);
    }

    @Override
    public int indexOf(Order order) {
        return this.data.indexOf(order);
    }

    @Override
    public Boolean contains(Order order) {
        return this.data.contains(order);
    }

    @Override
    public Boolean remove(Order order) {
        int index = this.data.indexOf(order);

        if (index >= 0) {
            this.data.remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean remove(int id) {

        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getId() == id) {
                this.data.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void set(int index, Order order) {
        validateIndex(index);

        this.data.set(index, order);
    }

    @Override
    public void set(Order oldOrder, Order newOrder) {
        validateIndex(oldOrder.getId());

        this.data.set(oldOrder.getId(), newOrder);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public Order[] toArray() {

        if(this.data.size() == 0){
            return new Order[0];
        } else {
            return this.data.toArray(new Order[0]);
        }
    }

    @Override
    public void swap(Order first, Order second) {
        int index1 = this.data.indexOf(first);
        int index2 = this.data.indexOf(second);

        if (index1 >= 0 && index2 >= 0) {
            Collections.swap(this.data, index1, index2);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Order> toList() {
        return new ArrayList<>(this.data);
    }

    @Override
    public void reverse() {
        Collections.reverse(this.data);
    }

    @Override
    public void insert(int index, Order order) {
        validateIndex(index);

        this.data.add(index, order);
    }

    @Override
    public Boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public int size() {
        return this.data.size();
    }

    private void validateIndex(int index) {
        if (index < 0 || index > this.data.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
    }
}

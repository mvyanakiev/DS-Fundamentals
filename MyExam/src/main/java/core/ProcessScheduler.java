package core;

import model.Task;
import shared.Scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessScheduler implements Scheduler {
    private List<Task> data;

    public ProcessScheduler() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        this.data.add(task);
    }

    @Override
    public Task process() {
        if (this.data.size() > 0) {
            return this.data.remove(0);
        } else {
            return null;
        }
    }

    @Override
    public Task peek() {
        if (this.data.size() > 0) {
            return this.data.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Boolean contains(Task task) {
        return this.data.contains(task);
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public Boolean remove(Task task) {
        int index = this.data.indexOf(task);

        if (index >= 0) {
            this.data.remove(index);
            return true;
        } else {
            throw new IllegalArgumentException();
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
        throw new IllegalArgumentException();
    }

    @Override
    public void insertBefore(int id, Task task) {

        boolean isInserted = false;

        for (int i = 0; i < this.data.size(); i++) {
            if(this.data.get(i).getId() == id){
                this.data.add(i, task);
                isInserted = true;
                break;
            }
        }

        if(!isInserted) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void insertAfter(int id, Task task) {

        boolean isInserted = false;

        for (int i = 0; i < this.data.size(); i++) {
            if(this.data.get(i).getId() == id){
                this.data.add(i+1, task);
                isInserted = true;
                break;
            }
        }

        if(!isInserted) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public Task[] toArray() {
        return this.data.toArray(new Task[0]);
    }

    @Override
    public void reschedule(Task first, Task second) {
        int index1 = this.data.indexOf(first);
        int index2 = this.data.indexOf(second);

        if (index1 >= 0 && index2 >= 0) {
            Collections.swap(this.data, index1, index2);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Task> toList() {
        return new ArrayList<>(this.data);
    }

    @Override
    public void reverse() {
        Collections.reverse(this.data);
    }

    @Override
    public Task find(int id) {

        for (Task task : this.data) {
            if (task.getId() == id) {
                return task;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Task find(Task task) {
        int index = this.data.indexOf(task);

        if (index >= 0) {
            return this.data.get(index);
        } else {
            throw new IllegalArgumentException();
        }
    }
}

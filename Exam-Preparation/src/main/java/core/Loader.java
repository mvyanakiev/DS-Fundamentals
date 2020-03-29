package core;

import interfaces.Buffer;
import interfaces.Entity;
import model.BaseEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Loader implements Buffer {

    private List<Entity> data;

    public Loader() {
        this.data = new ArrayList<>();
    }

    @Override
    public void add(Entity entity) {
        this.data.add(entity);
    }

    @Override
    public Entity extract(int id) {
        Entity result = null;

        for (Entity current : this.data) {
            if (current.getId() == id) {
                result = current;
                this.data.remove(current);
                break;
            }
        }
        return result;
    }

    @Override
    public Entity find(Entity entity) {
//        Entity result = null;
//        for (Entity current : this.data) {
//            if(current.equals(entity)){
//                result = current;
//                break;
//            }
//        }
//        return result;

        int index = this.data.indexOf(entity);

        return index == -1 ? null : this.data.get(index);
    }

    @Override
    public boolean contains(Entity entity) {
        return this.data.contains(entity);
    }

    @Override
    public int entitiesCount() {
        return this.data.size();
    }

    @Override
    public void replace(Entity oldEntity, Entity newEntity) {
//        this.data.add(this.data.indexOf(oldEntity), newEntity);

        int index = this.data.indexOf(oldEntity);
        ensureIndex(index);
        this.data.set(index, newEntity);
    }

    @Override
    public void swap(Entity first, Entity second) {

//        int firstIndex = -1;
//        int secondIndex = -1;
//
//        int size = this.entitiesCount();
//        for (int i = 0; i < size; i++) {
//            if (this.data.get(i).getId() == first.getId()) {
//                firstIndex = i;
//            }
//            if (this.data.get(i).getId() == second.getId()) {
//                secondIndex = i;
//            }
//        }

        int firstIndex = this.data.indexOf(first);
        int secondIndex = this.data.indexOf(second);

        ensureIndex(firstIndex);
        ensureIndex(secondIndex);

        this.data.set(firstIndex, second);
        this.data.set(secondIndex, first);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public Entity[] toArray() {
        int size = this.entitiesCount();
        Entity[] result = new Entity[size];

//        for (int i = 0; i < size; i++) {
//            result[i] = this.data.get(i);
//        }

        this.data.toArray(result);
        return result;
    }

    @Override
    public List<Entity> retainAllFromTo(BaseEntity.Status lowerBound, BaseEntity.Status upperBound) {
        List<Entity> result = new ArrayList<>();

//        int lowerStatus = lowerBound.ordinal();
//        int upperStatus = upperBound.ordinal();
//
//        for (Entity entity : data) {
//            if(entity.getStatus().ordinal() >= lowerStatus && entity.getStatus().ordinal() <= upperStatus){
//                result.add(entity);
//            }
//        }

        result = this.data
                .stream()
                .filter(e -> e.getStatus().ordinal() >= lowerBound.ordinal()
                        && e.getStatus().ordinal() <= upperBound.ordinal())
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Entity> getAll() {
//        return Collections.unmodifiableList(this.data);
        return new ArrayList<>(this.data);
    }

    @Override
    public void updateAll(BaseEntity.Status oldStatus, BaseEntity.Status newStatus) {

        for (Entity entity : data) {
            if (entity.getStatus().equals(oldStatus)) {
                entity.setStatus(newStatus);
            }
        }
    }

    @Override
    public void removeSold() {

//        this.data.removeIf(entity -> entity.getStatus().equals(BaseEntity.Status.SOLD));

        this.data = this.data
                .stream()
                .filter(e -> e.getStatus().equals(BaseEntity.Status.SOLD))
                .collect(Collectors.toList());
    }


    @Override
    public Iterator<Entity> iterator() {

//        return new Iterator<Entity>() {
//            int i = 0;
//
//            @Override
//            public boolean hasNext() {
//                return i < data.size();
//            }
//
//            @Override
//            public Entity next() {
//                return data.get(i++);
//            }
//        };

        return this.data.iterator();
    }

//    public boolean ensureNonEmpty() {
//        return this.data.size() > 0;
//    }

    private void ensureIndex(int index) {
        if (index == -1) {
            throw new IllegalStateException("Entity not found!");
        }
    }
}

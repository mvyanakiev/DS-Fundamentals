package core;

import model.File;
import model.SampleFile;
import shared.FileManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FileExplorer implements FileManager {
    private File root;
    private ArrayDeque<File> clipboard;

    public FileExplorer() {
        this.root = new SampleFile(1, "Root");
        this.clipboard = new ArrayDeque();
    }

    @Override
    public void addInDirectory(int directorNumber, File file) {

        boolean isAdded = false;
        Deque<File> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            File fileToCheck = queue.poll();

            if (fileToCheck.getNumber() == directorNumber) {
                fileToCheck.getChildren().add(file);
                isAdded = true;
                break;
            }

            for (File fileChild : fileToCheck.getChildren()) {
                queue.offer(fileChild);
            }
        }

        if (!isAdded) {
            throw new IllegalStateException();
        }
    }

    @Override
    public File getRoot() {
        return this.root;
    }

    @Override
    public File get(int number) {
        File toReturn = null;

        Deque<File> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            File fileToCheck = queue.poll();

            if (fileToCheck.getNumber() == number) {
                toReturn = fileToCheck;
                break;
            }

            for (File child : fileToCheck.getChildren()) {
                queue.offer(child);
            }
        }

        if (toReturn == null) {
            throw new IllegalStateException();
        } else {
            return toReturn;
        }
    }

    @Override
    public Boolean deleteFile(File file) {

        if (!this.contains(file)) {
            return false;
        }

        File current = this.root;
        File parent = null;

        if (file.getNumber() == 1) {
            this.root.getChildren().clear();
            this.root = null;
            return true;
        }

        Deque<File> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {

            parent = current;
            current = queue.poll();

            for (File child : current.getChildren()) {
                if (current.getNumber() == file.getNumber()) {

                    current.getChildren().clear();

                    if (parent != null) {
                        parent.getChildren().remove(current);
                    }
                    current = null;
                    return true;
                }

                queue.offer(child);
            }
        }
        return false;
    }

    @Override
    public List<File> getFilesInPath(File path) {

        if (!this.contains(path)) {
            throw new IllegalStateException();
        }

        List<File> result = new ArrayList<>();
        List<File> allFiles = this.getInLevel();

        for (File file : allFiles) {
            if (path.equals(file)) {

                for (File child : path.getChildren()) {
                    result.add(child);
                }

//                result.addAll(path.getChildren());
            }
        }

        return result;
    }

    @Override
    public void move(File file, File destination) { //todo Test it

        if (file.getNumber() == 1 || !this.contains(file)) {
            throw new IllegalStateException();
        }

        File current = this.root;
        File parent = null;

        Deque<File> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            parent = current;
            current = queue.poll();

            if (current.getNumber() == file.getNumber()) {
                break;
            }

            for (File child : current.getChildren()) {
                queue.offer(child);
            }
        }

        if (parent.getChildren().size() > 0) {
            parent.getChildren().remove(file);
        }

        List<File> children = destination.getChildren();
        children.add(file);
        destination.setGetChildren(children);
    }

    @Override
    public Boolean contains(File file) {
        return this.getInLevel().contains(file);
    }

    @Override
    public List<File> getInDepth() {
        List<File> result = new ArrayList<>();
        File file = this.root;
        result.add(file);
        doDFS(file, result);
        return result;
    }

    private void doDFS(File file, List<File> result) {
        for (File child : file.getChildren()) {
            result.add(child);
            doDFS(child, result);
        }
    }

    @Override
    public List<File> getInLevel() {
        List<File> result = new ArrayList<>();
        Deque<File> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            File file = queue.poll();
            result.add(file);

            for (File fileChild : file.getChildren()) {
                queue.offer(fileChild);
            }
        }
        return result;
    }

    @Override
    public void cut(int number) {
        File fileToCut = this.get(number);

        if(fileToCut != null){
            this.clipboard.push(fileToCut);
            this.deleteFile(fileToCut);
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void paste(File destination) {

        if (!this.contains(destination)) {
            throw new IllegalStateException();
        }

        if (!this.clipboard.isEmpty()) {
            destination.getChildren().add(this.clipboard.pop());
        }
    }

    @Override
    public Boolean isEmpty() {
        return this.root.getChildren().isEmpty();
    }

    @Override
    public String getAsString() {
        if (this.root == null) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        print(this.root, buffer, "", "");
        return buffer.toString().trim();
    }

    private void print(File file, StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(file.getNumber());
        buffer.append(System.lineSeparator());
        List<File> children = file.getChildren();
        for (int i = 0; i < children.size(); i++) {
            File next = children.get(i);
            if (i < children.size() - 1) {
                print(next, buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                print(next, buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
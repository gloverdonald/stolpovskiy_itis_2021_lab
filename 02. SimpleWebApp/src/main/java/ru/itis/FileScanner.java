package ru.itis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileScanner {
    int n = 0;
    List<String> filesName = new ArrayList<>();
    List<File> files = new ArrayList<>();
    String pathName;

    public FileScanner(String pathName) {
        this.pathName = pathName;
        File folder = new File(pathName);
        for (File file : (Objects.requireNonNull(folder.listFiles()))) {
            files.add(file);
            filesName.add(file.getName().replaceFirst("[.][^.]+$", ""));
        }
        n = files.size();
    }

    public List<String> getFilesName() {
        return filesName;
    }

    public List<File> getFiles() {
        return files;
    }

    public int amount() {
        return n;
    }
}

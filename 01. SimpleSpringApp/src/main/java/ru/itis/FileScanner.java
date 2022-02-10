package ru.itis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@PropertySource("classpath:files.properties")
public class FileScanner {
    int n = 0;
    List<String> filesName = new ArrayList<>();
    List<File> files = new ArrayList<>();
    @Value("${file.path}")
    String pathName;

    @PostConstruct
    public void init() {
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

package ru.itis.repositories;

import java.util.List;
import java.util.TreeMap;

public interface WordRepository {
    void dropAndCreate();

    void insert(String path, String word, Integer amount);

    TreeMap<String, Integer> topWords(String tableName);
}

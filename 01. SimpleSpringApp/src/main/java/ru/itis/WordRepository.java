package ru.itis;

import java.util.List;
import java.util.TreeMap;

public interface WordRepository {
    void createTable(String tableName);
    void insertOrUpdate(String word,String tableName);
    void insert(String tableName, String word, Integer amount);
    TreeMap<String, Integer> topWords(String tableName);
}

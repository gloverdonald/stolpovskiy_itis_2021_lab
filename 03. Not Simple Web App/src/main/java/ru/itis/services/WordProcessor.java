package ru.itis.services;

import org.springframework.stereotype.Component;
import ru.itis.repositories.WordRepositoryJdbcTemplateImpl;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WordProcessor {
    private final WordRepositoryJdbcTemplateImpl wordRepositoryJdbcTemplateImpl;

    public WordProcessor(WordRepositoryJdbcTemplateImpl wordRepositoryJdbcTemplateImpl) {
        this.wordRepositoryJdbcTemplateImpl = wordRepositoryJdbcTemplateImpl;
    }

    public TreeMap<String, Integer> getTopTenInTable(String path) {
        return wordRepositoryJdbcTemplateImpl.topWords(path);
    }

    public void getInfo(FileScanner fileScanner) {
        String ans = "";
        List<File> files = fileScanner.getFiles();
        List<String> filesName = fileScanner.getFilesName();
        Pattern ptrn = Pattern.compile("([а-яА-Я]+(-[а-яА-Я]+)*)|([a-zA-Z]+(-[a-zA-Z]+)*)");
        wordRepositoryJdbcTemplateImpl.dropAndCreate();
        for (int i = 0; i < fileScanner.amount(); i++) {
            try {
                FileInputStream fstream = new FileInputStream(files.get(i));
                String fileName = filesName.get(i);
                File file = files.get(i);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                HashMap<String, Integer> map = new HashMap<>();
                while ((strLine = br.readLine()) != null) {
                    Matcher matcher2 = ptrn.matcher(strLine);
                    while (matcher2.find()) {
                        String word = matcher2.group(0).toLowerCase();
                        if (!map.containsKey(word)) {
                            map.put(word, 1);
                        } else {
                            map.put(word, map.get(word) + 1);
                        }
                    }
                }
                for (String s : map.keySet()) {
                    String path = "" + file;
                    wordRepositoryJdbcTemplateImpl.insert(path, s, map.get(s));
                }
            } catch (Exception e) {
                ans = ans + "Error: " + e.getMessage();
                System.err.println("Error: " + e.getMessage());
            }
        }

    }
}

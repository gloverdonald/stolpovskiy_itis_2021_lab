package ru.itis;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WordProcessor {
    private final WordRepositoryJdbcTemplateImpl wordRepositoryJdbcTemplateImpl;
    private final FileScanner fileScanner;

    public WordProcessor(WordRepositoryJdbcTemplateImpl wordRepositoryJdbcTemplateImpl, FileScanner fileScanner) {
        this.wordRepositoryJdbcTemplateImpl = wordRepositoryJdbcTemplateImpl;
        this.fileScanner = fileScanner;
    }

    public void getInfo() {
        List<File> files = fileScanner.getFiles();
        List<String> filesName = fileScanner.getFilesName();

        Pattern ptrn = Pattern.compile("([а-яА-Я]+(-[а-яА-Я]+)*)|([a-zA-Z]+(-[a-zA-Z]+)*)");
        for (int i = 0; i < fileScanner.amount(); i++) {
            try {
                FileInputStream fstream = new FileInputStream(files.get(i));
                String fileName = filesName.get(i);
                File file = files.get(i);
                wordRepositoryJdbcTemplateImpl.createTable(fileName);
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
                    wordRepositoryJdbcTemplateImpl.insert(fileName, s, map.get(s));
                }
                TreeMap<String, Integer> top_words = wordRepositoryJdbcTemplateImpl.topWords(fileName);
                System.out.println("File name: " + file.getAbsolutePath());
                System.out.println("\nTop 10 words: ");
                for (String s : top_words.keySet()) {
                    System.out.println(s + ": " + top_words.get(s));
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

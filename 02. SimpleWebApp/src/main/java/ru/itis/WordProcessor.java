package ru.itis;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WordProcessor {
    private final FileScanner fileScanner;

    public WordProcessor(FileScanner fileScanner) {
        this.fileScanner = fileScanner;
    }

    public String getInfo() {
        String words = "<table>";
        List<File> files = fileScanner.getFiles();
        List<String> filesName = fileScanner.getFilesName();
        Pattern ptrn = Pattern.compile("([а-яА-Я]+(-[а-яА-Я]+)*)|([a-zA-Z]+(-[a-zA-Z]+)*)");
        for (int i = 0; i < fileScanner.amount(); i++) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(files.get(i), StandardCharsets.UTF_8));
                String strLine;
                TreeMap<String, Integer> map = new TreeMap<>();
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
                words = words + "<tr><td> <h3>" + filesName.get(i) + "</h3></td></tr>";
                for (String s : map.keySet()) {
                    words = words + "<tr><td>" + s + " </td><td>" + map.get(s) + "</td></tr>";
                }

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        words = words + "</table>";
        return words;
    }

}

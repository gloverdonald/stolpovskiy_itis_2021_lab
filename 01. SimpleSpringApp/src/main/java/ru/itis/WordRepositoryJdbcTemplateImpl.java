package ru.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component()
public class WordRepositoryJdbcTemplateImpl implements WordRepository {
    private JdbcTemplate jdbcTemplate = null;

    @Autowired
    public WordRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createTable(String tableName) {
        jdbcTemplate.execute("drop table if exists " + tableName + "; CREATE TABLE " + tableName + " (id serial, word varchar(128) unique, amount integer default 1);");
    }

    @Override
    public void insertOrUpdate(String tableName, String word) {
        jdbcTemplate.execute("insert into " + tableName + " (word) values('" + word + "') ON CONFLICT (word) DO UPDATE SET amount=" + tableName + ".amount  + 1;");
    }

    @Override
    public void insert(String tableName, String word, Integer amount) {
        jdbcTemplate.execute("insert into " + tableName + " (word, amount) values('" + word + "', " + amount + ") ;");
    }

    private final ResultSetExtractor<TreeMap<String, Integer>> listResultSetExtractor = resultSet -> {
        TreeMap<String, Integer> result = new TreeMap<>();
        while (resultSet.next()) {
            result.put(resultSet.getString("word"), resultSet.getInt("amount"));
        }
        return result;
    };

    @Override
    public TreeMap<String, Integer> topWords(String tableName) {
        return jdbcTemplate.query("SELECT word, amount FROM " + tableName + " ORDER BY amount DESC LIMIT 10;", listResultSetExtractor);
    }
}

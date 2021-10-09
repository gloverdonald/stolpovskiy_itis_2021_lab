package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import ru.itis.repositories.WordRepository;

import javax.sql.DataSource;
import java.util.TreeMap;

@Repository
public class WordRepositoryJdbcTemplateImpl implements WordRepository {
    private JdbcTemplate jdbcTemplate = null;

    public static final String SQL_DROP_AND_CREATE = "drop table if exists words; create table words (path varchar(512),word varchar(100),amount integer default 1);";
    public static final String SQL_INSERT = "insert into words values(?, ?, ?)";
    public static final String SQL_TOP_WORDS_IN_FILE = "SELECT word, amount FROM words WHERE path = ? ORDER BY amount DESC LIMIT 10;";

    @Autowired
    public WordRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void dropAndCreate() {
        jdbcTemplate.update(SQL_DROP_AND_CREATE);
    }

    @Override
    public void insert(String path, String word, Integer amount) {
        jdbcTemplate.update(SQL_INSERT, path, word, amount);
    }

    private final ResultSetExtractor<TreeMap<String, Integer>> listResultSetExtractor = resultSet -> {
        TreeMap<String, Integer> result = new TreeMap<>();
        while (resultSet.next()) {
            result.put(resultSet.getString("word"), resultSet.getInt("amount"));
        }
        return result;
    };

    @Override
    public TreeMap<String, Integer> topWords(String path) {
        return jdbcTemplate.query(SQL_TOP_WORDS_IN_FILE, listResultSetExtractor, path);
    }
}

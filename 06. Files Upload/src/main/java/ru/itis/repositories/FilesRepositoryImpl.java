package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.model.FileInfo;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class FilesRepositoryImpl implements FilesRepository {
    private final static String SQL_SELECT_BY_ID = "select * from files_info where storage_file_name = ?";
    private final static String SQL_INSERT = "insert into files_info(storage_file_name, original_file_name, size, type) values (?, ?, ?, ?)";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<FileInfo> rowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .storageFileName(row.getString("storage_file_name"))
                    .originalFileName(row.getString("original_file_name"))
                    .type(row.getString("type"))
                    .size(row.getLong("size"))
                    .build();

    public FilesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addFileInfo(FileInfo fileInfo) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setString(1, fileInfo.getStorageFileName());
            statement.setString(2, fileInfo.getOriginalFileName());
            statement.setLong(3, fileInfo.getSize());
            statement.setString(4, fileInfo.getType());
            return statement;
        });
    }


    @Override
    public Optional<FileInfo> getFileInfo(String storage_file_name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, storage_file_name));
        } catch (NoSuchElementException elementException) {
            return Optional.empty();
        }
    }
}

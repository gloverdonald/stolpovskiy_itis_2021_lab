package ru.itis.repositories;

import ru.itis.model.FileInfo;

import java.util.Optional;

public interface FilesRepository {
    void addFileInfo(FileInfo fileInfo);

    Optional<FileInfo> getFileInfo(String storage_file_name);
}

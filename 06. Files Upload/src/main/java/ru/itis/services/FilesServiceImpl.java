package ru.itis.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.repositories.FilesRepository;
import ru.itis.model.FileInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${storage.path}")
    private String storagePath;
    private final FilesRepository filesRepository;

    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void saveFileInfo(InputStream fileInputStream, String originalFileName, String contentType, Long size) {
        String storage_file_name = UUID.randomUUID().toString();
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                storage_file_name,
                size,
                contentType
        );
        try {
            Files.copy(fileInputStream, Paths.get(storagePath + "/" + storage_file_name));
            filesRepository.addFileInfo(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public FileInfo readFileInfo(String fileName) {
        return filesRepository.getFileInfo(fileName).get();
    }

    @Override
    public void downloadFileInfo(String fileName, OutputStream outputStream) {
        try {
            Files.copy(Paths.get(storagePath + "/" + fileName), outputStream);
            filesRepository.getFileInfo(fileName).get();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

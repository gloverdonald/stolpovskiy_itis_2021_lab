package ru.itis.services;

import ru.itis.model.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FilesService {
    void saveFileInfo(InputStream fileInputStream, String originalFileName, String contentType, Long size);
    FileInfo readFileInfo(String fileName);
    void downloadFileInfo(String fileName, OutputStream responseOutputStream);
}

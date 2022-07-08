package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.repository.FileInfoRepository;
import ru.itis.exceptions.FileNotFoundException;
import ru.itis.model.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;


@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {

    @Value("${files.storage.path}")
    private String storagePath;

    private final FileInfoRepository filesInfoRepository;

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = filesInfoRepository.findByStorageFileName(fileName).orElseThrow(FileNotFoundException::new);
        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalFileName() + "\"");
        response.setHeader("File-Description", file.getDescription());
        try {
            IOUtils.copy(new FileInputStream(storagePath + "/" + file.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

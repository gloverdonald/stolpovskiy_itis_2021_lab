package ru.itis.service;

import javax.servlet.http.HttpServletResponse;

public interface FilesService {
    void addFileToResponse(String fileName, HttpServletResponse response);
}

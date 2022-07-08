package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.services.FilesService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/files")
public class FilesController {

    private final FilesService filesService;

    @GetMapping
    public String getFilesUploadPage() {
        return "file_upload_page";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile[] multipartFiles,
                         @RequestParam("description") String description) {
        for (MultipartFile multipartFile : multipartFiles) {
            System.out.println(multipartFile);
        }
        filesService.upload(multipartFiles, description);
        return "redirect:/files";
    }
}

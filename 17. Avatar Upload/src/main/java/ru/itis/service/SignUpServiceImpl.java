package ru.itis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.SignUpDto;
import ru.itis.model.Account;
import ru.itis.model.FileInfo;
import ru.itis.repository.AccountRepository;
import ru.itis.repository.FileInfoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    @Value("${files.storage.path}")
    private String storagePath;

    private final AccountRepository accountRepository;
    private final FileInfoRepository fileInfoRepository;

    @Override
    public Long signUp(SignUpDto signUpDto) {
        FileInfo file = null;

        MultipartFile multipart = signUpDto.getMultipartFile();
        if (!multipart.isEmpty()) {
            try {
                String extension = multipart.getOriginalFilename()
                        .substring(multipart.getOriginalFilename().lastIndexOf("."));
                String storageFileName = UUID.randomUUID() + extension;
                file = FileInfo.builder()
                        .type(multipart.getContentType())
                        .originalFileName(multipart.getOriginalFilename())
                        .description("user avatar")
                        .storageFileName(storageFileName)
                        .size(multipart.getSize())
                        .build();
                fileInfoRepository.save(file);
                Files.copy(multipart.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        Account account = Account.builder()
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .avatar(file)
                .build();
        return accountRepository.save(account).getId();
    }
}

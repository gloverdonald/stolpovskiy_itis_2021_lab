package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.FileInfo;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findByStorageFileName(String fileName);
}

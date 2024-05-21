package com.example.gestionlocauxcommerciauxmvc.Property.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface fileRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String fileName);
}

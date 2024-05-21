package com.example.gestionlocauxcommerciauxmvc.Property.Image;

import com.example.gestionlocauxcommerciauxmvc.Property.File.fileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.gestionlocauxcommerciauxmvc.Property.File.FileData;

import java.util.Optional;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;

@Service
public class StorageService {

    @Autowired
    private imageRepository imageRepository;

    @Autowired
    private fileRepository fileDataRepository;

    private final String FOLDER_PATH="/Users/dell/Desktop/Images/";

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }



    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = imageRepository.findByName(fileName);
        byte[] image = ImageUtils.decompressImage(dbImageData.get().getData());
        return image;
    }


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());
        file.transferTo(new File(filePath));
        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}

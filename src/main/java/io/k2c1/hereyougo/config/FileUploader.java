package io.k2c1.hereyougo.config;

import io.k2c1.hereyougo.domain.Image;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUploader {

    private final ImageRepository imageRepository;

    @Value("${file.dir}")
    private String fileDir;

    public Image uploadFile(MultipartFile multipartFile, Post post) throws IOException {
        if(multipartFile.isEmpty()) return null;

        String orignalFilename = multipartFile.getOriginalFilename();
        String storedFilename = createStoredFilename(orignalFilename);
        multipartFile.transferTo(new File(getFullPath(storedFilename)));

        Image image = new Image(storedFilename, orignalFilename, post);
        imageRepository.save(image);

        return image;
    }

    public List<Image> uploadFiles(List<MultipartFile> multipartFiles, Post post) throws IOException {
        List<Image> files = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles)
            if(!multipartFile.isEmpty())
                files.add(uploadFile(multipartFile, post));

        return files;
    }

    public String createStoredFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String extractedFilename = getExtractedFilename(originalFilename);
        return uuid + "." + extractedFilename;
    }

    private String getExtractedFilename(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    private String getFullPath(String filename) {
        return fileDir + filename;
    }
}

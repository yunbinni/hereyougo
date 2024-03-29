package io.k2c1.hereyougo.config;

import io.k2c1.hereyougo.domain.Image;
import io.k2c1.hereyougo.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUploader {

    @Value("${file.dir}")
    private String fileDir;

    public Image uploadFile(MultipartFile multipartFile, Post post) throws IOException {
        if(multipartFile.isEmpty()) return null;

        String orignalFilename = multipartFile.getOriginalFilename();
        String storedFilename = createStoredFilename(orignalFilename);
        multipartFile.transferTo(new File(getFullPath(storedFilename)));

        return new Image(storedFilename, orignalFilename, post);
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

    public void removeImage(Image image) throws IOException {
        String imgUrl= fileDir + image.getStoredFilename();
        Path filePath = Paths.get(imgUrl);

        System.out.println("파일 이미지 경로" + imgUrl);

        Files.delete(filePath);
    }
}

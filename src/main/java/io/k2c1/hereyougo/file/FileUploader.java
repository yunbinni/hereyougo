package io.k2c1.hereyougo.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUploader {

    @Value("${file.dir}")
    private String fileDir;

    public UploadFile uploadFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) return null;

        String orignalFilename = multipartFile.getOriginalFilename();
        String storedFilename = createStoredFilename(orignalFilename);
        multipartFile.transferTo(new File(getFullPath(storedFilename)));

        return new UploadFile(orignalFilename, storedFilename);
    }

    public List<UploadFile> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> files = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles)
            if(!multipartFile.isEmpty())
                files.add(uploadFile(multipartFile));

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

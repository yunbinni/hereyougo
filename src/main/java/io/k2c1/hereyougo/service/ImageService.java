package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.config.FileUploader;
import io.k2c1.hereyougo.domain.Image;
import io.k2c1.hereyougo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final FileUploader fileUploader;

    public void removeImageFile(List<Image> images) throws IOException {
        try{
            for(Image image : images){
                fileUploader.removeImage(image);
                imageRepository.deleteByStoredFilename(image.getStoredFilename());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

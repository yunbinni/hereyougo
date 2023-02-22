package io.k2c1.hereyougo.file;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UploadFile {

    private final String originalFilename;
    private final String storedFilename;
}

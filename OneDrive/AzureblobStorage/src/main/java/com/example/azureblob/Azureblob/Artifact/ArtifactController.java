package com.example.azureblob.Azureblob.Artifact;

import com.example.azureblob.Azureblob.Client.ImageStorageClient;
import com.example.azureblob.Azureblob.Exception.Custom.Result.Result;
import com.example.azureblob.Azureblob.Exception.Custom.Result.StatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/artifacts")
public class ArtifactController {

    private final ImageStorageClient imageStorageClient;

    public ArtifactController(ImageStorageClient imageStorageClient) {
        this.imageStorageClient = imageStorageClient;
    }

    @PostMapping("/images")
    public Result uploadImage(@RequestParam String containerName, @RequestParam MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            String imageUrl = this.imageStorageClient.uploadImage(containerName, file.getOriginalFilename(), inputStream, file.getSize());
            return new Result(true, StatusCode.SUCCESS, "Upload Image Success", imageUrl);
        }
    }
}

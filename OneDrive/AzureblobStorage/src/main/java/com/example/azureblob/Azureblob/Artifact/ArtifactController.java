package com.example.azureblob.Azureblob.Artifact;

import com.example.azureblob.Azureblob.Client.ImageStorageClient;
import com.example.azureblob.Azureblob.Exception.Custom.Result.Result;
import com.example.azureblob.Azureblob.Exception.Custom.Result.StatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/artifacts")
public class ArtifactController {

    private final ImageStorageClient imageStorageClient;

    private final ArtifactService artifactService;

    public ArtifactController(ImageStorageClient imageStorageClient, ArtifactService artifactService) {
        this.imageStorageClient = imageStorageClient;
        this.artifactService = artifactService;
    }

    @GetMapping("/{artifactId}")
    public Result findArifactById(@PathVariable String artifactId) {
        Artifact byId = this.artifactService.findById(artifactId);
        return new Result(true,StatusCode.SUCCESS,"Keerthi",byId);
    }

    @PostMapping("/images")
    public Result uploadImage(@RequestParam String containerName, @RequestParam MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            String imageUrl = this.imageStorageClient.uploadImage(containerName, file.getOriginalFilename(), inputStream, file.getSize());
            return new Result(true, StatusCode.SUCCESS, "Upload Image Success", imageUrl);
        }
    }
}

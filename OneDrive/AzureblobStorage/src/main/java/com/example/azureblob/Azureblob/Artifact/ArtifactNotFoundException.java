package com.example.azureblob.Azureblob.Artifact;

public class ArtifactNotFoundException extends RuntimeException {
    public ArtifactNotFoundException(String id) {
        super("Could not find arifact with this " + id);
    }
}

package com.example.azureblob.Azureblob.Exception.Custom;

public class CustomBlobStorageException extends RuntimeException {
    public CustomBlobStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

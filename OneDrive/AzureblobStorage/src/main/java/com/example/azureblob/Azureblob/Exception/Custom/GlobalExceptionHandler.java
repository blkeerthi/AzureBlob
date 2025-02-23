package com.example.azureblob.Azureblob.Exception.Custom;

import com.example.azureblob.Azureblob.Artifact.ArtifactNotFoundException;
import com.example.azureblob.Azureblob.Exception.Custom.Result.Result;
import com.example.azureblob.Azureblob.Exception.Custom.Result.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArtifactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleArtifactNotFoundException(ArtifactNotFoundException ex){
        return new Result(false, StatusCode.NOT_FOUND,ex.getMessage());
    }
}


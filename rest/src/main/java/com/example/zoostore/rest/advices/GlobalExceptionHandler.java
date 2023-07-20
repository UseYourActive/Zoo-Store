package com.example.zoostore.rest.advices;

import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handleRestClientException(RestClientException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occurred during API call! " + e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That was an invalid request input! " + e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleItemNotFoundInRepoException(ItemNotFoundInRepositoryException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No item was found in the repository! " + e.getMessage());
    }

    @ExceptionHandler(MultimediaNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleMultimediaNotFoundInRepoException(MultimediaNotFoundInRepositoryException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No multimedia was found in the repository! " + e.getMessage());
    }

    @ExceptionHandler(TagNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleTagNotFoundInRepoException(TagNotFoundInRepositoryException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tag was found in the repository! " + e.getMessage());
    }

    @ExceptionHandler(VendorNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleVendorNotFoundInRepoException(VendorNotFoundInRepositoryException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vendor was found in the repository! " + e.getMessage());
    }
}

package org.alghom.deathstar.exposure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FalconRestController {
    ResponseEntity<String> getTheOdd(MultipartFile file);
}

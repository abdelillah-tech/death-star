package org.alghom.deathstar.exposure.controllers;

import org.alghom.deathstar.domain.services.FalconService;
import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;


@RestController
public class FalconRestControllerImpl implements FalconRestController {
    private final FalconService falconService;

    public FalconRestControllerImpl(FalconService falconService) {
        this.falconService = falconService;
    }

    @Override
    @PostMapping(value = "/give-me-the-odds", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> getTheOdd(@RequestParam("file") MultipartFile file) {
        try {
            String odds = falconService.getTheOdd(file);
            return ResponseEntity.ok(odds);
        } catch (MillenniumFalcomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

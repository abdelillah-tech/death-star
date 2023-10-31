package org.alghom.deathstar.domain.services;

import org.alghom.deathstar.domain.models.Empire;
import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.alghom.deathstar.exceptions.WrongJsonFormat;
import org.springframework.web.multipart.MultipartFile;

public interface FalconService {
    String getTheOdd(MultipartFile file) throws MillenniumFalcomException;
    String getTheOdd(Empire empire);
}

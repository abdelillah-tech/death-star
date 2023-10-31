package org.alghom.deathstar.domain.services;

import org.alghom.deathstar.domain.models.Empire;
import org.alghom.deathstar.dto.EmpireDTO;
import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.alghom.deathstar.exceptions.WrongJsonFormat;
import org.alghom.deathstar.utils.FalconMapper;
import org.alghom.deathstar.utils.FileReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

public class FalconServiceImpl implements FalconService {
    private final ComputerService computerService;
    public FalconServiceImpl(ComputerService computerService) {
        this.computerService = computerService;
    }
    @Override
    public String getTheOdd(MultipartFile file) throws MillenniumFalcomException {
        try {
            if(!Objects.equals(file.getContentType(), "application/json")) throw new IOException();
            EmpireDTO empireDTO = FileReader.readBytes(file.getBytes(), EmpireDTO.class);
            Empire empire = FalconMapper.empireDTO2EmpireMapper(empireDTO);
            double result = computerService.computeTheOdd(empire);
            return String.valueOf(result);
        } catch (IOException e) {
            throw new WrongJsonFormat("empire");
        }
    }

    @Override
    public String getTheOdd(Empire empire) {
        double result = computerService.computeTheOdd(empire);
        return String.valueOf(result);
    }
}

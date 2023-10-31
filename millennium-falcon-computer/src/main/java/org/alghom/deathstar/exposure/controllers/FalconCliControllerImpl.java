package org.alghom.deathstar.exposure.controllers;

import org.alghom.deathstar.domain.models.Empire;
import org.alghom.deathstar.domain.services.FalconService;

public class FalconCliControllerImpl implements FalconCliController {

    private final FalconService falconService;

    public FalconCliControllerImpl(FalconService falconService) {
        this.falconService = falconService;
    }

    @Override
    public void getTheOdd(Empire empire) {
        System.out.println(falconService.getTheOdd(empire) + "%");
    }
}

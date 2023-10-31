package org.alghom.deathstar.domain.services;

import org.alghom.deathstar.domain.models.Empire;

public interface ComputerService {
    double computeCapturingProbability(int huntingTemptation);
    double computeTheOdd(Empire empire);
}

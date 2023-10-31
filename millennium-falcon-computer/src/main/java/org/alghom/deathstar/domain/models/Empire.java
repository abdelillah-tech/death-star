package org.alghom.deathstar.domain.models;

import java.util.List;

public record Empire(Integer countdown, List<BountyHunter> bountyHunters) {
    public boolean isBountyHunterOnPlanet(Planet planet, int day) {
        for (BountyHunter bountyHunter: bountyHunters) {
            if (bountyHunter.planet().equals(planet) && bountyHunter.day() == day) return true;
        }
        return false;
    }
}
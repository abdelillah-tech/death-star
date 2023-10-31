package org.alghom.deathstar.domain.services;

import org.alghom.deathstar.domain.models.*;
import java.util.Set;

public class ComputerServiceImpl implements ComputerService {
    private final GalaxyMap galaxyMap;
    private final MillenniumFalcon millenniumFalcon;

    public ComputerServiceImpl(MillenniumFalcon millenniumFalcon, GalaxyMap galaxyMap) {
        this.millenniumFalcon = millenniumFalcon;
        this.galaxyMap = galaxyMap;
    }
    @Override
    public double computeCapturingProbability(int huntingTemptation) {
        if(huntingTemptation <= 0) return 0.0;
        return Math.pow(9, huntingTemptation - 1) / Math.pow(10, huntingTemptation) + computeCapturingProbability(huntingTemptation - 1);
    }

    @Override
    public double computeTheOdd(Empire empire) {
        TravelResult result = recursiveWay(millenniumFalcon, empire,0, new TravelResult());

        return result.isSucceeded() ?
                (1.0 - this.computeCapturingProbability(result.getTemptation())) * 100
                : 0.0;
    }

    private TravelResult recursiveWay(
            MillenniumFalcon falcon,
            Empire empire,
            int day,
            TravelResult history
    ) {
        TravelResult myHistory = new TravelResult(history);
        myHistory.getPlanets().add(new PlanetDistancePair(falcon.departure(), day));

        if(day > empire.countdown()) return new TravelResult();

        if(falcon.departure().equals(falcon.arrival())) {
            myHistory.setSucceeded(true);
            return myHistory;
        }

        if(empire.isBountyHunterOnPlanet(falcon.departure(), day)) myHistory.setTemptation(history.getTemptation() + 1);

        Set<PlanetDistancePair> neighbourPlanets = galaxyMap.get(falcon.departure());

        TravelResult finalNeighbourResult = null;

        for (PlanetDistancePair neighbourPlanet: neighbourPlanets) {

            int newAutonomy = falcon.autonomy() - neighbourPlanet.days();

            if(neighbourPlanet.days() > MillenniumFalcon.MAX_AUTONOMY) continue;

            int nextDayOnAPlanet = day + neighbourPlanet.days();

            Planet nextDayPlanet = new Planet(neighbourPlanet.planet().name());

            if(falcon.autonomy() < neighbourPlanet.days()) {
                newAutonomy = MillenniumFalcon.MAX_AUTONOMY;
                nextDayOnAPlanet = day + 1;
                nextDayPlanet = falcon.departure();
            }

            TravelResult travelResult = recursiveWay(
                    new MillenniumFalcon(newAutonomy, nextDayPlanet, falcon.arrival()),
                    empire,
                    nextDayOnAPlanet,
                    myHistory
            );

            if(travelResult.getTemptation() == 0 && travelResult.isSucceeded()) return travelResult;
            if(finalNeighbourResult == null) finalNeighbourResult = travelResult;
            if(travelResult.compareTo(finalNeighbourResult) > 0) finalNeighbourResult = travelResult;
        }


        return finalNeighbourResult == null ? history : finalNeighbourResult;
    }
}


package org.alghom.deathstar.domain.models;

import java.util.*;

public class GalaxyMap extends HashMap<Planet, LinkedHashSet<PlanetDistancePair>> {
    public GalaxyMap(List<Route> routes) {
        initGalaxy(routes);
    }

    public void initGalaxy(List<Route> routes) {
        routes.forEach(route -> {
            Planet origin = route.origin();
            Planet destination = route.destination();
            int travelTime = route.travelTime();

            LinkedHashSet<PlanetDistancePair> planetDistancePairs = super.get(origin);

            if (planetDistancePairs == null) planetDistancePairs = new LinkedHashSet<>();

            planetDistancePairs.add(new PlanetDistancePair(origin, travelTime));
            planetDistancePairs.add(new PlanetDistancePair(destination, travelTime));
            super.put(origin, planetDistancePairs);
        });
    }
}

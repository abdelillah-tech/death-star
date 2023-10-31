package org.alghom.deathstar.domain.repositories;


import org.alghom.deathstar.domain.models.Route;

import java.util.List;

public interface UniverseRepository {
    List<Route> getRoutes();
}

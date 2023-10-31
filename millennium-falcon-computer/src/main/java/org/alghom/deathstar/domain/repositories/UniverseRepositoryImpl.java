package org.alghom.deathstar.domain.repositories;


import org.alghom.deathstar.domain.models.Route;
import org.alghom.deathstar.domain.models.Planet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UniverseRepositoryImpl implements UniverseRepository {
    private final JdbcTemplate jdbcTemplate;
    public UniverseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Route> getRoutes() {
        return jdbcTemplate.query("SELECT * FROM routes", (resultSet, rowNum) -> {
            Planet origin = new Planet(resultSet.getString("ORIGIN"));
            Planet destination = new Planet(resultSet.getString("DESTINATION"));
            int travelTime = resultSet.getInt("TRAVEL_TIME");

            return new Route(origin, destination, travelTime);
        });
    }
}

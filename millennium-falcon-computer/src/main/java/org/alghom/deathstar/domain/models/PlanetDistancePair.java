package org.alghom.deathstar.domain.models;

import java.util.Objects;

public record PlanetDistancePair(Planet planet, int days) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanetDistancePair that = (PlanetDistancePair) o;

        if (days != that.days) return false;
        return Objects.equals(planet, that.planet);
    }

    @Override
    public int hashCode() {
        int result = planet != null ? planet.hashCode() : 0;
        result = 31 * result + days;
        return result;
    }
}

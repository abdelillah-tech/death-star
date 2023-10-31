package org.alghom.deathstar.domain.models;

public record MillenniumFalcon(
        Integer autonomy,
        Planet departure,
        Planet arrival
) {
    public static final int MAX_AUTONOMY = 6;
}

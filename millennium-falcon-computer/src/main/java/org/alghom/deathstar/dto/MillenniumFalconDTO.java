package org.alghom.deathstar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MillenniumFalconDTO(
        @JsonProperty("autonomy") int autonomy,
        @JsonProperty("departure") String departure,
        @JsonProperty("arrival") String arrival
) { }

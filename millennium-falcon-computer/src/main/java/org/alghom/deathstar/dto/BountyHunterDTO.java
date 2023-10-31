package org.alghom.deathstar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BountyHunterDTO(
        @JsonProperty("planet") String planet,
        @JsonProperty("day") int day
) { }

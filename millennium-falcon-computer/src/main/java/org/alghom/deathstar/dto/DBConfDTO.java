package org.alghom.deathstar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DBConfDTO(
        @JsonProperty("routes_db") String routesDB
) { }

package org.alghom.deathstar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EmpireDTO(
        @JsonProperty("countdown") int countdown,
        @JsonProperty("bounty_hunters") List<BountyHunterDTO> bountyHunterDTOS
) { }

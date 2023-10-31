package org.alghom.deathstar.utils;

import org.alghom.deathstar.dto.EmpireDTO;
import org.alghom.deathstar.dto.MillenniumFalconDTO;
import org.alghom.deathstar.domain.models.BountyHunter;
import org.alghom.deathstar.domain.models.Empire;
import org.alghom.deathstar.domain.models.MillenniumFalcon;
import org.alghom.deathstar.domain.models.Planet;

import java.util.List;

public class FalconMapper {
    public static Empire empireDTO2EmpireMapper(EmpireDTO empireDTO) {
        List<BountyHunter> bountyHunters = empireDTO.bountyHunterDTOS()
                .stream()
                .map(bountyHunterConfiguration ->
                        new BountyHunter(new Planet(bountyHunterConfiguration.planet()), bountyHunterConfiguration.day())
                )
                .toList();

        return new Empire(empireDTO.countdown(), bountyHunters);
    }

    public static MillenniumFalcon millenniumFalconDTO2MillenniumFalcon(MillenniumFalconDTO millenniumFalconDTO) {
        return new MillenniumFalcon(
                millenniumFalconDTO.autonomy(),
                new Planet(millenniumFalconDTO.departure()),
                new Planet(millenniumFalconDTO.arrival()));
    }
}

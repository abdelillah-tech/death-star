package org.alghom.deathstar.exposure;

import org.alghom.deathstar.domain.models.Empire;
import org.alghom.deathstar.domain.models.GalaxyMap;
import org.alghom.deathstar.domain.models.MillenniumFalcon;
import org.alghom.deathstar.domain.services.FalconService;
import org.alghom.deathstar.domain.services.FalconServiceImpl;
import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.alghom.deathstar.domain.repositories.UniverseRepository;
import org.alghom.deathstar.domain.repositories.UniverseRepositoryImpl;
import org.alghom.deathstar.domain.services.ComputerService;
import org.alghom.deathstar.domain.services.ComputerServiceImpl;
import org.alghom.deathstar.exceptions.WrongNumberOfArguments;
import org.alghom.deathstar.exposure.controllers.FalconCliController;
import org.alghom.deathstar.exposure.controllers.FalconCliControllerImpl;
import org.alghom.deathstar.utils.Initiator;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class CliApplication {
    public static void run(String[] args) {
        try {
            if(args == null || args.length != 2) throw new WrongNumberOfArguments();

            String falconConfigurationPath = args[0];
            String empireConfigurationPath = args[1];

            JdbcTemplate jdbcTemplate = Initiator.initShipDb(falconConfigurationPath);
            MillenniumFalcon millenniumFalcon = Initiator.initMillenniumFalcon(falconConfigurationPath);
            Empire empire = Initiator.initEmpire(empireConfigurationPath);

            UniverseRepository universeRepository = new UniverseRepositoryImpl(jdbcTemplate);
            GalaxyMap galaxyMap = Initiator.initGalaxy(universeRepository.getRoutes());

            ComputerService computerService = new ComputerServiceImpl(millenniumFalcon, galaxyMap);
            FalconService falconService = new FalconServiceImpl(computerService);

            FalconCliController falconController = new FalconCliControllerImpl(falconService);

            falconController.getTheOdd(empire);
        } catch (MillenniumFalcomException e) {
            System.out.println(e.getMessage());
        }
    }
}

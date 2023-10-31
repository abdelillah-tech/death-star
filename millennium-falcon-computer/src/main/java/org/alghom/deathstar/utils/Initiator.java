package org.alghom.deathstar.utils;

import org.alghom.deathstar.config.ConfigurationReader;
import org.alghom.deathstar.config.SQLiteDataSource;
import org.alghom.deathstar.domain.models.Empire;
import org.alghom.deathstar.domain.models.GalaxyMap;
import org.alghom.deathstar.domain.models.MillenniumFalcon;
import org.alghom.deathstar.domain.models.Route;
import org.alghom.deathstar.dto.EmpireDTO;
import org.alghom.deathstar.dto.MillenniumFalconDTO;
import org.alghom.deathstar.exceptions.ConfigurationFileNotFound;
import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.alghom.deathstar.exceptions.WrongJsonFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Initiator {
    private static final Logger LOGGER = LogManager.getLogger(Initiator.class);

    public static MillenniumFalcon initMillenniumFalcon(String falconConfigurationPath) throws MillenniumFalcomException {
        try {
            MillenniumFalconDTO millenniumFalconDTO = readConfigFile(falconConfigurationPath, MillenniumFalconDTO.class);

            LOGGER.debug("Ship configuration is ready");
            return FalconMapper.millenniumFalconDTO2MillenniumFalcon(millenniumFalconDTO);
        } catch (IOException e) {
            throw new WrongJsonFormat(falconConfigurationPath);
        }
    }

    public static Empire initEmpire(String empireConfigurationPath) throws MillenniumFalcomException {
        try {
            EmpireDTO empireDTO = readConfigFile(empireConfigurationPath, EmpireDTO.class);

            LOGGER.debug("Empire data received");
            return FalconMapper.empireDTO2EmpireMapper(empireDTO);
        } catch (IOException e) {
            throw new WrongJsonFormat(empireConfigurationPath);
        }
    }

    public static JdbcTemplate initShipDb(String configurationPath) throws MillenniumFalcomException {
        try{
            ConfigurationReader configurationReader = new ConfigurationReader(configurationPath);
            SQLiteDataSource sqLiteDataSource = new SQLiteDataSource(configurationReader);
            DataSource dataSource = sqLiteDataSource.dataSource();
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            LOGGER.debug("Ship data base is ready");
            return jdbcTemplate;
        } catch (IOException e) {
            throw new WrongJsonFormat(configurationPath);
        }
    }

    public static GalaxyMap initGalaxy(List<Route> routes) {
        LOGGER.debug("Ship routes are ready");
        return new GalaxyMap(routes);
    }

    public static <T> T readConfigFile(String configurationPath, Class<T> type) throws MillenniumFalcomException, IOException {
        File configurationFile = new File(configurationPath);
        if(!configurationFile.exists()) throw new ConfigurationFileNotFound(configurationPath);
        return FileReader.readJson(configurationFile, type);
    }
}

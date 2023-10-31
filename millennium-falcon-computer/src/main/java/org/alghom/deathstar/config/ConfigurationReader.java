package org.alghom.deathstar.config;

import org.alghom.deathstar.dto.DBConfDTO;
import org.alghom.deathstar.exceptions.MillenniumFalcomException;
import org.alghom.deathstar.exceptions.WrongJsonFormat;
import org.alghom.deathstar.domain.models.MillenniumFalcon;
import org.alghom.deathstar.utils.Initiator;

import java.io.IOException;
import java.nio.file.Paths;

import static org.alghom.deathstar.utils.Initiator.readConfigFile;

public class ConfigurationReader {
    private final String path;

    public ConfigurationReader(String path) {
        this.path = path;
    }

    public MillenniumFalcon millenniumFalcon() throws MillenniumFalcomException {
        return Initiator.initMillenniumFalcon(path);
    }

    public String dbPath() throws IOException, MillenniumFalcomException {
        DBConfDTO dbConfDTO = readConfigFile(path, DBConfDTO.class);
        return String.format("jdbc:sqlite:%s/%s", Paths.get(path).getParent(), dbConfDTO.routesDB());
    }
}
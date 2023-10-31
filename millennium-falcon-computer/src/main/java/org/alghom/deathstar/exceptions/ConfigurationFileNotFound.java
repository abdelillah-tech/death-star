package org.alghom.deathstar.exceptions;

public class ConfigurationFileNotFound extends MillenniumFalcomException {
    public ConfigurationFileNotFound(String fileName) {
        super(String.format("Can not find %s", fileName));
    }
}

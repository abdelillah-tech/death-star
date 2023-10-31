package org.alghom.deathstar.exceptions;

public class WrongJsonFormat extends MillenniumFalcomException {
    public WrongJsonFormat(String fileName) {
        super(String.format("The json format of file '%s' is wrong", fileName));
    }
}

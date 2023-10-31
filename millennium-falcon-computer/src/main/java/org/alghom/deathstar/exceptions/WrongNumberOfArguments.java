package org.alghom.deathstar.exceptions;

public class WrongNumberOfArguments extends MillenniumFalcomException {
    public WrongNumberOfArguments() {
        super("The command should take 2 arguments");
    }
}

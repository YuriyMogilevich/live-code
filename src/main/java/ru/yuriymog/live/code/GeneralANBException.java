package ru.yuriymog.live.code;

public class GeneralANBException extends Exception {
    public GeneralANBException() {
    }

    public GeneralANBException(String message) {
        super(message);
    }

    public GeneralANBException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralANBException(Throwable cause) {
        super(cause);
    }
}

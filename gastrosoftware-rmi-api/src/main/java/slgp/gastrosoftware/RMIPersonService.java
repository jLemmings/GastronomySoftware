package slgp.gastrosoftware;

import java.rmi.Remote;

public interface RMIPersonService extends PersonService, Remote {
    public static final String RO_NAME = "RO_PERSON_SERVICE";
}
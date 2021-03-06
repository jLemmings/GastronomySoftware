package ch.hslu.slgp.gastrosoftware.persister;


import ch.hslu.slgp.gastrosoftware.model.BestellPosition;

import java.util.List;

/**
 * Interface für Persistierung von BestellPositionen-Entities.
 */
public interface BestellPositionDAO extends GenericPersisterDAO<BestellPosition> {

    /**
     * Liefert alle Bestellpositionen zurück
     * @param
     * @return
     * @throws Exception
     */
    List<BestellPosition> findAll() throws Exception;

}



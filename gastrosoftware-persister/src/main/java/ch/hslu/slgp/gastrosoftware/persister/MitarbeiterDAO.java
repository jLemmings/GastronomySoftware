package ch.hslu.slgp.gastrosoftware.persister;


import ch.hslu.slgp.gastrosoftware.model.Mitarbeiter;

import java.util.List;

public interface MitarbeiterDAO extends GenericPersisterDAO<Mitarbeiter>{
    /**
     * Liefert alle Mitarbeiter für den übergebenen Nachnamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname
     * @return
     * @throws Exception
     */
    List<Mitarbeiter> findByNachname(String nachname) throws Exception;

    /**
     * Liefert alle Mitarbeiter für den übergebenen Vornamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param vorname
     * @return
     * @throws Exception
     */

    List<Mitarbeiter> findByVorname(String vorname) throws Exception;


    /**
     * Liefert alle Mitarbeiter für den übergebenen Nachname und Vorname zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname, vorname
     * @return
     * @throws Exception
     */
    List<Mitarbeiter> findByNachnameUndVorname(String nachname, String vorname) throws Exception;

    /**
     * Liefert alle Mitarbeiter für den übergebenen Username zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param username
     * @return
     * @throws Exception
     */

    Mitarbeiter findByUsername(String username) throws Exception;

}

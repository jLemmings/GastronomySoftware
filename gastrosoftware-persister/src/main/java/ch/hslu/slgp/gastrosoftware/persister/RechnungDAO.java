package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Rechnung;

import java.time.LocalDate;
import java.util.List;

public interface RechnungDAO extends GenericPersisterDAO<Rechnung> {

    /**
     * Liefert alle Rechnungen für das übergebene Datum zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param datum
     * @return
     * @throws Exception
     */
    List<Rechnung> findByDatum(LocalDate datum) throws Exception;
}

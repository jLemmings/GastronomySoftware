package ch.hslu.slgp.gastrosoftware.persister.impl;

import ch.hslu.slgp.gastrosoftware.model.Konsumartikel;
import ch.hslu.slgp.gastrosoftware.persister.KonsumartikelDAO;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class KonsumartikelDAOImpl extends GenericPersisterDAOImpl<Konsumartikel> implements KonsumartikelDAO {
    private static final Logger logger = LogManager.getLogger(KonsumartikelDAOImpl.class);

    public KonsumartikelDAOImpl() {
        super(Konsumartikel.class);
    }



    @Override
    public Konsumartikel findByBezeichnung(String bezeichnung) throws Exception {

        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Konsumartikel> query = em.createNamedQuery("Konsumartikel.findByBezeichnung", Konsumartikel.class);

        query.setParameter("bezeichnung", bezeichnung);

        List<Konsumartikel> liste = query.getResultList();

        em.close();

        if (liste.isEmpty()) {
            return null;
        } else if (liste.size() == 1) {
            return liste.get(0);
        } else {
            String message = "Mehr als eine Benutzer-Entity mit dem Benutzernamen \'" + bezeichnung + "\' gefunden";
            logger.error(message);
            throw new IllegalStateException(message);
        }
    }

    @Override
    public List<Konsumartikel> findByKategorie(String kategorie) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Konsumartikel> query = em.createNamedQuery("Konsumartikel.findByKategorie", Konsumartikel.class);

        query.setParameter("kategorie", kategorie);

        List<Konsumartikel> liste = query.getResultList();

        em.close();

        return liste;
    }

    @Override
    public List<Konsumartikel> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Konsumartikel> query = em.createNamedQuery("Konsumartikel.findAll", Konsumartikel.class);

        List<Konsumartikel> liste = query.getResultList();

        em.close();

        return liste;
    }


    @Override
    public Konsumartikel save(Konsumartikel entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Konsumartikel update(Konsumartikel entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Konsumartikel entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Konsumartikel findById(long id) throws Exception {
        return super.findById(id);
    }
}

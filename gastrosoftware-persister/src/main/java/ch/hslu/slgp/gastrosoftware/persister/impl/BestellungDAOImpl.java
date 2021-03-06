package ch.hslu.slgp.gastrosoftware.persister.impl;

import ch.hslu.slgp.gastrosoftware.model.BestellPosition;
import ch.hslu.slgp.gastrosoftware.model.Bestellung;
import ch.hslu.slgp.gastrosoftware.persister.BestellPositionDAO;
import ch.hslu.slgp.gastrosoftware.persister.BestellungDAO;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class BestellungDAOImpl extends GenericPersisterDAOImpl<Bestellung> implements BestellungDAO {

    public BestellungDAOImpl(Class<Bestellung> type) {
        super(type);
    }
	
	  public BestellungDAOImpl() {
		  super(Bestellung.class);
	  }

    public List<Bestellung> findByDatum(LocalDate datum) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByDatum", Bestellung.class);

        query.setParameter("datum", datum);

        List<Bestellung> liste = query.getResultList();

        em.close();

        return liste;
    }

    @Override
    public List<Bestellung> findAllBezahlt(boolean bezahlt) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findBezahlt", Bestellung.class);
        query.setParameter("bezahlt", bezahlt);
        List<Bestellung> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Bestellung> findByTischNummer(Integer tischNummer) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByTischNummer", Bestellung.class);
        query.setParameter("tischNummer", tischNummer);
        List<Bestellung> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public Bestellung save(Bestellung entity) throws Exception {
        BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();
        for (BestellPosition bestellPosition : entity.getBestellPositionList()) {
            bestellPositionDAO.save(bestellPosition);
        }
        return super.save(entity);
    }

    @Override
    public Bestellung update(Bestellung entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Bestellung entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Bestellung findById(long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public List<Bestellung> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findAll", Bestellung.class);

        List<Bestellung> liste = query.getResultList();

        em.close();

        return liste;
    }
}

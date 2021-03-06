package ch.hslu.slgp.gastrosoftware.persister.impl;

import ch.hslu.slgp.gastrosoftware.model.Tagesmenu;
import ch.hslu.slgp.gastrosoftware.persister.TagesmenuDAO;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TagesmenuDAOImpl extends GenericPersisterDAOImpl<Tagesmenu> implements TagesmenuDAO {
    public TagesmenuDAOImpl(Class<Tagesmenu> type) {
        super(type);
    }
	
	public TagesmenuDAOImpl() {
		super(Tagesmenu.class);
	}


    public List<Tagesmenu> findyByWochenTag(String wochenTag) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<Tagesmenu> query = em.createNamedQuery("Tagesmenu.findyByWochenTag", Tagesmenu.class);
        query.setParameter("wochenTag", wochenTag);
        List<Tagesmenu> liste = query.getResultList();
        em.close();
        return liste;
    }

    @Override
    public List<Tagesmenu> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<Tagesmenu> query = em.createNamedQuery("Tagesmenu.findAll", Tagesmenu.class);
        List<Tagesmenu> liste = query.getResultList();
        em.close();
        return liste;
    }

    @Override
    public Tagesmenu save(Tagesmenu entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Tagesmenu update(Tagesmenu entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Tagesmenu entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Tagesmenu findById(long id) throws Exception {
        return super.findById(id);
    }
}

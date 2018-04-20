package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.BestellungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BestellungDAOTest {

    private static Logger logger = LogManager.getLogger(BestellungDAOTest.class);

    private static BestellungDAO pBestellungDAO = new BestellungDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllBestellung();
        Util.deleteAllBestellPosition();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllBestellung();
        Util.deleteAllMitarbeiter();
        Util.deleteAllPersonen();
        Util.deleteAllBestellung();
        Util.deleteAllTisch();
        Util.deleteAllBestellPosition();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createBestellungListe();
        Util.createMitarbeiter();
        Util.createTisch();
        logger.info("Initalisierung fertig!");
    }


    @Test
    public void testBestellungSave() throws Exception {
        init();
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE);
    }

    @Test
    public void testBestellungDelete() throws Exception {
        init();
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE);
        int size = pBestellungDAO.findAll().size();
        Bestellung lastBestellung = pBestellungDAO.findAll().get(size - 1);
        pBestellungDAO.delete(lastBestellung);
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE - 1);
    }

    @Test
    public void testBestellungUpdateZubereitet() throws Exception {
        init();
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE);
        int size = pBestellungDAO.findAll().size();
        Bestellung lastBestellung = pBestellungDAO.findAll().get(size - 1);
        assertFalse(lastBestellung.getZubereitet());

        lastBestellung.setZubereitet(true);

        pBestellungDAO.update(lastBestellung);

        Bestellung lastBestellungNachUpdate = pBestellungDAO.findAll().get(size - 1);

        assertTrue(lastBestellungNachUpdate.getZubereitet());

    }

    @Test
    public void testBestellungFindByDate() throws Exception {
        init();
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE);

        Bestellung bestellung = pBestellungDAO.findAll().get(0);

        LocalDate datum = bestellung.getDatum();

        logger.info(datum);

        //assertTrue(bestellung.equals(pBestellungDAO.findByDatum(datum).get(0)));
    }

    @Test
    public void testBestellungFindByBezahlt() throws Exception {
        init();
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE);
        List<Bestellung> listFalse = pBestellungDAO.findAllBezahlt(false);
        List<Bestellung> listTrue = pBestellungDAO.findAllBezahlt(true);
        assertTrue(listFalse.size() == Util.INIT_SIZE_BESTELLUNG_LISTE);
        assertTrue(listTrue.size() == 0);
    }

}

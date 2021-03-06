package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Tisch;
import ch.hslu.slgp.gastrosoftware.persister.impl.TischDAOImpl;
import ch.hslu.slgp.gastrosoftware.persister.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TischDAOTest {
    private static Logger logger = LogManager.getLogger(TischDAOTest.class);

    private static TischDAO pTischDAO = new TischDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllTisch();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllTisch();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createTisch();
        logger.info("Initalisierung fertig!");
    }


    @Test
    public final void testSave() throws Exception {
        init();
        assertTrue(pTischDAO.findAll().size() == Util.INIT_SIZE_TISCH);
    }

    @Test
    public final void testFindByTischNummer() throws Exception{
        init();
        assertTrue(pTischDAO.findAll().size() == Util.INIT_SIZE_TISCH);
        Tisch tisch = pTischDAO.findByTischNummer(2);
        assertNotNull(tisch);

    }

}
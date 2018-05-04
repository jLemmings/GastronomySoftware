import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.input.sax.XMLReaderJDOMFactory;
//import org.jdom2.input.sax.XMLReaderSchemaFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;
import slgp.gastrosoftware.model.*;
import slgp.gastrosoftware.persister.EsswarenDAO;
import slgp.gastrosoftware.persister.GetraenkeDAO;
import slgp.gastrosoftware.persister.MitarbeiterDAO;
import slgp.gastrosoftware.persister.impl.EsswarenDAOImpl;
import slgp.gastrosoftware.persister.impl.GetraenkeDAOImpl;
import slgp.gastrosoftware.persister.impl.MitarbeiterDAOImpl;
import slgp.gastrosoftware.persister.util.JpaUtil;


public class AppInitializer {

    private static Logger logger = LogManager.getLogger(AppInitializer.class);

    public static void main(String[] args) {

        try {
            dropTables();
            logger.info(">> DATENBANK SCHEMA NEU ERSTELLT");

            initApplication();
            initTestdata();

            logger.info(">> DATENBANK ERFOLGREICH INITIALISIERT");

        } catch (Exception e) {
            logger.error("Initialisierung misslungen", e);
            throw new RuntimeException(e);
        }
    }

    private static void dropTables() {

        /*
         * Es wird nur die Verbindung aufgebaut, damit die bestehenden Tabellen gelöscht und neu angelegt werden.
         */
        JpaUtil.createEntityManagerForDelition().close();

    }

    private static void initApplication() throws Exception {

        Mitarbeiter ersterLeiter = null;
        Mitarbeiter ersterServicepersonal = null;
        Mitarbeiter ersterBarpersonal = null;

        String strPlz = "";

        try {
            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("moebelhaus.properties"));

            /* erster Administrator */
            String benutzerName = props.getProperty("erster_leiter_name");
            String benutzerVorname = props.getProperty("erster_leiter_vorname");
            String benutzerStrasse = props.getProperty("erster_leiter_adresse_strasse");
            String benutzerFunktion = props.getProperty("erster_leiter_funktion");
            strPlz = props.getProperty("erster_leiter_adresse_plz");
            String benutzerOrt = props.getProperty("erster_leiter_adresse_ort");
            String benutzerEmail = props.getProperty("erster_leiter_kontakt_mail");
            String benutzerTelefon = props.getProperty("erster_leiter_kontakt_telefon");
            String benutzerBenutzername = props.getProperty("erster_leiter_credentials_benutzername");
            String benutzerKennwort = props.getProperty("erster_leiter_credentials_kennwort");

            int benutzerPlz = Integer.parseInt(strPlz);

            ersterLeiter = new Mitarbeiter(benutzerName, benutzerVorname, benutzerFunktion, new Adresse(benutzerStrasse, benutzerPlz, benutzerOrt), new Kontakt(benutzerEmail, benutzerTelefon),
                    new Login(benutzerBenutzername, benutzerKennwort));

            /* erster Servicepersonal */
            benutzerName = props.getProperty("erster_servicepersonal_name");
            benutzerVorname = props.getProperty("erster_servicepersonal_vorname");
            benutzerStrasse = props.getProperty("erster_servicepersonal_adresse_strasse");
            benutzerFunktion = props.getProperty("erster_servicepersonal_funktion");
            strPlz = props.getProperty("erster_servicepersonal_adresse_plz");
            benutzerOrt = props.getProperty("erster_servicepersonal_adresse_ort");
            benutzerEmail = props.getProperty("erster_servicepersonal_kontakt_mail");
            benutzerTelefon = props.getProperty("erster_servicepersonal_kontakt_telefon");
            benutzerBenutzername = props.getProperty("erster_servicepersonal_credentials_benutzername");
            benutzerKennwort = props.getProperty("erster_servicepersonal_credentials_kennwort");

            benutzerPlz = Integer.parseInt(strPlz);

            ersterServicepersonal = new Mitarbeiter(benutzerName, benutzerVorname, benutzerFunktion, new Adresse(benutzerStrasse, benutzerPlz, benutzerOrt), new Kontakt(benutzerEmail, benutzerTelefon),
                    new Login(benutzerBenutzername, benutzerKennwort));

            /* Erster Barpersonal */
            benutzerName = props.getProperty("erster_barpersonal_name");
            benutzerVorname = props.getProperty("erster_barpersonal_vorname");
            benutzerStrasse = props.getProperty("erster_barpersonal_adresse_strasse");
            benutzerFunktion = props.getProperty("erster_barpersonal_funktion");
            strPlz = props.getProperty("erster_barpersonal_adresse_plz");
            benutzerOrt = props.getProperty("erster_barpersonal_adresse_ort");
            benutzerEmail = props.getProperty("erster_barpersonal_kontakt_mail");
            benutzerTelefon = props.getProperty("erster_barpersonal_kontakt_telefon");
            benutzerBenutzername = props.getProperty("erster_barpersonal_credentials_benutzername");
            benutzerKennwort = props.getProperty("erster_barpersonal_credentials_kennwort");

            benutzerPlz = Integer.parseInt(strPlz);

            ersterBarpersonal = new Mitarbeiter(benutzerName, benutzerVorname, benutzerFunktion, new Adresse(benutzerStrasse, benutzerPlz, benutzerOrt), new Kontakt(benutzerEmail, benutzerTelefon),
                    new Login(benutzerBenutzername, benutzerKennwort));

            /* Ersten Admin speichern */
            ersterLeiter = new MitarbeiterDAOImpl().save(ersterLeiter);
            /* Ersten FilialeLager-Mitarbeiter speichern */
            ersterServicepersonal = new MitarbeiterDAOImpl().save(ersterServicepersonal);
            /* Ersten Admin speichern */
            ersterBarpersonal = new MitarbeiterDAOImpl().save(ersterBarpersonal);

        } catch (NumberFormatException e) {
            logger.error("Der Wert für Postleitzahl [" + strPlz + "] ist nicht korrekt: ", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Fehler beim Einlesen der property-Datei: ", e);
            throw new RuntimeException(e);
        }
    }

    private static void initTestdata() {
        try {
//            initRegal();1
            initEsswaren();
            initGetraenke();
//            initProduktTyp();
//            initProdukt();
            initBenutzer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //    private static void initRegal() throws Exception {
//
//        int anzahl = 20;
//
//        Lager lager = null;
//        LagerDAO lagerDao = new LagerDAOImpl();
//        RegalDAO regalDao = new RegalDAOImpl();
//        TablarDAO tablarDao = new TablarDAOImpl();
//
//        for (int i = 0; i < anzahl; i++) {
//
//            Regal r = new Regal(i + 1);
//            lager = lagerDao.findAll().get(0);
//
//            r.setBezeichnung("R" + r.getNummer());
//
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TA0"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TB0"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TC0"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TD0"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TA1"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TB1"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TC1"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TD1"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TA2"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TB2"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TC2"));
//            r.getTablarListe().add(new Tablar(r.getBezeichnung() + "-" + "TD2"));
//
//            for (Tablar t : r.getTablarListe()) {
//                tablarDao.save(t);
//            }
//
//            regalDao.save(r);
//            lager.getRegalListe().add(r);
//        }
//
//        lagerDao.update(lager);
//
//    }
//
    private static void initEsswaren() throws Exception {

        String xmlDateiName = "/esswarenInit.xml";
        String schemaDateiName = "/esswarenInitSchema.xsd";

        List<Esswaren> esswarenList = new ArrayList<Esswaren>();

        try {

            /* Laden und Validieren der XML-Datei */

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            URL xsdURL = AppInitializer.class.getResource(schemaDateiName).toURI().toURL();
            Schema schema = schemaFactory.newSchema(xsdURL);

            XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);

            SAXBuilder sb = new SAXBuilder(factory);

            URL xmlURL = AppInitializer.class.getResource(xmlDateiName).toURI().toURL();
            Document doc = sb.build(xmlURL);

            Element esswarenElement = doc.getRootElement();

            List<Element> esswarenElementList = esswarenElement.getChildren("esswaren");

            int index = 0;

            logger.info(">> Erzeugung von Konsumartikel gestartet.");

            for (Element element : esswarenElementList) {

                element = esswarenElementList.get(index++);

                Double preis = Double.parseDouble(element.getChildText("preis"));

                Esswaren esswaren = new Esswaren(element.getChildText("bezeichnung"), element.getChildText("kategorie"), preis);
                esswarenList.add(esswaren);

                logger.info("    >> Esswaren " + esswaren.getBezeichnung() + " erzeugt.");

            }

            logger.info(">> Erzeugung von Esswaren beendet.");

            EsswarenDAO esswarenDAO = new EsswarenDAOImpl();

            for (Esswaren esswaren : esswarenList) {
                esswarenDAO.save(esswaren);
                logger.info(">> Esswaren mit Id-Nr. " + esswaren.getId() + " wurde in die Datebank gespeichert.");
            }

        } catch (JDOMException e) {
            logger.error("Fehler bei der Validierung von " + xmlDateiName, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Fehler beim Lesen der XML-Datei: " + xmlDateiName + ".", e);
            throw new RuntimeException(e);
        }
    }

    private static void initGetraenke() throws Exception {

        String xmlDateiName = "/getraenkeInit.xml";
        String schemaDateiName = "/getraenkeInitSchema.xsd";

        List<Getraenke> getraenkeList = new ArrayList<Getraenke>();

        try {

            /* Laden und Validieren der XML-Datei */

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            URL xsdURL = AppInitializer.class.getResource(schemaDateiName).toURI().toURL();
            Schema schema = schemaFactory.newSchema(xsdURL);

            XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);

            SAXBuilder sb = new SAXBuilder(factory);

            URL xmlURL = AppInitializer.class.getResource(xmlDateiName).toURI().toURL();
            Document doc = sb.build(xmlURL);

            Element getraenkeElement = doc.getRootElement();

            List<Element> getraenkeElementList = getraenkeElement.getChildren("getraenke");

            int index = 0;

            logger.info(">> Erzeugung von Getränke gestartet.");

            for (Element element : getraenkeElementList) {

                element = getraenkeElementList.get(index++);

                Double preis = Double.parseDouble(element.getChildText("preis"));

                Getraenke getraenke = new Getraenke(element.getChildText("bezeichnung"), element.getChildText("kategorie"), preis);
                getraenkeList.add(getraenke);

                logger.info("    >> Getränke " + getraenke.getBezeichnung() + " erzeugt.");

            }

            logger.info(">> Erzeugung von Esswaren beendet.");

            GetraenkeDAO getraenkeDAO = new GetraenkeDAOImpl();

            for (Getraenke getraenke : getraenkeList) {
                getraenkeDAO.save(getraenke);
                logger.info(">> Getränke mit Id-Nr. " + getraenke.getId() + " wurde in die Datebank gespeichert.");
            }

        } catch (JDOMException e) {
            logger.error("Fehler bei der Validierung von " + xmlDateiName, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Fehler beim Lesen der XML-Datei: " + xmlDateiName + ".", e);
            throw new RuntimeException(e);
        }
    }

    //
//    private static void initProduktTyp() throws Exception {
//
//        String xmlDateiName = "/produktTypInit.xml";
//        String schemaDateiName = "/produktTypInitSchema.xsd";
//
//        List<ProduktTyp> produktTypListe = new ArrayList<ProduktTyp>();
//
//        try {
//
//            /* Laden und Validieren der XML-Datei */
//
//            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//
//            URL xsdURL = AppInitializer.class.getResource(schemaDateiName).toURI().toURL();
//            Schema schema = schemaFactory.newSchema(xsdURL);
//
//            XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);
//
//            SAXBuilder sb = new SAXBuilder(factory);
//
//            URL xmlURL = AppInitializer.class.getResource(xmlDateiName).toURI().toURL();
//            Document doc = sb.build(xmlURL);
//
//            Element hersteller = doc.getRootElement();
//
//            List<Element> produktTypElementliste = hersteller.getChildren("produktTyp");
//
//            int index = 0;
//
//            logger.info(">> Erzeugung von ProduktTypen gestartet.");
//
//            List<Lieferant> lieferantenListe = new LieferantDAOImpl().findAll();
//
//            for (Element element : produktTypElementliste) {
//
//                element = produktTypElementliste.get(index++);
//
//                Double preis = Double.parseDouble(element.getChildText("preis"));
//                ProduktTyp pTyp = new ProduktTyp(element.getChildText("name"), element.getChildText("beschreibung"),
//                        preis, element.getChildText("code"), lieferantenMap.get(element.getChildText("lieferant")));
//                pTyp.setMinimalerBestand(Integer.parseInt(element.getChildText("min-bestand")));
//                produktTypListe.add(pTyp);
//
//                logger.info("    >> ProduktTyp mit Code " + pTyp.getTypCode() + " erzeugt.");
//
//            }
//
//            logger.info(">> Erzeugung von ProduktTypen beendet.");
//
//            TablarDAO tablarDAO = new TablarDAOImpl();
//            ProduktTypDAO produktTypDAO = new ProduktTypDAOImpl();
//            index = 0;
//
//            for (ProduktTyp pTyp : produktTypListe) {
//                pTyp.setMaximalerBestand(50);
//                pTyp.setAblageTablar(tablarDAO.findAll().get(index++));
//                produktTypDAO.save(pTyp);
//                logger.info(">> ProduktTyp mit Id-Nr. " + pTyp.getId() + " wurde in die Datebank gespeichert.");
//            }
//
//        } catch (JDOMException e) {
//            logger.error("Fehler bei der Validierung von " + xmlDateiName, e);
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            logger.error("Fehler beim Lesen der XML-Datei: " + xmlDateiName + ".", e);
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    private static void initProdukt() throws Exception {
//
//        List<ProduktTyp> produktTypListe = new ProduktTypDAOImpl().findAll();
//
//        ProduktDAO dao = new ProduktDAOImpl();
//
//        logger.info(">> Erzeugung von Produkte gestartet.");
//
//        for (ProduktTyp pTyp : produktTypListe) {
//
//            int anzahlProdukte = ThreadLocalRandom.current().nextInt(pTyp.getMinimalerBestand(),
//                    pTyp.getMaximalerBestand());
//
//            for (int i = 1; i <= anzahlProdukte; i++) {
//                Produkt p = new Produkt(pTyp, getProduktCode());
//                dao.save(p);
//                logger.info("        >> Produkt vom Typ " + pTyp.getTypCode() + " mit Id-Nr. " + p.getId()
//                        + " wurde in die Datebank gespeichert.");
//            }
//            logger.info("   >> " + anzahlProdukte + " Produkt(e) von Typ " + pTyp.getTypCode() + " erzeugt.");
//        }
//
//        logger.info(">> Erzeugung von Produkte beendet.");
//    }
//
    private static void initBenutzer() throws Exception {

        String xmlDateiName = "/benutzerInit.xml";
        String schemaDateiName = "/benutzerInitSchema.xsd";

        List<Mitarbeiter> mitarbeiterList = new ArrayList<Mitarbeiter>();

        try {

            /* Laden und Validieren der XML-Datei */

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            URL xsdURL = AppInitializer.class.getResource(schemaDateiName).toURI().toURL();
            Schema schema = schemaFactory.newSchema(xsdURL);

            XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);

            SAXBuilder sb = new SAXBuilder(factory);

            URL xmlURL = AppInitializer.class.getResource(xmlDateiName).toURI().toURL();
            Document doc = sb.build(xmlURL);

            Element users = doc.getRootElement();

            List<Element> benutzerElementliste = users.getChildren("benutzer");

            int index = 0;

            logger.info(">> Erzeugung von Benutzer gestartet.");

            for (Element element : benutzerElementliste) {

                element = benutzerElementliste.get(index++);

                int plz = Integer.parseInt(element.getChildText("plz"));

                Mitarbeiter mitarbeiter = new Mitarbeiter(element.getChildText("nachname"), element.getChildText("vorname"), element.getChildText("funktion"),
                        new Adresse(element.getChildText("strasse"), plz, element.getChildText("ort")),
                        new Kontakt(element.getChildText("email"), element.getChildText("telefon")),
                        new Login(element.getChildText("benutzername"), element.getChildText("passwort")));
                mitarbeiterList.add(mitarbeiter);

                logger.info("    >> Mitarbeiter " + mitarbeiter.getVorname() + " " + mitarbeiter.getName() + " erzeugt.");

            }

            logger.info(">> Erzeugung von Benutzer beendet.");

            MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAOImpl();

            for (Mitarbeiter ma : mitarbeiterList) {
                mitarbeiterDAO.save(ma);
                logger.info(">> Benutzer mit Id-Nr. " + ma.getId() + " wurde in die Datebank gespeichert.");
            }

        } catch (JDOMException e) {
            logger.error("Fehler bei der Validierung von " + xmlDateiName, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Fehler beim Lesen der XML-Datei: " + xmlDateiName + ".", e);
            throw new RuntimeException(e);
        }
    }
//
//    /* Helper-Methode */
//    private static long getProduktCode() {
//
//        try {
//            Thread.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        return new GregorianCalendar().getTimeInMillis();
//    }
}

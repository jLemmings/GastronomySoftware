import api.PrinterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestPrinter {
    public static void main (String[] args){
        Logger logger = LogManager.getLogger(TestPrinter.class);

        List<Konsumartikel> konsumartikelList = new ArrayList<>();
        konsumartikelList.add(new Esswaren("Pizza", "Hauptspeise", 13));
        konsumartikelList.add(new Esswaren("Pasta", "Hauptspeise", 15));

        List<BestellPosition> bestellPositionList = new ArrayList<>();
        for (Konsumartikel konsumartikel : konsumartikelList) {
            bestellPositionList.add(new BestellPosition(konsumartikel, 1));
        }

        Tisch tisch = new Tisch(6);

        Mitarbeiter ma = new Mitarbeiter("Meierhans", "Franz", "Barpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22"));

        List<Bestellung> bestellungList = new ArrayList<>();
        bestellungList.add(new Bestellung(ma, tisch, bestellPositionList, false, false, LocalDate.now()));

        TischRechnung tischRechnung = new TischRechnung(LocalDate.now(), "Hallo", bestellungList);

        PDFPrinter pdfPrinter = new PDFPrinter();
        try {
            logger.info("Starting PDF Print");
            pdfPrinter.printTischRechnungAlsPdf(tischRechnung);
            logger.info("DONE!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
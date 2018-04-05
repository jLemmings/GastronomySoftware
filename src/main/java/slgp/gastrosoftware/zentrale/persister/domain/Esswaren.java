package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Esswaren.findAll", query = "SELECT e FROM Esswaren e")})
public class Esswaren extends Konsumartikel{

    public Esswaren() {
    }

    public Esswaren(String bezeichnung, String kategorie, double preis) {
        super(bezeichnung, kategorie, preis);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

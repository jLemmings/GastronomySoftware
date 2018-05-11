package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "TischRechnung.findAll", query = "SELECT e FROM TischRechnung e")})
public class TischRechnung extends Rechnung {


    @OneToMany(fetch = FetchType.EAGER)
    private List<Bestellung> bestellungList;
    private double summeBestellungen;

    public TischRechnung() {
    }

    public TischRechnung(LocalDate datum, List<Bestellung> bestellungList) {
        super(datum);
        this.bestellungList = bestellungList;
        berechneSummeBestellungen();
    }

    public void berechneSummeBestellungen() {
        for (Bestellung bestellung : bestellungList){
            summeBestellungen += bestellung.getSummebestellPositionList();
        }
    }

    public int getId() {
        return super.getId();
    }

    public List<Bestellung> getBestellungList() {
        return bestellungList;
    }

    public void setBestellungList(List<Bestellung> bestellungList) {
        this.bestellungList = bestellungList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TischRechnung that = (TischRechnung) o;
        return Double.compare(that.summeBestellungen, summeBestellungen) == 0 &&
                Objects.equals(bestellungList, that.bestellungList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), bestellungList, summeBestellungen);
    }

    public double getSummeBestellungen() {
        return summeBestellungen;
    }

    public void setSummeBestellungen(double summeBestellungen) {
        this.summeBestellungen = summeBestellungen;
    }

    @Override
    public String toString() {
        return "TischRechnung{" +
                "bestellungList=" + bestellungList +
                ", summeBestellungen=" + summeBestellungen +
                '}';
    }
}

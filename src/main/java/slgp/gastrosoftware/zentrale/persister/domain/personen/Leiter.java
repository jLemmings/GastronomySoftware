package slgp.gastrosoftware.zentrale.persister.domain.personen;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Leiter extends Person {
	
	
	public Leiter(String name, String vorname, Adresse adresse, Kontakt kontakt) {
		super(name, vorname, adresse, kontakt);
	}

	public Leiter() {
	}

	
	@Override
	public String toString() {
		return super.toString();
	}

}
package slgp.gastrosoftware.zentrale.persister.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String vorname;
	private Adresse adresse;
	private Kontakt kontakt;
	private Login login;
	
	public Person() {
		
	}
	
	public Person(String name, String vorname, Adresse adresse, Kontakt kontakt, Login login) {
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
		this.kontakt = kontakt;
		this.login = login;
	}
	
	public Person(String name, String vorname, Adresse adresse, Kontakt kontakt) {
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
		this.kontakt = kontakt;
	}
	
	public Login getLogin() {
		return login;
	}

	public int getId() {
		return id;
	}


	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Kontakt getKontakt() {
		return kontakt;
	}

	public void setKontakt(Kontakt kontakt) {
		this.kontakt = kontakt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, adresse);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if(!(obj instanceof Person)) {
			return false;
		}
		
		Person pers = (Person)obj;
		
		return this.name == pers.name && this.adresse == pers.adresse;
	}
	
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", vorname=" + vorname + ", adresse=" + adresse + ", kontakt=" + kontakt + ", login=" + login + "]";
	}

}

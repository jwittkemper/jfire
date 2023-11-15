package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( name = "Replication")
@XmlRootElement(name = "Replication")
public class Replication implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2180767840110593353L;
	int dbVersion;
	String herkunft;
	List<Mitglied> mitglied;
	List<FoerderMitglied> foerdermitglieder;
	
	public int getDbVersion() {
		return dbVersion;
	}
	public void setDbVersion(int dbVersion) {
		this.dbVersion = dbVersion;
	}
	public String getHerkunft() {
		return herkunft;
	}
	public void setHerkunft(String herkunft) {
		this.herkunft = herkunft;
	}
	public List<Mitglied> getMitglied() {
		return mitglied;
	}
	public void setMitglied(List<Mitglied> mitglied) {
		this.mitglied = mitglied;
	}
	public List<FoerderMitglied> getFoerdermitglieder() {
		return foerdermitglieder;
	}
	public void setFoerdermitglieder(List<FoerderMitglied> foerdermitglieder) {
		this.foerdermitglieder = foerdermitglieder;
	}

}

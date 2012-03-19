package biz.wittkemper.jfire.data.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( name = "Replication")
@XmlRootElement(name = "Replication")
public class Replication {
	
	int dbVersion;
	String herkunft;
	List<Mitglied> mitglied;
	
	
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

}

package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class Mitglied implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 371003173198002825L;

	Long id;
	Long masterId;
	Anrede anrede;
	String name;
	String vorname;
	String strasseNr;
	int plz;
	String ort;
	Date gebDatum;
	String telefonPrivatFest;
	String telefonPrivatMobil;
	String telefonDienst;
	String eMail;
	Date eintritt;
	MitgliedStatus status;
	Date masterInsert;
	Date lastChange = new Date();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	@Column(length=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=30)
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	@Column(length=100)
	public String getStrasseNr() {
		return strasseNr;
	}

	public void setStrasseNr(String strasseNr) {
		this.strasseNr = strasseNr;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	@Column(length=30)
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public Date getGebDatum() {
		return gebDatum;
	}

	public void setGebDatum(Date gebDatum) {
		this.gebDatum = gebDatum;
	}

	@Column(length=30)
	public String getTelefonPrivatFest() {
		return telefonPrivatFest;
	}

	public void setTelefonPrivatFest(String telefonPrivatFest) {
		this.telefonPrivatFest = telefonPrivatFest;
	}

	@Column(length=30)
	public String getTelefonPrivatMobil() {
		return telefonPrivatMobil;
	}

	public void setTelefonPrivatMobil(String telefonPrivatMobil) {
		this.telefonPrivatMobil = telefonPrivatMobil;
	}

	@Column(length=30)
	public String getTelefonDienst() {
		return telefonDienst;
	}

	public void setTelefonDienst(String telefonDienst) {
		this.telefonDienst = telefonDienst;
	}

	@Column(length=60)
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Date getEintritt() {
		return eintritt;
	}

	public void setEintritt(Date eintritt) {
		this.eintritt = eintritt;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public MitgliedStatus getStatus() {
		return status;
	}

	public void setStatus(MitgliedStatus status) {
		this.status = status;
	}

	public Date getMasterInsert() {
		return masterInsert;
	}

	public void setMasterInsert(Date masterInsert) {
		this.masterInsert = masterInsert;
	}

	public Date getLastChange() {
		return lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public Anrede getAnrede() {
		return anrede;
	}

	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}


}

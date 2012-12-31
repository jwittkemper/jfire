package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Proxy;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Mitglied")
@Entity
@Proxy(lazy = false)
public class Mitglied implements Serializable {

	public enum LOESCHGRUNG {
		AUSTRITT, VERSTORBEN
	}

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
	boolean geloescht;
	LOESCHGRUNG geloeschtWeil;
	Date geloeschtAM;
	boolean rettungsdienst;
	boolean edit;
	boolean datenfreigabe;
	Set<Mitglied_Fuehrerschein> fuehrerscheins = new HashSet<Mitglied_Fuehrerschein>(
			0);

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

	@Column(length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 30)
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	@Column(length = 100)
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

	@Column(length = 30)
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

	@Column(length = 30)
	public String getTelefonPrivatFest() {
		return telefonPrivatFest;
	}

	public void setTelefonPrivatFest(String telefonPrivatFest) {
		this.telefonPrivatFest = telefonPrivatFest;
	}

	@Column(length = 30)
	public String getTelefonPrivatMobil() {
		return telefonPrivatMobil;
	}

	public void setTelefonPrivatMobil(String telefonPrivatMobil) {
		this.telefonPrivatMobil = telefonPrivatMobil;
	}

	@Column(length = 30)
	public String getTelefonDienst() {
		return telefonDienst;
	}

	public void setTelefonDienst(String telefonDienst) {
		this.telefonDienst = telefonDienst;
	}

	@Column(length = 60)
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

	@ManyToOne(fetch = FetchType.EAGER)
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

	@ManyToOne(fetch = FetchType.EAGER)
	public Anrede getAnrede() {
		return anrede;
	}

	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}

	public boolean isGeloescht() {
		return geloescht;
	}

	public void setGeloescht(boolean geloescht) {
		this.geloescht = geloescht;
	}

	public boolean isRettungsdienst() {
		return rettungsdienst;
	}

	public void setRettungsdienst(boolean rettungsdienst) {
		this.rettungsdienst = rettungsdienst;
	}

	@Transient
	public void mergeMitglied(Mitglied mg) {
		this.setAnrede(mg.getAnrede());
		this.setEintritt(mg.getEintritt());
		this.seteMail(mg.geteMail());
		this.setGebDatum(mg.getGebDatum());
		this.setGeloescht(mg.isGeloescht());
		this.setLastChange(mg.getLastChange());
		this.setMasterInsert(mg.getMasterInsert());
		this.setName(mg.getName());
		this.setOrt(mg.getOrt());
		this.setPlz(mg.getPlz());
		this.setRettungsdienst(mg.isRettungsdienst());
		this.setStatus(mg.getStatus());
		this.setStrasseNr(mg.getStrasseNr());
		this.setTelefonDienst(mg.getTelefonDienst());
		this.setTelefonPrivatFest(mg.getTelefonPrivatFest());
		this.setTelefonPrivatMobil(mg.getTelefonPrivatMobil());
		this.setVorname(mg.getVorname());
		this.setGeloeschtAM(mg.getGeloeschtAM());
		this.setGeloeschtWeil(mg.getGeloeschtWeil());
		this.setDatenfreigabe(mg.isDatenfreigabe());
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	@Column(name = "loeschgrund")
	@Enumerated(EnumType.ORDINAL)
	public LOESCHGRUNG getGeloeschtWeil() {
		return geloeschtWeil;
	}

	public void setGeloeschtWeil(LOESCHGRUNG geloeschtWeil) {
		this.geloeschtWeil = geloeschtWeil;
	}

	@Column(name = "geloeschtAM")
	public Date getGeloeschtAM() {
		return geloeschtAM;
	}

	public void setGeloeschtAM(Date geloeschtAM) {
		this.geloeschtAM = geloeschtAM;
	}

	@Column(name = "datenfreigabe")
	public boolean isDatenfreigabe() {
		return datenfreigabe;
	}

	public void setDatenfreigabe(boolean datenfreigabe) {
		this.datenfreigabe = datenfreigabe;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mfID.mitglied", cascade = CascadeType.ALL)
	public Set<Mitglied_Fuehrerschein> getFuehrerscheins() {
		return fuehrerscheins;
	}

	public void setFuehrerscheins(Set<Mitglied_Fuehrerschein> fuehrerscheins) {
		this.fuehrerscheins = fuehrerscheins;
	}
}

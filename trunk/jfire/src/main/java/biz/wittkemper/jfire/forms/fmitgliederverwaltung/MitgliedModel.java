package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;

import com.jgoodies.binding.beans.ExtendedPropertyChangeSupport;

public class MitgliedModel {
	private Mitglied mitglied = new Mitglied();

	String name;
	String vorname;
	Date eintritt;
	String email;
	Date gebDatum;
	
	private ExtendedPropertyChangeSupport changeSupport;

	private List<MitgliedStatus> mitgliedStatuse = new ArrayList<MitgliedStatus>();
	private FoerderMitglied foerderMitglied;

	private MitgliedStatus status;

	private String strasseNr;

	private int plz;

	private String ort;

	private String telefondienst;

	private String telefonPrivatFest;

	private String telefonPrivatMobil;

	public MitgliedModel() {
		changeSupport = new ExtendedPropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener x) {
		changeSupport.addPropertyChangeListener(x);
	}

	public void removePropertyChangeListener(PropertyChangeListener x) {
		changeSupport.removePropertyChangeListener(x);
	}

	public void setMitglied(Mitglied mitglied) {
		this.mitglied = mitglied;
		beanToModel();
	}

	private void beanToModel() {
		this.setName(mitglied.getName());
		this.setVorname(mitglied.getVorname());
		this.setEintritt(mitglied.getEintritt());
		this.seteMail(mitglied.geteMail());
		this.setGebDatum(mitglied.getGebDatum());
		this.setOrt(mitglied.getOrt());
		this.setPlz(mitglied.getPlz());
		this.setStatus(mitglied.getStatus());
		this.setStrasseNr(mitglied.getStrasseNr());
		this.setTelefonDienst(mitglied.getTelefonDienst());
		this.setTelefonPrivatFest(mitglied.getTelefonPrivatFest());
		this.setTelefonPrivatMobil(mitglied.getTelefonPrivatMobil());
	}

	public Mitglied getMitglied() {

		return modelToBean();
	}

	private Mitglied modelToBean() {
		mitglied.setEintritt(eintritt);
		mitglied.seteMail(email);
		mitglied.setGebDatum(gebDatum);
		mitglied.setName(name);
		mitglied.setVorname(vorname);
		mitglied.setOrt(ort);
		mitglied.setPlz(plz);
		mitglied.setStrasseNr(strasseNr);
		mitglied.setTelefonDienst(telefondienst);
		mitglied.setTelefonPrivatFest(telefonPrivatFest);
		mitglied.setTelefonPrivatMobil(telefonPrivatMobil);
		mitglied.setStatus(status);
		
		return mitglied;
	}

	public void setName(String name) {
		String old = this.name;
		this.name = name;
		changeSupport.firePropertyChange("name", old, name);
	}

	public String getName() {
		return this.name;
	}

	public void setVorname(String vorname) {
		String old = this.vorname;
		this.vorname = vorname;
		changeSupport.firePropertyChange("vorname", old, vorname);
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setStrasseNr(String strasseNr) {
		String old = this.strasseNr;
		this.strasseNr = strasseNr;
		changeSupport.firePropertyChange("strasseNr", old, strasseNr);
	}

	public String getStrasseNr() {
		return this.strasseNr;
	}

	public void setPlz(int plz) {
		int old = this.plz;
		this.plz = plz;
		changeSupport.firePropertyChange("plz", old, plz);
	}

	public int getPlz() {
		return this.plz;
	}

	public void setOrt(String ort) {
		String old = this.ort;
		this.ort = ort;
		changeSupport.firePropertyChange("ort", old, ort);
	}

	public String getOrt() {
		return this.ort;
	}

	public void setTelefonDienst(String telefon) {
		String old = this.telefondienst;
		this.telefondienst = telefon;
		changeSupport.firePropertyChange("telefonDienst", old, telefon);
	}

	public String getTelefonDienst() {
		return this.telefondienst;
	}

	public void setTelefonPrivatFest(String telefon) {
		String old = this.telefonPrivatFest;
		this.telefonPrivatFest = telefon;
		changeSupport.firePropertyChange("telefonPrivatFest", old, telefon);
	}

	public String getTelefonPrivatFest() {
		return this.telefonPrivatFest;
	}

	public void setTelefonPrivatMobil(String telefon) {
		String old = this.telefonPrivatMobil;
		this.telefonPrivatMobil = telefon;
		changeSupport.firePropertyChange("telefonPrivatMobil", old, telefon);
	}

	public String getTelefonPrivatMobil() {
		return this.telefonPrivatMobil;
	}

	public void seteMail(String email) {
		String old = this.email;
		this.email = email;
		changeSupport.firePropertyChange("eMail", old, email);
	}

	public String geteMail() {
		return this.email;
	}

	public void setGebDatum(Date gebDatum) {
		Date old = this.gebDatum;
		this.gebDatum = gebDatum;
		changeSupport.firePropertyChange("gebDatum", old, gebDatum);
	}

	public Date getGebDatum() {
		return this.gebDatum;
	}

	public void setStatus(MitgliedStatus status) {
		MitgliedStatus old = this.status;
		this.status =status;
		changeSupport.firePropertyChange("status", old, status);
	}

	public MitgliedStatus getStatus() {
		return this.status;
	}

	public void setEintritt(Date eintritt) {
		Date old = this.eintritt;
		this.eintritt = eintritt;
		changeSupport.firePropertyChange("eintritt", old, eintritt);
	}

	public Date getEintritt() {
		return this.eintritt;
	}

	public List<MitgliedStatus> getMitgliedStatuse() {

		mitgliedStatuse = DAOFactory.getInstance().getMitgliedStatusDAO()
				.findByQueryString("From MitgliedStatus a");
		return mitgliedStatuse;
	}

	public FoerderMitglied getFoerderMitglied() {
		return foerderMitglied;
	}

	public void setFoerderMitglied(FoerderMitglied foerderMitglied) {
		this.foerderMitglied = foerderMitglied;
	}

}

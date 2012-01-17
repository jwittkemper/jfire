package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.jgoodies.binding.beans.ExtendedPropertyChangeSupport;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;

public class MitgliedModel extends Mitglied {

	private ExtendedPropertyChangeSupport changeSupport;
	private static final long serialVersionUID = 1L;
	
	private List<MitgliedStatus> mitgliedStatuse = new ArrayList<MitgliedStatus>();
	
	public MitgliedModel(){
		changeSupport = new ExtendedPropertyChangeSupport(this);
	}
	
	
	public void setMitglied(Mitglied mitglied){
		this.setName(mitglied.getName());
		this.setVorname(mitglied.getVorname());
		this.setEintritt(mitglied.getEintritt());
		this.setStrasseNr(mitglied.getStrasseNr());
		this.setPlz(mitglied.getPlz());
		this.setOrt(mitglied.getOrt());
		this.setTelefonDienst(mitglied.getTelefonDienst());
		this.setTelefonPrivatFest(mitglied.getTelefonPrivatFest());
		this.setTelefonPrivatMobil(mitglied.getTelefonPrivatMobil());
		this.seteMail(mitglied.geteMail());
		this.setGebDatum(mitglied.getGebDatum());
		this.setStatus(mitglied.getStatus());
		this.setEintritt(mitglied.getEintritt());
		
	}
	@Transient
	public void addPropertyChangeListener(PropertyChangeListener x) {
		changeSupport.addPropertyChangeListener(x);
	}

	@Transient
	public void removePropertyChangeListener(PropertyChangeListener x) {
		changeSupport.removePropertyChangeListener(x);
	}
	
	public void setName(String name) {
		String old = super.getName();
		super.setName(name);
		System.out.println(super.getName());
		changeSupport.firePropertyChange("name", old, name);
	}

	public void setVorname(String vorname) {
		String old = super.getVorname();
		super.setVorname(vorname);
		System.out.println(super.getVorname());
		changeSupport.firePropertyChange("vorname", old, vorname);
	}
	
	public void setStrasseNr(String strasseNr){
		String old = super.getStrasseNr();
		super.setStrasseNr(strasseNr);
		System.out.println(super.getStrasseNr());
		changeSupport.firePropertyChange("strasseNr", old, strasseNr);
	}
	
	public void setPlz(int plz){
		int old = super.getPlz();
		super.setPlz(plz);
		changeSupport.firePropertyChange("plz", old, plz);
	}
	
	public void setOrt(String ort){
		String old = super.getOrt();
		super.setOrt(ort);
		changeSupport.firePropertyChange("ort", old, ort);
	}
	
	public void setTelefonDienst(String telefon){
		String old = super.getTelefonDienst();
		super.setTelefonDienst(telefon);
		changeSupport.firePropertyChange("telefonDienst", old, telefon);
	}

	public void setTelefonPrivatFest(String telefon){
		String old = super.getTelefonPrivatFest();
		super.setTelefonPrivatFest(telefon);
		changeSupport.firePropertyChange("telefonPrivatFest", old, telefon);
	}
	
	public void setTelefonPrivatMobil(String telefon){
		String old = super.getTelefonPrivatMobil();
		super.setTelefonPrivatMobil(telefon);
		changeSupport.firePropertyChange("telefonPrivatMobil", old, telefon);
	}
	
	public void seteMail(String email){
		String old = super.geteMail();
		super.seteMail(email);
		changeSupport.firePropertyChange("eMail", old, email);
	}
	
	public void setGebDatum(Date gebDatum){
		Date old = super.getGebDatum();
		super.setGebDatum(gebDatum);
		changeSupport.firePropertyChange("gebDatum", old, gebDatum);
	}

	public void setStatus(MitgliedStatus status){
		MitgliedStatus old = super.getStatus();
		super.setStatus(status);
		changeSupport.firePropertyChange("status", old, status);
	}

	public void setEintritt (Date eintritt){
		Date old = super.getEintritt();
		super.setEintritt(eintritt);
		changeSupport.firePropertyChange("eintritt", old, eintritt);
	}
	
	public List<MitgliedStatus> getMitgliedStatuse() {
		
		mitgliedStatuse =  DAOFactory.getInstance().getMitgliedStatusDAO().findByQueryString("From MitgliedStatus a");
		return mitgliedStatuse;
	}


}

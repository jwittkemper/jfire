package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.beans.PropertyChangeListener;

import javax.persistence.Transient;

import com.jgoodies.binding.beans.ExtendedPropertyChangeSupport;

import biz.wittkemper.jfire.data.entity.Mitglied;

public class MitgliedModel extends Mitglied {

	private ExtendedPropertyChangeSupport changeSupport;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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

}

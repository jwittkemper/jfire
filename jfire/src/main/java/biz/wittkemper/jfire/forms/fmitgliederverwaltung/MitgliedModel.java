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

}

package biz.wittkemper.jfire.forms.fmitgliedersearch;

import com.jgoodies.binding.beans.ExtendedPropertyChangeSupport;

import biz.wittkemper.jfire.data.entity.Mitglied;

public class MitgliederSearchModel extends Mitglied {

	private static final long serialVersionUID = 1756613435340335567L;
	private ExtendedPropertyChangeSupport changeSupport;
	
	public MitgliederSearchModel(){
		changeSupport = new ExtendedPropertyChangeSupport(this);
	}
	
	public MitgliederSearchModel(Mitglied mitglied){
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
		this.setId(mitglied.getId());
	}
}

package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.HibernateSession;
import biz.wittkemper.jfire.data.entity.Anrede;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;
import biz.wittkemper.jfire.data.entity.Mitglied_Fuehrerschein;

import com.jgoodies.binding.beans.ExtendedPropertyChangeSupport;

public class MitgliedModel {
	private Mitglied mitglied = new Mitglied();

	String name;
	String vorname;
	Date eintritt;
	String email;
	Date gebDatum;

	private final ExtendedPropertyChangeSupport changeSupport;

	private List<MitgliedStatus> mitgliedStatuse = new ArrayList<MitgliedStatus>();
	private List<Anrede> anreden = new ArrayList<Anrede>();
	private List<Mitglied_Fuehrerschein> fuehrerscheine = new ArrayList<Mitglied_Fuehrerschein>();

	private FoerderMitglied foerderMitglied;

	private Anrede anrede;

	private MitgliedStatus status;

	private String strasseNr;

	private long plz;

	private String ort;

	private String telefondienst;

	private String telefonPrivatFest;

	private String telefonPrivatMobil;

	private boolean rettungsdienst;

	private boolean datenfreigabe;

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
		this.setAnrede(mitglied.getAnrede());
		this.setRettungsdienst(mitglied.isRettungsdienst());
		this.setDatenfreigabe(mitglied.isDatenfreigabe());
		this.setFuehrerscheine(mitglied.getFuehrerscheins());
		if (this.fuehrerscheine.size() > 0) {
			System.out.println(fuehrerscheine.get(0).getFuehrerschein()
					.getBezeichnung());
		}
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
		mitglied.setPlz((int) plz);
		mitglied.setStrasseNr(strasseNr);
		mitglied.setTelefonDienst(telefondienst);
		mitglied.setTelefonPrivatFest(telefonPrivatFest);
		mitglied.setTelefonPrivatMobil(telefonPrivatMobil);
		mitglied.setStatus(status);
		mitglied.setAnrede(anrede);
		mitglied.setRettungsdienst(rettungsdienst);
		mitglied.setDatenfreigabe(datenfreigabe);
		mitglied.setFuehrerscheins(fuehrerscheine);
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

	public void setPlz(long plz) {
		long old = this.plz;
		this.plz = plz;
		changeSupport.firePropertyChange("plz", old, plz);
	}

	public long getPlz() {
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
		this.status = status;
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

	public Anrede getAnrede() {
		return anrede;
	}

	public void setAnrede(Anrede anrede) {
		Anrede old = anrede;
		this.anrede = anrede;
		changeSupport.firePropertyChange("anrede", old, anrede);
	}

	public List<Anrede> getAnreden() {
		HibernateSession.beginTransaction();
		anreden = DAOFactory.getInstance().getAnredeDAO()
				.findByQueryString("From Anrede a");
		HibernateSession.commitTransaction();
		return anreden;
	}

	public List<MitgliedStatus> getMitgliedStatuse() {
		HibernateSession.beginTransaction();
		mitgliedStatuse = DAOFactory.getInstance().getMitgliedStatusDAO()
				.findByQueryString("From MitgliedStatus a");
		HibernateSession.commitTransaction();
		return mitgliedStatuse;
	}

	public FoerderMitglied getFoerderMitglied() {
		return foerderMitglied;
	}

	public void setFoerderMitglied(FoerderMitglied foerderMitglied) {
		this.foerderMitglied = foerderMitglied;
		changeSupport.firePropertyChange("foerderMitgliedSeit", null, null);
	}

	public Date getFoerderMitgliedSeit() {
		if (foerderMitglied != null) {
			return foerderMitglied.getEintritt();
		} else {
			return null;
		}
	}

	public void setFoerderMitgliedSeit(Date foerderMitgliedSeit) {
		Date old = foerderMitgliedSeit;
		this.foerderMitglied.setEintritt(foerderMitgliedSeit);
		changeSupport.firePropertyChange("foerderMitgliedSeit", old,
				foerderMitgliedSeit);
	}

	public boolean isRettungsdienst() {
		return rettungsdienst;
	}

	public void setRettungsdienst(boolean rettungsdienst) {
		boolean old = this.rettungsdienst;
		this.rettungsdienst = rettungsdienst;
		changeSupport.firePropertyChange("rettungsdienst", old, rettungsdienst);
		this.rettungsdienst = rettungsdienst;
	}

	public boolean isDatenfreigabe() {
		return datenfreigabe;
	}

	public void setDatenfreigabe(boolean datenfreigabe) {
		boolean old = this.datenfreigabe;
		this.datenfreigabe = datenfreigabe;
		changeSupport.firePropertyChange("datenfreigabe", old, datenfreigabe);
	}

	public String getTelefondienst() {
		return telefondienst;
	}

	public void setTelefondienst(String telefondienst) {
		String old = this.telefondienst;
		this.telefondienst = telefondienst;
		changeSupport.firePropertyChange("telefonDienst", old, telefondienst);
	}

	public List<Mitglied_Fuehrerschein> getFuehrerscheine() {
		return fuehrerscheine;
	}

	public void setFuehrerscheine(List<Mitglied_Fuehrerschein> fuehrerscheine) {
		this.fuehrerscheine = fuehrerscheine;
	}

}

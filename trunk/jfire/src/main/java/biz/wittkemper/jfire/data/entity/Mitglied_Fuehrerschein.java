package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
// @Table(name = "Mitglied_Fuehrerschein", uniqueConstraints = {
// @UniqueConstraint(columnNames = {
// "ID", "fid" }) })
@AssociationOverrides({
		@AssociationOverride(name = "mfID.mitglied", joinColumns = @JoinColumn(name = "ID")),
		@AssociationOverride(name = "mfID.fueherschein", joinColumns = @JoinColumn(name = "fid")) })
public class Mitglied_Fuehrerschein implements Serializable {

	private static final long serialVersionUID = -247492957682215780L;

	private MitgliedFuehrerscheinID mfID = new MitgliedFuehrerscheinID();
	private Date befristetBis;

	public Date getBefristetBis() {
		return befristetBis;
	}

	public void setBefristetBis(Date befristetBis) {
		this.befristetBis = befristetBis;
	}

	@EmbeddedId
	public MitgliedFuehrerscheinID getMfID() {
		return mfID;
	}

	public void setMfID(MitgliedFuehrerscheinID mfID) {
		this.mfID = mfID;
	}

	@Transient
	public Mitglied getMitglied() {
		return mfID.getMitglied();
	}

	public void setMitglied(Mitglied mitglied) {
		mfID.setMitglied(mitglied);
	}

	@Transient
	public Fuehrerschein getFuehrerschein() {
		return mfID.getFueherschein();
	}

	public void setFuehrerschein(Fuehrerschein fueherschein) {
		mfID.setFueherschein(fueherschein);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Mitglied_Fuehrerschein that = (Mitglied_Fuehrerschein) o;

		if (getMfID() != null ? !getMfID().equals(that.getMfID()) : that
				.getMfID() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (getMfID() != null ? getMfID().hashCode() : 0);
	}
}

package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class MitgliedFuehrerscheinID implements Serializable {

	private static final long serialVersionUID = -8910449139199983767L;

	private Mitglied mitglied;
	private Fuehrerschein fueherschein;

	@ManyToOne
	public Mitglied getMitglied() {
		return mitglied;
	}

	public void setMitglied(Mitglied mitglied) {
		this.mitglied = mitglied;
	}

	@ManyToOne
	public Fuehrerschein getFueherschein() {
		return fueherschein;
	}

	public void setFueherschein(Fuehrerschein fueherschein) {
		this.fueherschein = fueherschein;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MitgliedFuehrerscheinID that = (MitgliedFuehrerscheinID) o;

		if (mitglied != null ? !mitglied.equals(that.mitglied)
				: that.mitglied != null)
			return false;
		if (fueherschein != null ? !fueherschein.equals(that.fueherschein)
				: that.fueherschein != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = (mitglied != null ? mitglied.hashCode() : 0);
		result = 31 * result
				+ (fueherschein != null ? fueherschein.hashCode() : 0);
		return result;
	}
}

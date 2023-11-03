package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class FoerderMitglied implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3108910777991630866L;
	Mitglied mitglied;
	Date eintritt;

	@ManyToOne
	@PrimaryKeyJoinColumn
	@Id
	public Mitglied getMitglied() {
		return mitglied;
	}

	public void setMitglied(Mitglied mitglied) {
		this.mitglied = mitglied;
	}

	public Date getEintritt() {
		return eintritt;
	}

	public void setEintritt(Date eintritt) {
		this.eintritt = eintritt;
	}

}

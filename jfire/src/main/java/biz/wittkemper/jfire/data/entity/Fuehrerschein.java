package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Fuehrerschein implements Serializable {

	private static final long serialVersionUID = 1763457562100906476L;

	private Long fid;
	private String bezeichnung;
	private boolean befristet;

	private Set<Mitglied_Fuehrerschein> fuehrerscheine = new HashSet<Mitglied_Fuehrerschein>(
			0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public boolean isBefristet() {
		return befristet;
	}

	public void setBefristet(boolean befristet) {
		this.befristet = befristet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mfID.fueherschein")
	public Set<Mitglied_Fuehrerschein> getFuehrerscheine() {
		return fuehrerscheine;
	}

	public void setFuehrerscheine(Set<Mitglied_Fuehrerschein> fuehrerscheine) {
		this.fuehrerscheine = fuehrerscheine;
	}

	@Column(length = 20)
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}

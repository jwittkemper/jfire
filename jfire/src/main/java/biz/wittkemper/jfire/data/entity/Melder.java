package biz.wittkemper.jfire.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("Melder")
@Proxy(lazy = false)
public class Melder extends Material {

	private static final long serialVersionUID = 6020960045570050916L;

	String seriennummer;
	MelderTyp melderTyp;
	List<MelderSchleifen> schleifen = new ArrayList<MelderSchleifen>();

	public String getSeriennummer() {
		return seriennummer;
	}

	public void setSeriennummer(String seriennummer) {
		this.seriennummer = seriennummer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATERIALTYP_ID")
	public MelderTyp getMelderTyp() {
		return melderTyp;
	}

	public void setMelderTyp(MelderTyp melderTyp) {
		this.melderTyp = melderTyp;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Melder_Schleifen", joinColumns = { @JoinColumn(name = "MATERIAL_ID") }, inverseJoinColumns = { @JoinColumn(name = "SCHLEIFE_ID") })
	public List<MelderSchleifen> getSchleifen() {
		return schleifen;
	}

	public void setSchleifen(List<MelderSchleifen> schleifen) {
		this.schleifen = schleifen;
	}

}

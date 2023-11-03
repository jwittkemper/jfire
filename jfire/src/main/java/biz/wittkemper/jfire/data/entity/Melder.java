package biz.wittkemper.jfire.data.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Melder")
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

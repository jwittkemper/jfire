package biz.wittkemper.jfire.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Melder")
public class Melder extends Material {

	private static final long serialVersionUID = 6020960045570050916L;

	String seriennummer;
	List<MelderSchleifen> schleifen = new ArrayList<MelderSchleifen>();

	public String getSeriennummer() {
		return seriennummer;
	}

	public void setSeriennummer(String seriennummer) {
		this.seriennummer = seriennummer;
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

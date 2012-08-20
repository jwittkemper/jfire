package biz.wittkemper.jfire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Melder")
public class Melder extends Material {

	private static final long serialVersionUID = 6020960045570050916L;

	String seriennummer;

	public MelderTyp getMelderTyp() {
		return (MelderTyp) super.materialTyp;
	}

	public void setMelderTyp(MelderTyp melderTyp) {
		super.materialTyp = melderTyp;
	}

	public String getSeriennummer() {
		return seriennummer;
	}

	public void setSeriennummer(String seriennummer) {
		this.seriennummer = seriennummer;
	}

}

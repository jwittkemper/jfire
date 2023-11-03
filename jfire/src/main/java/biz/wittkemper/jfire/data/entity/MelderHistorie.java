package biz.wittkemper.jfire.data.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Melder")
public class MelderHistorie extends Historie {

	private static final long serialVersionUID = 1L;

	Melder melder;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHLUESSEL", nullable = false)
	public Melder getMelder() {
		return melder;
	}

	public void setMelder(Melder melder) {
		this.melder = melder;
	}
}

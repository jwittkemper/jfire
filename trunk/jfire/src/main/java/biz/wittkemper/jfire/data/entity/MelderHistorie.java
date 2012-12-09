package biz.wittkemper.jfire.data.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("Melder")
@Proxy(lazy = false)
public class MelderHistorie extends Historie {

	private static final long serialVersionUID = 1L;

	Melder melder;

	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "SCHLUESSEL", nullable = false)
	public Melder getMelder() {
		return melder;
	}

	public void setMelder(Melder melder) {
		this.melder = melder;
	}
}

package biz.wittkemper.jfire.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("MeldeempfaengerTyp")
@Proxy(lazy = false)
public class MelderTyp extends MaterialTyp {
	private static final long serialVersionUID = 4168446762580004575L;

}

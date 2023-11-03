package biz.wittkemper.jfire.data.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MeldeempfaengerTyp")
public class MelderTyp extends MaterialTyp {
	private static final long serialVersionUID = 4168446762580004575L;

}

package biz.wittkemper.jfire.data.validation;

import biz.wittkemper.jfire.data.entity.Mitglied;

import com.jgoodies.binding.value.AbstractValueModel;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.ValidationUtils;

public class MitgliedValidator extends AbstractValueModel implements Validator<Mitglied> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6798067395722218711L;
	private Mitglied mitglied = new Mitglied();
	
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this.mitglied;
	}

	@Override
	public void setValue(Object mitglied) {
		if (mitglied instanceof Mitglied){
			this.mitglied = (Mitglied) mitglied;
		}
	}

	@Override
	public ValidationResult validate(Mitglied mitglied) {
		ValidationResult result = new ValidationResult();
		
		if(ValidationUtils.isBlank(mitglied.getName())){
			result.addError("Name ist leer!");
		}
		
		if(ValidationUtils.isBlank(mitglied.getVorname())){
			result.addError("Vorname ist leer!");
		}
		
		return result;
	}

}

package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Material implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1141320439080401772L;
	Long id;
	MaterialTyp materialTyp;
	Date uebernahme;
	long masterID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATERIAL_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUebernahme() {
		return uebernahme;
	}

	public void setUebernahme(Date uebernahme) {
		this.uebernahme = uebernahme;
	}

	public long getMasterID() {
		return masterID;
	}

	public void setMasterID(long masterID) {
		this.masterID = masterID;
	}

}

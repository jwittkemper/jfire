package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( name = "" )
@XmlRootElement(name = "MitgliedStatus")
@Entity
public class MitgliedStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9084034101911136383L;

	Integer id;
	String bezeichnungLang;
	String bezeichnungKurz;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length=30)
	public String getBezeichnungLang() {
		return bezeichnungLang;
	}

	public void setBezeichnungLang(String bezeichnungLang) {
		this.bezeichnungLang = bezeichnungLang;
	}

	@Column(length=30)
	public String getBezeichnungKurz() {
		return bezeichnungKurz;
	}

	public void setBezeichnungKurz(String bezeichnungKurz) {
		this.bezeichnungKurz = bezeichnungKurz;
	}
	
	@Transient
	public String toString(){
		return this.bezeichnungLang;
	}
}

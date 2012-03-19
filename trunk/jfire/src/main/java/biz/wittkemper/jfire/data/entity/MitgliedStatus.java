package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Proxy;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( name = "" )
@XmlRootElement(name = "MitgliedStatus")
@Entity
@Proxy(lazy = false)
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

package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( name = "" )
@XmlRootElement(name = "Anrede")
@Entity
public class Anrede implements Serializable{

	private static final long serialVersionUID = -8051272073300938729L;
	int id;
	String anrede;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length=20)
	public String getAnrede() {
		return this.anrede;
	}
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	
	public String toString(){
		return this.getAnrede();
	}
}

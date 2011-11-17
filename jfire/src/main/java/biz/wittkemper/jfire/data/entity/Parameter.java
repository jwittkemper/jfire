package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class Parameter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146673813401414814L;
	
	Long id;
	Long masterid;
	String bezeichnung;
	String value;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length=100)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(length=100)
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public Long getMasterid() {
		return masterid;
	}
	public void setMasterid(Long masterid) {
		this.masterid = masterid;
	}
	
}

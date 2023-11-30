package biz.wittkemper.jfire.data.entity;

import java.io.Serializable;

public class Lehrgang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -500224017602081250L;
	
	int id;
	String bezeichnung;
	boolean agt=false;
	int laufbahnrank;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public boolean isAgt() {
		return agt;
	}
	public void setAgt(boolean agt) {
		this.agt = agt;
	}
	
	public int getLaufbahnrank() {
		return laufbahnrank;
	}
	public void setLaufbahnrank(int laufbahnrank) {
		this.laufbahnrank = laufbahnrank;
	}
	
	@Override
	public String toString() {
		return "Lehrgang [bezeichnung=" + bezeichnung + "]";
	}
	
	
}

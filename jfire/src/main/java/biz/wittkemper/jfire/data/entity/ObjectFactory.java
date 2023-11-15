package biz.wittkemper.jfire.data.entity;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {
	private final static QName Q_NAME = new QName("", "Replication");
	
	public ObjectFactory(){
	}
	
	
	public Mitglied createMitglied(){
		return new Mitglied();
	}
	
	public Anrede createAnrede(){
		return new Anrede();
	}
	
	public MitgliedStatus createMitgliedStatus(){
		return new MitgliedStatus();
	}
	
	public FoerderMitglied createFoerderMitglied(){
		return new FoerderMitglied();
	}
	@XmlElementDecl(namespace="", name="Replication")
	public JAXBElement<Replication> createReplication(Replication repl){
		return new JAXBElement<Replication>(Q_NAME, Replication.class,null, repl);
	}

}

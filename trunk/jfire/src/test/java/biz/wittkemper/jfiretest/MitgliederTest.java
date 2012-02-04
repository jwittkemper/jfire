package biz.wittkemper.jfiretest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Mitglied;

public class MitgliederTest {

	@Test
	public void test() {
		
		String suchliste = "Witt";
		
		List<Mitglied> list = DAOFactory.getInstance().getMitgliedDAO().searchByName(suchliste.split(","));
		
		for(Mitglied mg: list){
			System.out.println(mg.getId() + " " + mg.getVorname() + " " + mg.getName());
		}
	}

}

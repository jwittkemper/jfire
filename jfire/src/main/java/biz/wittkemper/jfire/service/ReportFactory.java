package biz.wittkemper.jfire.service;

import biz.wittkemper.jfire.data.entity.Report;
import biz.wittkemper.jfire.service.ReportService.REPORTS;

public class ReportFactory {
	static Report foerderMitglieder = new Report("Foerderverein","Mitgliederliste Förderverein",false,"reports/mitgliederFoerderverein.jrxml", "");
	static Report telefonlisteAktiv = new Report("Telefonliste", "Telefonliste (Löschzug)", true,"reports/Telefonliste.jrxml", "1");
	static Report telefonlisteReserve = new Report("Telefonliste", "Telefonliste (Reservezug)", true,"reports/Telefonliste.jrxml", "2");
	static Report anwenheitsListe = new Report("Uebungsliste", "Anwesenheitsliste Übung", true, "reports/AnwesenheitUebung.jrxml", "1");
	
	public static Report getReport(REPORTS name){
		Report lreport = null;
		switch(name){
		case MITGLIEDERFOERDERVEREIN: lreport = foerderMitglieder;
			break;
		case TELEFONLISTEAKTIVE: lreport = telefonlisteAktiv;
			break;
		case TELEFONRESERVE: lreport = telefonlisteReserve;
			break;
		case ANWESENHEIUEBUNG: lreport = anwenheitsListe;
			break;
		}
		
		return lreport;
	}
}

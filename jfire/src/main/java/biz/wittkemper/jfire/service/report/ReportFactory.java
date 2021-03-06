package biz.wittkemper.jfire.service.report;

import biz.wittkemper.jfire.data.entity.Report;
import biz.wittkemper.jfire.service.report.ReportService.REPORTS;

public class ReportFactory {
	static Report foerderMitglieder = new Report("Foerderverein",
			"Mitgliederliste Förderverein", false,
			"reports/mitgliederFoerderverein.jrxml", "");
	static Report telefonlisteAktiv = new Report("Telefonliste",
			"Telefonliste (Löschzug)", true, "reports/Telefonliste.jrxml", "1");
	static Report telefonlisteReserve = new Report("Telefonliste",
			"Telefonliste (Reservezug)", true, "reports/Telefonliste.jrxml",
			"2");
	static Report anwenheitsListe = new Report("Uebungsliste",
			"Anwesenheitsliste Übung", true, "reports/AnwesenheitUebung.jrxml",
			"1");
	static Report jubilaeumsListe = new Report("Jubilaeumsliste", "", true,
			"reports/Dienstjubiläen.jrxml", "1");
	static Report aktiveMitglieder = new Report("AktiveMitglieder",
			"Mitgliederliste Feuerwehr", false,
			"reports/mitgliederAktive.jrxml", "");
	static Report anwenheitsEinsatz = new Report("Einsatzliste",
			"Anwesenheitsliste Einsatz", true,
			"reports/AnwesenheitEinsatz.jrxml", "1");

	static Report Mitgliederliste = new Report("Adressliste", "Adressliste",
			true, "reports/Mitglieder_Freigabe.jrxml", "");

	public static Report getReport(REPORTS name) {
		Report lreport = null;
		switch (name) {
		case MITGLIEDERFOERDERVEREIN:
			lreport = foerderMitglieder;
			break;
		case TELEFONLISTEAKTIVE:
			lreport = telefonlisteAktiv;
			break;
		case TELEFONRESERVE:
			lreport = telefonlisteReserve;
			break;
		case ANWESENHEIUEBUNG:
			lreport = anwenheitsListe;
			break;
		case JUBILAEUMSLISTE:
			lreport = jubilaeumsListe;
			break;
		case MITGLIEDERAKTIVE:
			lreport = aktiveMitglieder;
			break;
		case ANWESENHEITEINSATZ:
			lreport = anwenheitsEinsatz;
			break;
		case MITGLIEDERLISTE:
			lreport = Mitgliederliste;
		}

		return lreport;
	}
}

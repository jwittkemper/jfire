package biz.wittkemper.jfiretest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Anrede;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.MitgliedStatus;
import biz.wittkemper.jfire.utils.DateUtils;

public class ExcelImport {

	@Test
	public void test() throws IOException {

		Anrede frau = new Anrede();
		frau.setAnrede("Frau");

		Anrede herr = new Anrede();
		herr.setAnrede("Herr");
		DAOFactory.getInstance().getAnredeDAO().save(frau);
		DAOFactory.getInstance().getAnredeDAO().save(herr);

		MitgliedStatus statusAL = new MitgliedStatus();
		statusAL.setBezeichnungLang("Aktiv Löschzug");
		statusAL.setBezeichnungKurz("AL");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusAL);

		MitgliedStatus statusAR = new MitgliedStatus();
		statusAR.setBezeichnungLang("Aktiv Reservezug");
		statusAR.setBezeichnungKurz("AR");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusAR);

		MitgliedStatus statusPV = new MitgliedStatus();
		statusPV.setBezeichnungKurz("PS");
		statusPV.setBezeichnungLang("Passives Mitglied");
		DAOFactory.getInstance().getMitgliedStatusDAO().save(statusPV);

		JFileChooser chooser = new JFileChooser();

		int ret = chooser.showDialog(null, "übernehmen");

		if (ret == JFileChooser.APPROVE_OPTION) {
			InputStream filein = new FileInputStream(chooser.getSelectedFile());

			HSSFWorkbook wb = new HSSFWorkbook(filein);

			for (int id = 0; id < 10; id++) {
				if (id == 0 || id == 1) {
					HSSFSheet sheet = wb.getSheetAt(id);

					int max = sheet.getLastRowNum();

					for (int i = 1; i <= max; i++) {
						HSSFRow row = sheet.getRow(i);

						Mitglied mitglied = getMitgliedFromRow(row);
						mitglied.setAnrede(herr);
						if (id == 0) {
							mitglied.setStatus(statusAL);
						} else if (id == 1) {
							mitglied.setStatus(statusAR);
						}
						if (mitglied.getName().length() > 0) {
							DAOFactory.getInstance().getMitgliedDAO()
									.save(mitglied);
						}

					}
				}
			}
			inputFoerderVerein(wb.getSheetAt(2), statusPV);
		}
	}

	private void inputFoerderVerein(HSSFSheet sheet, MitgliedStatus status) {

		int max = sheet.getLastRowNum();
		System.out.println(sheet.toString());
		for (int i = 1; i <= max; i++) {
			HSSFRow row = sheet.getRow(i);
			System.out.println("ZEILE " + i );
			Mitglied mitglied = getFoerderMitgliedFromRow(row);
			Mitglied md = mitgliedExist(mitglied);
			if (md != null) {
				FoerderMitglied foerderMitglied = new FoerderMitglied();
				foerderMitglied.setMitglied(md);
				foerderMitglied.setEintritt(mitglied.getEintritt());
				DAOFactory.getInstance().getFoerderMitgliedDAO()
						.save(foerderMitglied);
			} else {
				mitglied.setStatus(status);
				DAOFactory.getInstance().getMitgliedDAO().save(mitglied);
				FoerderMitglied foerderMitglied = new FoerderMitglied();
				foerderMitglied.setMitglied(mitglied);
				foerderMitglied.setEintritt(mitglied.getEintritt());
				DAOFactory.getInstance().getFoerderMitgliedDAO()
						.save(foerderMitglied);
			}
		}

	}

	private Mitglied mitgliedExist(Mitglied mitglied) {
		Mitglied md = DAOFactory.getInstance().getMitgliedDAO()
				.findByNameVorname(mitglied.getName(), mitglied.getVorname());

		if (md != null) {
			if (md.getAnrede().getId() != mitglied.getAnrede().getId()) {
				md.setAnrede(mitglied.getAnrede());
				DAOFactory.getInstance().getMitgliedDAO().merge(md);
			}

			return md;
		}
		return null;
	}

	private Mitglied getFoerderMitgliedFromRow(HSSFRow row) {
		Mitglied mitglied = new Mitglied();
		for (int j = 1; j <= 11; j++) {
			switch (j) {

			case 1:
				mitglied.setAnrede(getAnrede(row.getCell(j)));
				System.out.println("Name: " + mitglied.getName());
				break;
			case 2:
				mitglied.setName(row.getCell(j).toString());
				break;
			case 3:
				mitglied.setVorname(row.getCell(j).toString());
				break;
			case 4:
				try {
					mitglied.setStrasseNr(row.getCell(j).toString());
				} catch (Exception e) {
					mitglied.setStrasseNr("");
				}
				break;
			case 5:
				String plz = row.getCell(j).toString().substring(0, 5);

				mitglied.setPlz(Integer.parseInt(plz));
				break;

			case 6:
				mitglied.setOrt(row.getCell(j).toString());
				break;
			case 7:
				try {
					String datum = row.getCell(j).toString();

					mitglied.setGebDatum(DateUtils.getDate(datum));
				} catch (Exception e) {
					mitglied.setGebDatum(null);
				}
				break;
			case 8:
				try {
					if (null != row.getCell(j).toString()) {
						mitglied.setEintritt(DateUtils.getDate(row.getCell(j)
								.toString()));
					}
				} catch (Exception e) {
					mitglied.setEintritt(null);
					System.out.println(e);
				}
				break;
			case 9:
				try {
					String telefon = row.getCell(j).toString();
					String[] tel = telefon.split("/");
					mitglied.setTelefonPrivatFest(tel[0].trim());
					if (tel.length > 1)
						mitglied.setTelefonPrivatMobil(tel[1].trim());
				} catch (Exception e) {
					mitglied.setTelefonPrivatMobil("");
					mitglied.setTelefonPrivatFest("");
				}
				break;
			case 10:
				try {
					if (null != row.getCell(j).toString()) {
						mitglied.setTelefonDienst(row.getCell(j).toString()
								.trim());
					}
				} catch (Exception e) {
					mitglied.setTelefonDienst("");
				}
				break;

			}

		}
		return mitglied;
	}

	private Anrede getAnrede(HSSFCell cell) {
		String text = cell.toString();

		return DAOFactory.getInstance().getAnredeDAO().getAnredeByText(text);
	}

	private Mitglied getMitgliedFromRow(HSSFRow row) {
		Mitglied mitglied = new Mitglied();

		for (int j = 0; j <= 11; j++) {
			switch (j) {

			case 0:
				mitglied.setName(row.getCell(j).toString());
				System.out.println(mitglied.getName());
				break;
			case 1:
				mitglied.setVorname(row.getCell(j).toString());
				break;
			case 2:
				mitglied.setStrasseNr(row.getCell(j).toString());
				break;
			case 3:
				mitglied.setPlz(59071);
				break;
			case 4:
				mitglied.setOrt("Hamm");
				break;

			case 7:
				String datum = row.getCell(j).toString();

				mitglied.setGebDatum(DateUtils.getDate(datum));
				break;
			case 8:
				String telefon = row.getCell(j).toString();
				String[] tel = telefon.split("/");
				mitglied.setTelefonPrivatFest(tel[0].trim());
				if (tel.length > 1)
					mitglied.setTelefonPrivatMobil(tel[1].trim());
				break;
			case 9:
				if (null != row.getCell(j).toString()) {
					mitglied.setTelefonDienst(row.getCell(j).toString().trim());
				}
				break;
			case 10:
				String mail = row.getCell(j).toString();
				if (mail != null) {
					mitglied.seteMail(mail);
				}
				break;
			case 11:
				if (null != row.getCell(j).toString()) {
					mitglied.setEintritt(DateUtils.getDate(row.getCell(j)
							.toString()));
				}
				break;
			}

		}
		mitglied.setGeloescht(false);
		return mitglied;
	}

}

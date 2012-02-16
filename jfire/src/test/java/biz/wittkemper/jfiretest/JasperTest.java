package biz.wittkemper.jfiretest;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Transaction;
import org.junit.Ignore;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.SessionFactotyUtil;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.service.ReportService;
import biz.wittkemper.jfire.service.ReportService.REPORTS;

public class JasperTest {

	@Ignore
	public void test() {
		URL url = ClassLoader
				.getSystemResource("reports/mitgliederFoerderverein.jrxml");
		File file = new File(url.getFile());

		Mitglied mitglied = DAOFactory.getInstance().getMitgliedDAO().load(12L);
		System.out.println(mitglied.getName());

		try {
			JasperDesign jasperDesign = JRXmlLoader.load(file);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);

			Transaction tx = SessionFactotyUtil.getInstance()
					.getCurrentSession().beginTransaction();
			Connection con = SessionFactotyUtil.getInstance()
					.getCurrentSession().connection();

			Map p = new HashMap();

			p.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, p, con);

			File destFile = new File(file.getParent(), jasperPrint.getName()
					+ ".xls");

			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					destFile.toString());
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.exportReport();

			// JasperViewer.viewReport(jasperPrint);

			tx.commit();
			// File destFile = new File(file.getParent(), jasperPrint.getName()
			// + ".xlsx");

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Ignore
	public void view(){
		try {
			ReportService.showReport(REPORTS.MITGLIEDERFOERDERVEREIN, null);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

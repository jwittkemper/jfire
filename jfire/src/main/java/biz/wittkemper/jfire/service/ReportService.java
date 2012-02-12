package biz.wittkemper.jfire.service;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

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
import net.sf.jasperreports.view.JasperViewer;

import org.hibernate.Transaction;
import org.jfree.ui.ExtensionFileFilter;

import biz.wittkemper.jfire.data.dao.SessionFactotyUtil;
import biz.wittkemper.jfire.utils.DateUtils;

public class ReportService {
	public enum REPORTS {
		MITGLIEDERFOERDERVEREIN
	}

	public static void showReport(REPORTS name) throws JRException {
		String report = "";
		Map p = new HashMap();
		p.put("STAND", DateUtils.getCurDateString());
		switch (name) {
		case MITGLIEDERFOERDERVEREIN:
			report = "mitgliederFoerderverein.jrxml";
			break;
		}
//		URL url = ClassLoader.getSystemResource("reports/" + report);
		InputStream stream = ClassLoader.getSystemResourceAsStream("reports/" + report);
		

		JasperDesign jasperDesign = JRXmlLoader.load(stream);
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);

		Transaction tx = SessionFactotyUtil.getInstance().getCurrentSession()
				.beginTransaction();
		Connection con = SessionFactotyUtil.getInstance().getCurrentSession()
				.connection();

		int res = JOptionPane.showConfirmDialog(null,
				"Wollen sie die Daten sehen und ausdrucken?", "Frage:",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		switch (res) {
		case JOptionPane.YES_OPTION:
			zeigeReport(jasperReport, p, con);
			break;
		case JOptionPane.NO_OPTION:
			ExportReport(jasperReport, p, con);
			break;
		default:
		}
		tx.commit();
	}

	private static void ExportReport(JasperReport jasperReport, Map map,
			Connection con) throws JRException {
		File destFile = new File(getSaveFile());
		
		map.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				map, con);
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);

		exporter.exportReport();
	}

	private static String getSaveFile() {
		JFileChooser fc = new JFileChooser("Datenexport...");
		FileFilter filter1 = new ExtensionFileFilter("Excel", "XLS");
		fc.setFileFilter(filter1);
		String file = "";

		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file += fc.getSelectedFile().getAbsoluteFile() + ".xls";
		}

		return file;
	}

	private static void zeigeReport(JasperReport jasperReport, Map map,
			Connection con) throws JRException {
		map.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
		map.put("Titel", "Mitgliederliste Förderverein");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				map, con);
		JasperViewer view = new JasperViewer(jasperPrint, true);
		JDialog dialog = new JDialog(new JFrame(), "Datenanzeige", true);
		dialog.setSize(1024, 768);
		dialog.setLocationRelativeTo(null);

		dialog.getContentPane().add(view.getContentPane());
		dialog.setVisible(true);

	}
}

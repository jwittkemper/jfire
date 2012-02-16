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
import biz.wittkemper.jfire.data.entity.Report;
import biz.wittkemper.jfire.utils.DateUtils;

public class ReportService {
	public enum REPORTS {
		MITGLIEDERFOERDERVEREIN, TELEFONLISTEAKTIVE, TELEFONRESERVE, ANWESENHEIUEBUNG
	}

	public static void showReport(REPORTS name, Map refMap) throws JRException {

		Report report = ReportFactory.getReport(name);
		Map map = fillMap(report, refMap);

		InputStream stream = ClassLoader.getSystemResourceAsStream(report
				.getFilename());

		JasperDesign jasperDesign = JRXmlLoader.load(stream);
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);

		Transaction tx = SessionFactotyUtil.getInstance().getCurrentSession()
				.beginTransaction();
		Connection con = SessionFactotyUtil.getInstance().getCurrentSession()
				.connection();

		int res;
		if (report.isViewOnly()) {
			res = JOptionPane.YES_OPTION;
		} else {
			Object[] options = { "Liste anzeigen", "Liste exportieren",
					"abbrechen" };
			res = JOptionPane.showOptionDialog(null,
					"Was möchten sie mit der Liste tun?", "Frage",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		}
		switch (res) {
		case JOptionPane.YES_OPTION:
			zeigeReport(jasperReport, map, con);
			break;
		case JOptionPane.NO_OPTION:
			ExportReport(jasperReport, map, con);
			break;
		default:
		}
		tx.commit();
	}

	private static Map fillMap(Report report, Map refMap) {
		Map map;
		if (refMap == null) {
			map = new HashMap();
		}else{
			map = refMap;
		}
		map.put("STAND", DateUtils.getCurDateString());
		map.put("Titel", report.getTitel());
		map.put("Auswahl", report.getSqlWhere());
		return map;
	}

	private static void ExportReport(JasperReport jasperReport, Map map,
			Connection con) throws JRException {

		map.remove("Titel");
		File destFile = new File(getSaveFile());
		if (destFile.getName().length() > 0) {
			map.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, map, con);
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
		}
	}

	private static String getSaveFile() {
		JFileChooser fc = new JFileChooser("Datenexport...");
		FileFilter filter1 = new ExtensionFileFilter("Excel", "XLS");
		fc.setFileFilter(filter1);
		String file = "";

		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file += fc.getSelectedFile().getAbsoluteFile() + ".xls";
		} else {
			file = "";
		}

		return file;
	}

	private static void zeigeReport(JasperReport jasperReport, Map map,
			Connection con) throws JRException {

		map.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
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

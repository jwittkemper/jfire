package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.record.formula.functions.Islogical;
import org.apache.poi.hssf.record.formula.functions.Setvalue;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.view.ValidationComponentUtils;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.validation.MitgliedValidator;
import biz.wittkemper.jfire.forms.fmitgliedersearch.FmitgliederSearrch;
import biz.wittkemper.jfire.forms.fmitgliedersearch.MitgliederSeachControler;
import biz.wittkemper.jfire.utils.FrameUtils;
import biz.wittkemper.jfire.utils.NumberUtils;

public class FMitgliederVerwaltungController {

	enum EDITMODE {
		EDIT, NEW, NONE;
	}

	EDITMODE viewmode = EDITMODE.NONE;

	FrameUtils frameUtils = new FrameUtils();
	NumberUtils numberUtils = new NumberUtils();
	FMitgliederVerwaltungView view;
	MitgliedModel model = new MitgliedModel();

	KeyListener searchKey = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				sucheMitglied();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	};

	public FMitgliederVerwaltungController() {

		try {
			initClass();
			view.enableImput(false);
			view.SetFoerderVerein(false);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initClass() throws PropertyVetoException {

		this.view = new FMitgliederVerwaltungView();
		this.model = view.getModel();
		initListener();
		switchViewMode(EDITMODE.NONE);
	}

	private void loadData(Long id) {
		Mitglied mitglied = DAOFactory.getInstance().getMitgliedDAO().load(id);
		if (mitglied != null) {
			model.setMitglied(mitglied);
			view.setMitgliedLabel("(" + model.getMitglied().getId() + ") "
					+ model.getMitglied().getVorname() + " "
					+ model.getMitglied().getName());

			if (DAOFactory.getInstance().getFoerderMitgliedDAO()
					.load(model.getMitglied().getId()) != null) {
				model.setFoerderMitglied(DAOFactory.getInstance()
						.getFoerderMitgliedDAO()
						.load(model.getMitglied().getId()));
				view.SetFoerderVerein(true);
			} else {
				view.SetFoerderVerein(false);
			}
		} else {
			model.setMitglied(new Mitglied());
			view.setMitgliedLabel("");
			view.SetFoerderVerein(false);
			view.enableImput(false);
		}
	}

	private void switchViewMode(EDITMODE mode) {
		viewmode = mode;
		view.setToolbar(mode);
	}

	private void initListener() {
		this.view.setBeendenListener(new BeendenListener());
		this.view.setSaveListener(new SaveListener());
		this.view.setSeachListener(new SeachListener());
		this.view.setSeachKeyListener(searchKey);
		this.view.setDeleteListener(new DeleteListener());
	}

	public JInternalFrame getFrame() throws PropertyVetoException {
		this.view.show();
		this.view.setSelected(true);

		try {
			this.view.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.view;
	}

	private void sucheMitglied() {
		String lsearch = view.getSearchText().trim();
		if (lsearch.trim() == "") {
			lsearch = "0";
		}
		if (numberUtils.isLongValue(lsearch)) {
			loadData(Long.parseLong(lsearch));
		} else {
			loadByName(lsearch);
		}
		view.repaint();
	}

	private void loadByName(String lsearch) {

		MitgliederSeachControler search = new MitgliederSeachControler();
		search.viewSearchForm(lsearch);
		if (search.getMitglied() != null) {
			loadData(search.getMitglied().getId());
		}

	}

	private void speicherMitglied() {
		view.trigger.triggerCommit();
		if (viewmode == EDITMODE.NEW) {
			DAOFactory.getInstance().getMitgliedDAO()
					.save(this.model.getMitglied());
		} else if (viewmode == EDITMODE.EDIT) {
			DAOFactory.getInstance().getMitgliedDAO()
					.update(this.model.getMitglied());
			if (model.getFoerderMitglied() != null) {
				if (DAOFactory.getInstance().getFoerderMitgliedDAO().EintragDa(model.getMitglied().getId())){
					DAOFactory.getInstance().getFoerderMitgliedDAO().update(model.getFoerderMitglied());	
				}else{
					DAOFactory.getInstance().getFoerderMitgliedDAO().save(model.getFoerderMitglied());
				}
				
			}
		}
	}

	private void sucheNaechstesMitglied(String richtung) {
		long id;
		if (model.getMitglied().getId() == null) {
			id = 0;
		} else {
			id = model.getMitglied().getId();
		}
		if (richtung.equals("left")) {
			loadData(DAOFactory.getInstance().getMitgliedDAO().getPrev(id)
					.getId());
		} else if (richtung.equals("right")) {
			loadData(DAOFactory.getInstance().getMitgliedDAO().getNext(id)
					.getId());
		}
		view.repaint();
	}

	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.getMitglied().getId() > 0) {
				String msg = "Wollen sie das ausgewählte Mitglied wirklich löschen?";

				if (JOptionPane.showConfirmDialog(view, msg, "Frage:",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					Mitglied mitglied = model.getMitglied();
					mitglied.setGeloescht(true);

					DAOFactory.getInstance().getMitgliedDAO().update(mitglied);
					sucheNaechstesMitglied("right");
				}
			}
		}

	}

	class SeachListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("search")) {
				sucheMitglied();
			} else if (e.getActionCommand().equals("left")) {
				sucheNaechstesMitglied("left");
			} else if (e.getActionCommand().equals("right")) {
				sucheNaechstesMitglied("right");
			} else if (e.getActionCommand().equals("edit")) {
				editMitglied();
			}else if (e.getActionCommand().equals("newFoerdermitglied")){
				
				model.setFoerderMitglied(new FoerderMitglied());
				model.getFoerderMitglied().setMitglied(model.getMitglied());
				view.SetFoerderVerein(true);
			}
			view.repaint();
		}

		private void editMitglied() {
			if (model.getMitglied().getId() != null
					&& model.getMitglied().getId() > 0) {
				view.enableImput(true);
				switchViewMode(EDITMODE.EDIT);
			}

		}

	}

	class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.trigger.triggerCommit();
			MitgliedValidator validator = new MitgliedValidator();
			ValidationResult res = validator.validate(model.getMitglied());

			if (res.hasErrors() == false) {
				speicherMitglied();
				switchViewMode(EDITMODE.NONE);
				view.enableImput(false);
			} else {
				ValidationComponentUtils
						.updateComponentTreeMandatoryAndBlankBackground(view);
				ValidationComponentUtils
						.updateComponentTreeMandatoryBackground(view);
				ValidationComponentUtils
						.updateComponentTreeMandatoryBorder(view);

			}

		}

	}

	class BeendenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (viewmode) {
			case EDIT:
				String Meldung = "Wollen sie die Eingabeohne zu speichern abbrechen?";
				if (JOptionPane.showConfirmDialog(view, Meldung, "Frage",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					view.enableImput(false);
					view.trigger.triggerFlush();
					switchViewMode(EDITMODE.NONE);
				}

				break;
			case NONE:
				view.setVisible(false);
				view.dispose();
				break;
			case NEW:
				String md = "Wollen sie die Eingabeohne zu speichern abbrechen?";
				if (JOptionPane.showConfirmDialog(view, md, "Frage",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					view.enableImput(false);
					view.trigger.triggerFlush();
					switchViewMode(EDITMODE.NONE);
				}
				break;
			}

		}

	}

}

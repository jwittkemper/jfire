package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.validation.MitgliedValidator;
import biz.wittkemper.jfire.forms.fmitgliedersearch.MitgliederSeachControler;
import biz.wittkemper.jfire.utils.FrameUtils;
import biz.wittkemper.jfire.utils.NumberUtils;
import biz.wittkemper.jfire.utils.ParameterUtils;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.view.ValidationComponentUtils;

public class FMitgliederVerwaltungController {

	private final Logger logger = LoggerFactory
			.getLogger(FMitgliederVerwaltungController.class);

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
				try {
					sucheMitglied();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			if (ParameterUtils.isMasterDB()) {
				view.setNewUserActice(true);
			} else {
				view.setNewUserActice(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initClass() throws Exception {

		this.view = new FMitgliederVerwaltungView();
		this.model = view.getModel();
		initListener();
		switchViewMode(EDITMODE.NONE);
	}

	private void loadData(Long id) throws Exception {

		if (id != null && id > 0) {
			Mitglied mitglied = DAOFactory.getInstance().getMitgliedDAO()
					.load(id);
			if (mitglied != null) {

				model.setFoerderMitglied(null);
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
					model.setFoerderMitglied(null);
					view.SetFoerderVerein(false);
				}
				model.getTableModel().fireTableDataChanged();
			} else {
				model.setMitglied(new Mitglied());
				view.setMitgliedLabel("");
				view.SetFoerderVerein(false);
				view.enableImput(false);
				model.getTableModel().fireTableDataChanged();
			}

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

	private void sucheMitglied() throws NumberFormatException, Exception {
		String lsearch = view.getSearchText().trim();
		if (lsearch.trim().length() == 0) {
			lsearch = "0";
		}
		if (numberUtils.isLongValue(lsearch)) {
			loadData(Long.parseLong(lsearch));
		} else {
			loadByName(lsearch);
		}
		view.repaint();
	}

	private void loadByName(String lsearch) throws Exception {

		MitgliederSeachControler search = new MitgliederSeachControler();
		search.viewSearchForm(lsearch);
		if (search.getMitglied() != null) {
			loadData(search.getMitglied().getId());
		}

	}

	private void speicherMitglied() throws Exception {
		view.trigger.triggerCommit();
		try {
			if (viewmode == EDITMODE.NEW) {
				DAOFactory.getInstance().getMitgliedDAO()
						.save(this.model.getMitglied());
				loadData(model.getMitglied().getId());
			} else if (viewmode == EDITMODE.EDIT) {
				DAOFactory.getInstance().getMitgliedDAO()
						.update(this.model.getMitglied());
				if (model.getFoerderMitglied() != null) {
					if (DAOFactory.getInstance().getFoerderMitgliedDAO()
							.EintragDa(model.getMitglied().getId())) {
						DAOFactory.getInstance().getFoerderMitgliedDAO()
								.merge(model.getFoerderMitglied());
					} else {
						DAOFactory.getInstance().getFoerderMitgliedDAO()
								.save(model.getFoerderMitglied());
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void sucheNaechstesMitglied(String richtung) throws Exception {
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
			try {
				if (model.getMitglied().getId() > 0) {
					if (!model.getMitglied().isGeloescht()) {
						FMitgliedDelete delete = new FMitgliedDelete();
						delete.setModal(true);
						delete.setVisible(true);
						if (delete.isCloseOK()) {

							Mitglied mitglied = model.getMitglied();
							mitglied.setGeloescht(!mitglied.isGeloescht());
							mitglied.setGeloeschtAM(delete.getAustittsDatum());
							mitglied.setGeloeschtWeil(delete.getLoeschGrunf());
							
							DAOFactory.getInstance().getMitgliedDAO()
									.update(mitglied);

							delete.dispose();
							view.trigger.triggerFlush();
							switchViewMode(EDITMODE.NONE);
							view.trigger.triggerFlush();
							view.enableImput(false);
							view.repaint();
							sucheNaechstesMitglied("right");
						}
					} else {
						Mitglied mitglied = model.getMitglied();
						mitglied.setGeloescht(!mitglied.isGeloescht());
						mitglied.setGeloeschtAM(null);
						mitglied.setGeloeschtWeil(null);
						
						DAOFactory.getInstance().getMitgliedDAO()
								.update(mitglied);
						
						view.trigger.triggerFlush();
						switchViewMode(EDITMODE.NONE);
						view.trigger.triggerFlush();
						view.enableImput(false);
						view.repaint();

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	class SeachListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("search")) {
					e.getSource();
					sucheMitglied();
				} else if (e.getActionCommand().equals("left")) {
					sucheNaechstesMitglied("left");
				} else if (e.getActionCommand().equals("right")) {
					sucheNaechstesMitglied("right");
				} else if (e.getActionCommand().equals("edit")) {
					editMitglied();
				} else if (e.getActionCommand().equals("newFoerdermitglied")) {

					model.setFoerderMitglied(new FoerderMitglied());
					model.getFoerderMitglied().setMitglied(model.getMitglied());
					view.SetFoerderVerein(true);
					view.setNewFoerderMitglied();
				} else if (e.getActionCommand().equals("new")) {
					model.setFoerderMitglied(null);
					model.setMitglied(new Mitglied());
					switchViewMode(EDITMODE.NEW);
					view.enableImput(true);
				}
				view.repaint();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
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
			try {
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
			} catch (Exception ex) {
				ex.printStackTrace();
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

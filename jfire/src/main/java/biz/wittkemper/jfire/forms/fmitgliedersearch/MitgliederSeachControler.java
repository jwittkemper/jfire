package biz.wittkemper.jfire.forms.fmitgliedersearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Mitglied;
/**
 * 
 * @author joerg
 *
 */
public class MitgliederSeachControler {

	FmitgliederSearrch view;
	List<Mitglied> mitglieder = new ArrayList<Mitglied>();
	boolean returnValue;
	MitgliedSearchModel pm = new MitgliedSearchModel();

	public MitgliederSeachControler() {
		this.view = new FmitgliederSearrch(pm);
	}

	public boolean viewSearchForm(String searchText) throws Exception {

		initClass();
		this.view.setSearchText(searchText);
		searchData(searchText, true);
		this.view.setModal(true);
		this.view.setVisible(true);

		return returnValue;

	}

	private void initClass() {
		this.view.setCancelListener(new CancelListener());
		this.view.setOKListener(new OKListener());
		this.view.setSearchListener(new SearchListener());
		this.view.setMouseListener(new MousClick());
	}

	public Mitglied getMitglied() {
		return view.getSelectedMitglied();
	}

	private void searchData(String searchText, boolean nuraktive) throws Exception{
		pm.setData(DAOFactory.getInstance().getMitgliedDAO()
				.searchByName(searchText.split(" "), nuraktive));
	}

	class SearchListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					searchData(view.getSearchText(), !view.getGeloschteAnzeigen());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class OKListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (view.getSelectedMitglied() != null) {
				view.setVisible(false);
				returnValue = true;
			}
		}

	}

	class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			returnValue = false;
			view.setVisible(false);
		}

	}

	class MousClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				if (view.getSelectedMitglied() != null) {
					view.setVisible(false);
					returnValue = true;
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}

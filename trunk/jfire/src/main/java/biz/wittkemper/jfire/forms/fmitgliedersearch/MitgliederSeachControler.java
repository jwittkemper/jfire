package biz.wittkemper.jfire.forms.fmitgliedersearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Mitglied;


public class MitgliederSeachControler {
	
	FmitgliederSearrch view;
	List<Mitglied> mitglieder = new ArrayList<Mitglied>();
	boolean returnValue;
	ExamplePresentationModel pm = new ExamplePresentationModel();
	
	public MitgliederSeachControler(){
		this.view = new FmitgliederSearrch(pm);
	}
	
	public boolean viewSearchForm(String searchText){
		
		initClass();
		this.view.setSearchText(searchText);
		searchData(searchText);
		this.view.setModal(true);
		this.view.setVisible(true);
		
		return returnValue;
		
	}
	
	private void initClass(){
		this.view.setCancelListener(new CancelListener());
		this.view.setOKListener(new OKListener());
		this.view.setSearchListener(new SearchListener());
	}
	
	private void searchData(String searchText){
		
		pm.setData(DAOFactory.getInstance().getMitgliedDAO().searchByName(searchText.split(" ")));
	}
	class SearchListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				searchData(view.getSearchText());
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class OKListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	class CancelListener  implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			returnValue = false;
			view.setVisible(false);
		}
		
	}
}

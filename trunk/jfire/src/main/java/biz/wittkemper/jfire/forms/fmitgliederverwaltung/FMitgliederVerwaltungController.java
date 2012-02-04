package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import org.apache.poi.hssf.record.formula.functions.Islogical;

import com.jgoodies.validation.ValidationResult;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.validation.MitgliedValidator;
import biz.wittkemper.jfire.forms.fmitgliedersearch.FmitgliederSearrch;
import biz.wittkemper.jfire.forms.fmitgliedersearch.MitgliederSeachControler;
import biz.wittkemper.jfire.utils.FrameUtils;
import biz.wittkemper.jfire.utils.NumberUtils;

public class FMitgliederVerwaltungController {

	FrameUtils frameUtils = new FrameUtils();
	NumberUtils numberUtils = new NumberUtils();
	FMitgliederVerwaltungView view;
	MitgliedModel model = new MitgliedModel();
	
	KeyListener searchKey = new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER){
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
	}

	private void loadData(Long id) {
		Mitglied mitglied = DAOFactory.getInstance().getMitgliedDAO().load(id);
		if (mitglied != null) {
			model.setMitglied(DAOFactory.getInstance().getMitgliedDAO()
					.load(id));
			view.setMitgliedLabel("(" + model.getId() +") " + model.getVorname() +" " + model.getName());
			
			if (DAOFactory.getInstance().getFoerderMitgliedDAO().load(model.getId())!=null){
				model.setFoerderMitglied(DAOFactory.getInstance().getFoerderMitgliedDAO().load(model.getId()));
				view.SetFoerderVerein(true);
			}else{
				view.SetFoerderVerein(false);
			}
		} else {
			model.setMitglied(new Mitglied());
			view.setMitgliedLabel("");
			view.SetFoerderVerein(false);
			view.enableImput(false);
		}
	}

	private void initListener() {
		this.view.setBeendenListener(new BeendenListener());
		this.view.setSaveListener(new SaveListener());
		this.view.setSeachListener(new SeachListener());
		this.view.setSeachKeyListener(searchKey);
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

	private void sucheMitglied(){
		String lsearch = view.getSearchText().trim();
		if (lsearch.trim()==""){
			lsearch="0";
		}
		if(numberUtils.isLongValue(lsearch)){
			loadData(Long.parseLong(lsearch));
		}else{
			loadByName(lsearch);
		}
	}
	private void loadByName(String lsearch) {
		
		MitgliederSeachControler search = new MitgliederSeachControler();
		search.viewSearchForm(lsearch);
		if (search.getMitglied()!=null){
			loadData(search.getMitglied().getId());
		}
		
	}
	class SeachListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("search")){
				sucheMitglied();
			}else if(e.getActionCommand().equals("left")){
				sucheNaechstesMitglied("left");
			}else if(e.getActionCommand().equals("right")){
				sucheNaechstesMitglied("right");
			}
		}

		private void sucheNaechstesMitglied(String richtung) {
			long id;
			if(model.getId()==null){
				id=0;
			}else{
				id = model.getId();
			}
			if (richtung.equals("left")){
				loadData(DAOFactory.getInstance().getMitgliedDAO().getPrev(id).getId());
			}else if(richtung.equals("right")){
				loadData(DAOFactory.getInstance().getMitgliedDAO().getNext(id).getId());
			}
			
			
		}

	}

	class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.trigger.triggerCommit();
			MitgliedValidator validator = new MitgliedValidator();
			ValidationResult res = validator.validate(model);

			System.out.println(res.getMessagesText());
		}

	}

	class BeendenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.setVisible(false);
			view.dispose();

		}

	}

}

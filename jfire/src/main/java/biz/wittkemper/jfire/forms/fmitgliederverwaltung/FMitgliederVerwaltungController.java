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
import biz.wittkemper.jfire.utils.FrameUtils;
import biz.wittkemper.jfire.utils.NumberUtils;

public class FMitgliederVerwaltungController {

	FrameUtils frameUtils = new FrameUtils();
	NumberUtils numberUtils = new NumberUtils();
	FMitgliederVerwaltungView view;
	MitgliedModel model;
	
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
	
	public FMitgliederVerwaltungController(Long id) {
		super();
		try {
			initClass();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadData(id);
	}

	public FMitgliederVerwaltungController() {

		try {
			initClass();
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
		} else {
			model.setMitglied(new Mitglied());
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
		if(numberUtils.isLongValue(view.getSearchText())){
			loadData(Long.parseLong(view.getSearchText()));
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
			if (richtung.equals("left")){
				loadData(DAOFactory.getInstance().getMitgliedDAO().getPrev(view.getModel().getId()).getId());
			}else if(richtung.equals("right")){
				loadData(DAOFactory.getInstance().getMitgliedDAO().getNext(view.getModel().getId()).getId());
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

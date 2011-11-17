package biz.wittkemper.jfire.forms.fmitgliederverwaltung;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.validation.ValidationResultModel;

import biz.wittkemper.jfire.data.entity.Mitglied;

public class FMitgliederVerwaltungView extends JInternalFrame {
	/**
	 * 
	 */
	MitgliedModel model = new MitgliedModel();
	
	private static final long serialVersionUID = -8498808806506779920L;
	private JTextField IName;
	private JTextField IVorname;
	private JButton btnbeenden;
	private JButton btnSave;
	public PresentationModel<MitgliedModel>  pmodel ;
	ValueModel name;
	ValueModel vorname;
	public Trigger trigger;
	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public FMitgliederVerwaltungView() throws PropertyVetoException  {
		
		setMaximizable(true);
		setIcon(true);
		setTitle("Mitgliederverwaltung");
		setResizable(true);
		setBounds(100, 100, 893, 555);
		putClientProperty("JInternalFrame.isPalette", true);
		JLabel lblName = new JLabel("Name:");
		this.trigger = new Trigger();
		
		pmodel = new PresentationModel<MitgliedModel>(this.model, this.trigger);
		
		
		name = pmodel.getBufferedModel("name");
		vorname = pmodel.getBufferedModel("vorname");
		
		IName = new JTextField();
		IName.setColumns(10);
		Bindings.bind(IName, name);

		JLabel lblVorname = new JLabel("Vorname:");
		
		IVorname = new JTextField();
		IVorname.setColumns(10);
		Bindings.bind(IVorname, vorname);
		
		btnbeenden = new JButton("beenden");
		
		btnSave = new JButton("speichern");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(652, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(18)
					.addComponent(btnbeenden)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVorname)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(IName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(IVorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(677, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(IName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVorname)
						.addComponent(IVorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnbeenden)
						.addComponent(btnSave))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
	
	protected void setSaveListener(ActionListener al){
		btnSave.addActionListener(al);
	}
	protected void setBeendenListener(ActionListener al){
		btnbeenden.addActionListener(al);
	}

	public MitgliedModel getModel() {
		return model;
	}

	public void setModel(MitgliedModel model) {
		this.model = model;
	}
}

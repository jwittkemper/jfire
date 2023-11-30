package biz.wittkemper.jfire.forms.stammdaten;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FEdit extends JDialog {
	
	public FEdit() {
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 272, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}

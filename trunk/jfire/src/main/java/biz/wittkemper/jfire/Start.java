package biz.wittkemper.jfire;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import biz.wittkemper.jfire.forms.fmain.FMainController;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}

					FMainController controller = new FMainController();
					controller.showView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}

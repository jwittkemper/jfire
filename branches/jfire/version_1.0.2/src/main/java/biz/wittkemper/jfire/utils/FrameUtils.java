package biz.wittkemper.jfire.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class FrameUtils {

	public void CenterDialog(JDialog dialog) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - dialog.getHeight()) / 2;
		int left = (screenSize.width - dialog.getWidth()) / 2;

		dialog.setLocation(left, top);
	}

	public void CenterFramne(JFrame frame){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - frame.getHeight()) / 2;
		int left = (screenSize.width - frame.getWidth()) / 2;

		frame.setLocation(left, top);
		frame.repaint();
		frame.repaint();
	}
	
	public void MaximiseFrame(JFrame frame){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize);
	}
	public Image getApplicationIcon() {
		URL url = this.getClass().getResource("Feuerwehr_Symbol.jpg");
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		return image;
	}
	
	public Dimension getSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}

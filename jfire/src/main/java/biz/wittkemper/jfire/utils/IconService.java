package biz.wittkemper.jfire.utils;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class IconService {
	public enum ICONSERVICE {
		search, left, right, main, edituser, newuser, delete, eintrittFoerderverein;
	}

	public ImageIcon getButtonIcon(ICONSERVICE picture) {

		ImageIcon img = getImageService(picture);

		return img;

	}

	public Image getImage(ICONSERVICE picture) {
		
		Image img = getImageService(picture).getImage();
		return img;
	}

	public ImageIcon getImageService(ICONSERVICE picture) {

		String pic = "";

		switch (picture) {
		case search:
			pic = "search.png";
			break;
		case left:
			pic = "left.png";
			break;
		case right:
			pic = "right.png";
			break;
		case main:
			pic = "main.png";
			break;
		case edituser:
			pic = "editUser.png";
			break;
		case newuser:
			pic = "addUser.png";
			break;
		case delete:
			pic = "del.png";
			break;
		case eintrittFoerderverein:
			pic="foerderMitglied.png";
			break;
		}
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		InputStream  inimg = loader.getResourceAsStream("/picture/" + pic);
//		img = ImageIO.read(inimg);
		URL img = this.getClass().getResource("/picture/" + pic);
		if (img != null) {
			ImageIcon im= null;
	
				URL imageURL = getClass().getClassLoader().getResource("picture/" + pic);
		        im = new ImageIcon(imageURL);

			return im;
		}else {
			return null;
		}
		

	}
}

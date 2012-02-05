package biz.wittkemper.jfire.utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class IconService {
	public enum ICONSERVICE {
		search, left, right, main, edituser, newuser;
	}

	public ImageIcon getButtonIcon(ICONSERVICE picture) {

		ImageIcon img = getImageService(picture);

		return img;

	}

	public Image getImage(ICONSERVICE picture) {
		return getImageService(picture).getImage();
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
		}
		URL img = ClassLoader.getSystemResource("picture/" + pic);
		ImageIcon im = new ImageIcon(img);

		return im;

	}
}

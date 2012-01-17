package biz.wittkemper.jfire.utils;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class IconService {
	public enum ICONSERVICE {
		search, left, right, main;
	}

	public ImageIcon getButtonIcon(ICONSERVICE picture) {

		ImageIcon img = new ImageIcon(getImageService(picture));

		return img;

	}

	public Image getImageService(ICONSERVICE picture) {

		String pic = "";

		switch (picture) {
		case search: pic="search.png";
			break;
		case left:pic="left.png";
			break;
		case right:pic="right.png";
			break;
		case main:pic="main.png";
			break;
		}
		Image im = Toolkit.getDefaultToolkit().getImage("picture/" + pic);

		return im;

	}
}

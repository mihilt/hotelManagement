package project.gui.additional;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		Dimension d = getSize();
		setSize(new Dimension(d.width, d.height));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(img, 0, 0, d.width, d.height, null);
	}
	
}
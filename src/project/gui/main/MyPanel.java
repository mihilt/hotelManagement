package project.gui.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.gui.additional.RoomManageGui;

public class MyPanel extends JPanel {
	
	public MyPanel(JFrame f, String title, String id) {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomManageGui room = new RoomManageGui(title, id);
			}
		});
		
	}
	
}

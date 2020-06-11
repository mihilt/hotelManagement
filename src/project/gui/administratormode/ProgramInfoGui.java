package project.gui.administratormode;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProgramInfoGui extends JFrame {

	public ProgramInfoGui() {

		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		setBounds(0, 0, 300, 265);
		setLocationRelativeTo(null);
		setTitle("프로그램 정보");
		setLayout(null);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 300, 300);

		JLabel programNameLabel = new JLabel("KH HOTEL");
		programNameLabel.setFont(new Font("HY견고딕", Font.BOLD, 25));
		programNameLabel.setBounds(0, 20, 300, 25);
		programNameLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(programNameLabel);

		JTextArea infoTextA = new JTextArea();
		infoTextA.setBounds(60, 60, 240, 100);
		infoTextA.setBackground(new Color(238, 238, 238));
		infoTextA.setEditable(false);
		infoTextA.setText("숙박업소를 관리하시는 업주분들이 \n" + "   업무에 필요한 모든 기록 장부를 \n" + "           수기로 작성하지 않고,\n "
				+ "            컴퓨터를 사용하여\n" + " 편하게 이용하실 수 있도록 만든 \n" + "              프로그램입니다. ");
		mainPanel.add(infoTextA);

		JLabel editDateLabel = new JLabel("최종 수정일 : 2020-06-07");
		editDateLabel.setFont(new Font("HY견고딕", Font.PLAIN, 17));
		editDateLabel.setBounds(0, 170, 300, 17);
		editDateLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(editDateLabel);

		JLabel makersLabel = new JLabel("만든 사람들 : 김원식, 이장송, 남보람");
		makersLabel.setFont(new Font("HY견고딕", Font.PLAIN, 17));
		makersLabel.setBounds(0, 190, 300, 17);
		makersLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(makersLabel);

		add(mainPanel);
		setVisible(true);
	}

}

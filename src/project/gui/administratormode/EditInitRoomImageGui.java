package project.gui.administratormode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.gui.additional.JfileChooserUtil;
import project.io.ObjectIO;
import project.vo.Program;

public class EditInitRoomImageGui extends JFrame {

	private Program program;

	public EditInitRoomImageGui() {

		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		setBounds(0, 0, 500, 550);
		setLocationRelativeTo(null);
		setTitle("기본 방 이미지 변경");
		setLayout(null);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 500, 550);

		JLabel currentImg = new JLabel("현재 기본 방 이미지");
		currentImg.setFont(new Font("HY견고딕", Font.BOLD, 15));
		currentImg.setBounds(180, 20, 150, 15);
		mainPanel.add(currentImg);

		JLabel roomImageLabel = new JLabel();
		roomImageLabel.setBounds(50, 50, 400, 300);
		mainPanel.add(roomImageLabel);

		JLabel caution = new JLabel("! 이미 초기화 세팅을 완료한 아이디의 기본 방 이미지는 바뀌지 않습니다");
		caution.setFont(new Font("HY견고딕", Font.BOLD, 13));
		caution.setBounds(0, 355, 500, 15);
		caution.setForeground(Color.red);
		caution.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(caution);

		JButton searchBtn = new JButton("탐색");
		searchBtn.setBounds(50, 390, 60, 30);
		mainPanel.add(searchBtn);

		TextField changeImgTextF = new TextField();
		changeImgTextF.setBounds(130, 390, 320, 30);
		changeImgTextF.setEditable(false);
		mainPanel.add(changeImgTextF);

		JButton saveBtn = new JButton("저장");
		saveBtn.setBounds(200, 450, 80, 50);
		mainPanel.add(saveBtn);

		program = (Program) new ObjectIO().loadObject(program, "files/programFile/programSettingData.dat");
		roomImageLabel.setIcon(changeIconSize(program.getRoomImgPath(), 400, 300));
		changeImgTextF.setText(program.getRoomImgPath());

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String imgPath = JfileChooserUtil.jFileChooserUtil();
				changeImgTextF.setText(imgPath);
				if (changeImgTextF.getText().length() > 1) {
					roomImageLabel.setIcon(changeIconSize(imgPath, 400, 300));
				}
			}
		});

		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				program.setRoomImgPath(changeImgTextF.getText());
				ObjectIO io = new ObjectIO();
				io.saveObject(program, "files/programFile/programSettingData.dat");
				JOptionPane.showMessageDialog(null, "저장이 완료되었습니다.");
				dispose();
			}
		});

		add(mainPanel);
		setVisible(true);
	}

	public Icon changeIconSize(String path, int size_x, int size_y) {

		Image originImg = new ImageIcon(path).getImage();
		Image changedImg = originImg.getScaledInstance(size_x, size_y, Image.SCALE_SMOOTH);
		return new ImageIcon(changedImg);

	}

}

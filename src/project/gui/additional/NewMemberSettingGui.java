package project.gui.additional;

import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.controller.BridgeController;
import project.controller.InitRoom;
import project.gui.main.FrontGui;
import project.io.ObjectIO;
import project.vo.Room;

public class NewMemberSettingGui extends JFrame {
	
	public NewMemberSettingGui(String id) {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		JPanel panel = new JPanel();
		setBounds(500, 280, 480, 450);
		setLocationRelativeTo(null);
		setTitle("방 초기화 세팅");
		JLabel label1 = new JLabel("처음 오셨군요? 환영합니다!");
		label1.setFont(new Font("HY견고딕", Font.BOLD, 25));
		JLabel label2 = new JLabel("일괄적으로 세팅할 초기값이 필요합니다.");

		JLabel roomNum = new JLabel("세팅할 방의 갯수 :");
		roomNum.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		roomNum.setBounds(60, 150, 150, 30);

		JLabel price = new JLabel("숙박 가격 :");
		price.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		price.setBounds(105, 220, 200, 30);

		TextField f1 = new TextField();
		f1.setBounds(200, 150, 200, 30);
		TextField f2 = new TextField();
		f2.setBounds(200, 220, 200, 30);

		JButton btn = new JButton("등록");
		btn.setSize(80, 40);
		btn.setLocation(195, 300);

		panel.add(label1);
		panel.add(label2);
		add(f1);
		add(roomNum);
		add(f2);
		add(price);
		add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (f1.getText().length() < 1 || f2.getText().length() < 1)
						JOptionPane.showMessageDialog(null, "입력을 완료해주세요.");
					else if (Integer.parseInt(f1.getText()) > 60) {
						JOptionPane.showMessageDialog(null, "방 60개 초과는 구현 못했습니다. 60개 이하로 설정해주세요.");
					} else {
						String idPath = "files/membersDataFiles/" + id;
						String idLogPath = "files/membersDataFiles/" + id + "/logs";
						File idFolder = new File(idPath);
						File idLogFolder = new File(idLogPath);

						try {
							idFolder.mkdirs();
							idLogFolder.mkdirs();
						} catch (Exception exception) {
							exception.getStackTrace();
						}

						List<Room> roomList = new ArrayList<Room>();
						roomList = new InitRoom().roomSet(Integer.parseInt(f1.getText()),
								Integer.parseInt(f2.getText()));

						ObjectIO io = new ObjectIO();
						String path = "files/membersDataFiles/" + id + "/roomFile.dat";

						io.saveObject(roomList, path);
						JOptionPane.showMessageDialog(null, "세팅을 완료했습니다.");
						BridgeController.fg = new FrontGui(id);

						dispose();
					}
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "한글만 입력해주세요");
				}

			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		add(panel);
		setVisible(true);

	}

}
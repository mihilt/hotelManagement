package project.gui.additional;

import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import project.controller.BridgeController;
import project.io.ObjectIO;
import project.vo.Room;

public class ChangeRoomSettingGui extends JFrame {

	private List<Room> roomList = new ArrayList<Room>();
	private ImagePanel imagePanel;

	public ChangeRoomSettingGui(String id) {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		ObjectIO io = new ObjectIO();
		String path = "files/membersDataFiles/" + id + "/roomFile.dat";
		roomList = (List<Room>) io.loadObject(roomList, path);
		setLayout(null);

		setBounds(0, 0, 800, 450);
		setLocationRelativeTo(null);
		setTitle("방 정보 변경");

		JLabel roomNum = new JLabel("방 : ");
		roomNum.setBounds(110, 30, 30, 30);

		JLabel roomNickname = new JLabel("방 별명 추가 : ");
		roomNickname.setBounds(55, 180, 100, 30);

		JLabel price = new JLabel("가격 변경 :");
		price.setBounds(68, 240, 80, 30);

		JLabel roomImgLabel = new JLabel("이미지 변경 :");
		roomImgLabel.setBounds(50, 115, 100, 30);

		JLabel roomImgLabel2 = new JLabel("(이미지 경로)");
		roomImgLabel2.setBounds(50, 130, 100, 30);

		String roomNameArr[] = new String[roomList.size()];
		for (int i = 0; i < roomList.size(); i++) {
			roomNameArr[i] = roomList.get(i).getName();
		}

		JComboBox comboBox = new JComboBox(roomNameArr);
		comboBox.setBounds(155, 30, 200, 30);

		TextField changeImgTextF = new TextField();
		changeImgTextF.setBounds(155, 120, 200, 30);
		changeImgTextF.setEditable(false);

		TextField addNicknameTextF = new TextField();
		addNicknameTextF.setBounds(155, 180, 200, 30);

		TextField changePriceTextF = new TextField();
		changePriceTextF.setBounds(155, 240, 200, 30);

		JButton registrationBtn = new JButton("등록");
		registrationBtn.setSize(80, 40);
		registrationBtn.setLocation(195, 320);

		JButton searchBtn = new JButton("탐색");
		searchBtn.setBounds(370, 120, 60, 30);

		JLabel realRoomImageLabel = new JLabel();
		realRoomImageLabel.setBounds(470, 10, 300, 400);

		realRoomImageLabel.setIcon(changeIconSize(roomList.get(0).getRoomImg(), 300, 300));
		changeImgTextF.setText(roomList.get(0).getRoomImg());
		addNicknameTextF.setText(roomList.get(0).getNickname());
		changePriceTextF.setText(String.valueOf(roomList.get(0).getPrice()));

		add(realRoomImageLabel);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String roomName = comboBox.getSelectedItem().toString();
				int roomIndex = Integer.parseInt(roomName.substring(1)) - 1;

				String imgPath = roomList.get(roomIndex).getRoomImg();
				realRoomImageLabel.setIcon(changeIconSize(imgPath, 300, 300));
				changeImgTextF.setText(roomList.get(roomIndex).getRoomImg());
				addNicknameTextF.setText(roomList.get(roomIndex).getNickname());
				changePriceTextF.setText(String.valueOf(roomList.get(roomIndex).getPrice()));
			}
		});

		add(comboBox);
		add(roomNickname);
		add(roomImgLabel);
		add(roomImgLabel2);
		add(changeImgTextF);
		add(roomNum);
		add(addNicknameTextF);
		add(price);
		add(changePriceTextF);
		add(registrationBtn);
		add(searchBtn);

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String imgPath = JfileChooserUtil.jFileChooserUtil();
				changeImgTextF.setText(imgPath);
				if (changeImgTextF.getText().length() > 1) {
					realRoomImageLabel.setIcon(changeIconSize(imgPath, 300, 300));
				}
			}
		});

		registrationBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (comboBox.getSelectedItem().toString().length() < 1) {
						JOptionPane.showMessageDialog(null, "변경하실 방을 입력해주세요");

					} else {

						String roomName = comboBox.getSelectedItem().toString();
						int roomIndex = Integer.parseInt(roomName.substring(1)) - 1;

						roomList.get(roomIndex).setNickname(addNicknameTextF.getText());

						if (changePriceTextF.getText().length() > 0) {
							roomList.get(roomIndex).setPrice(Integer.parseInt(changePriceTextF.getText()));
						}

						if (changeImgTextF.getText().length() > 1) {
							roomList.get(roomIndex).setRoomImg(changeImgTextF.getText());
						}

						io.saveObject(roomList, path);
						JOptionPane.showMessageDialog(null, "변경이 완료 되었습니다.");

						BridgeController.fg.refreshRoomsInfo();

						dispose();

					}

				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "허용되지 않은 입력입니다.");
				}

			}

		});

		setResizable(false);
		setVisible(true);

	}

	public Icon changeIconSize(String path, int size_x, int size_y) {

		Image originImg = new ImageIcon(path).getImage();
		Image changedImg = originImg.getScaledInstance(size_x, size_y, Image.SCALE_SMOOTH);
		return new ImageIcon(changedImg);

	}
}
package project.gui.login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.controller.BridgeController;
import project.gui.administratormode.AdminGui;
import project.io.ObjectIO;
import project.vo.Member;

public class LoginGui extends JFrame {

	public LoginGui() {

		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		setTitle("KH HOTEL");
		setBounds(600, 380, 450, 400);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(null);

		ImageIcon kh = new ImageIcon("files/otherImages/kh_gold.png");
		JLabel labelKh = new JLabel(kh);
		labelKh.setBounds(150, 20, 50, 50);

		ImageIcon hotel = new ImageIcon("files/otherImages/hotel_gold.jpg");
		JLabel labelHotel = new JLabel(hotel);
		labelHotel.setBounds(200, 32, 90, 40);

		ImageIcon imageId = new ImageIcon("files/otherImages/id.png");
		JLabel labelId = new JLabel(imageId);
		labelId.setBounds(97, 120, 40, 40);

		JTextField idField = new JTextField();
		idField.setBounds(170, 120, 200, 40);

		ImageIcon imagePwd = new ImageIcon("files/otherImages/password.png");
		JLabel labelPwd = new JLabel(imagePwd);
		labelPwd.setBounds(95, 180, 50, 50);

		JPasswordField pwdField = new JPasswordField();
		pwdField.setBounds(170, 180, 200, 40);

		ImageIcon loginImage = new ImageIcon("files/otherImages/login.png");

		JButton loginBtn = new JButton(loginImage);
		loginBtn.setBounds(280, 270, 100, 35);

		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setFocusPainted(false);

		ImageIcon joinImage = new ImageIcon("files/otherImages/signup.png");

		JButton joinBtn = new JButton(joinImage);
		joinBtn.setBounds(170, 270, 100, 35);

		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);

		panel.add(loginBtn);
		panel.add(joinBtn);
		panel.add(labelId);
		panel.add(labelPwd);
		panel.add(idField);
		panel.add(pwdField);
		panel.add(labelKh);
		panel.add(labelHotel);

		add(panel);

		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Map<String, Member> memberMap = new HashMap<>();

				ObjectIO io = new ObjectIO();
				memberMap = (Map<String, Member>) io.loadObject(memberMap, "files/memberFile/member.dat");

				try {

					if (idField.getText().equals("kh") && pwdField.getText().equals("kh")) {
						new AdminGui();
					} else if (memberMap.containsKey(idField.getText())
							&& memberMap.get(idField.getText()).getPassword().equals(pwdField.getText())) {
						new BridgeController(idField.getText());
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");
					}

				} catch (NullPointerException exception) {
					JOptionPane.showMessageDialog(null, "입력을 해주세요");
				}

			}
		});

		joinBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Join();
			}
		});

		add(panel);
		setVisible(true);
	}

}
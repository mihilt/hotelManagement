package project.gui.login;

import java.awt.TextField;
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

import project.io.ObjectIO;
import project.vo.Member;

public class Join extends JFrame {

	public Join() {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		JPanel panel = new JPanel();
		setBounds(500, 280, 480, 450);
		setLocationRelativeTo(null);
		JLabel idLabel = new JLabel("아이디");
		JLabel passwordLabel = new JLabel("비밀번호");
		JLabel nameLabel = new JLabel("이름");
		JLabel addressLabel = new JLabel("주소");
		JLabel additionsLabel = new JLabel("추가사항");

		idLabel.setBounds(40, 10, 40, 30);
		passwordLabel.setBounds(40, 50, 60, 40);
		nameLabel.setBounds(40, 90, 40, 40);
		addressLabel.setBounds(40, 130, 40, 40);
		additionsLabel.setBounds(40, 170, 60, 40);

		TextField idField = new TextField();
		TextField passwordField = new TextField();
		TextField nameField = new TextField();
		TextField addressField = new TextField();
		TextField additionsField = new TextField();

		idField.setBounds(120, 10, 200, 30);
		passwordField.setBounds(120, 50, 200, 30);
		nameField.setBounds(120, 90, 200, 30);
		addressField.setBounds(120, 135, 280, 30);
		additionsField.setBounds(120, 180, 280, 100);
		passwordField.setEchoChar('*');

		JButton btn1 = new JButton("가입");

		btn1.setSize(80, 40);
		btn1.setLocation(200, 300);

		add(btn1);
		add(nameLabel);
		add(idLabel);
		add(passwordLabel);
		add(addressLabel);
		add(additionsLabel);
		add(idField);
		add(passwordField);
		add(nameField);
		add(addressField);
		add(additionsField);

		add(panel);

		setTitle("회원가입");

		setVisible(true);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, Member> memberMap = new HashMap<>();
				ObjectIO io = new ObjectIO();
				memberMap = (Map<String, Member>) io.loadObject(memberMap, "files/memberFile/member.dat");

				if (idField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
				} else if (passwordField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				} else if (memberMap.containsKey(idField.getText())) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다.");
				} else {
					memberMap.put(idField.getText(), new Member(idField.getText(), passwordField.getText(),
							nameField.getText(), addressField.getText(), additionsField.getText()));
					io.saveObject(memberMap, "files/memberFile/member.dat");
					JOptionPane.showMessageDialog(null, "회원가입 성공");
					dispose();
				}

			}
		});
	}

}
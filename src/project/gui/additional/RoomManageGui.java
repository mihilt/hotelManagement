package project.gui.additional;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import project.controller.BridgeController;
import project.io.LogDateSort;
import project.io.ObjectIO;
import project.vo.Log;
import project.vo.Room;

public class RoomManageGui extends JFrame implements Serializable {
	
	private String cleaning;
	private int price;
	private String inTime;
	private String outTime;
	private String roomImg;
	private Date checkInDate;
	private Date checkOutDate;
	private Date situationTime;
	private List<Room> roomList = new ArrayList<Room>();
	private List<Log> logList = new ArrayList<Log>();

	public RoomManageGui(String title, String id) {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		setResizable(false);
		this.setTitle(title + " 세팅");
		String no = title.replaceAll("방", "");
		int roomNo = Integer.parseInt(no);
		SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일\n k시 m분 s초");
		ObjectIO io = new ObjectIO();
		roomList = (List<Room>) io.loadObject(roomList, "files/membersDataFiles/" + id + "/roomFile.dat");
		logList = (List<Log>) io.loadObject(logList,
				"files/membersDataFiles/" + id + "/logs/" + logDateFormat.format(new Date()) + "log.dat");
		checkInDate = roomList.get(roomNo - 1).getCheckInDate();
		checkOutDate = roomList.get(roomNo - 1).getCheckOutDate();
		cleaning = roomList.get(roomNo - 1).getState();
		roomImg = roomList.get(roomNo - 1).getRoomImg();
		price = roomList.get(roomNo - 1).getPrice();
		setBounds(200, 200, 600, 800);
		setLocationRelativeTo(null);
		setLayout(null);
		Calendar c;

		ImagePanel ImagePanel = new ImagePanel(new ImageIcon(roomImg).getImage());
		ImagePanel.setBounds(105, 50, 400, 280);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(200, 200, 200));
		panel.setBounds(0, 0, 600, 780);
		JLabel label1 = new JLabel();
		label1.setText(title);
		label1.setBounds(20, 20, 80, 20);

		JLabel label2 = new JLabel();
		label2.setText("입실 시간 :");
		label2.setBounds(150, 500, 80, 20);
		JLabel label3 = new JLabel();
		label3.setText("퇴실 시간 :");
		label3.setBounds(150, 560, 80, 20);

		JLabel label4 = new JLabel();
		label4.setText(roomList.get(roomNo - 1).getNickname());
		label4.setBounds(120, 355, 360, 20);
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setFont(new Font("돋움", Font.BOLD, 20));

		JLabel label5 = new JLabel();
		label5.setText("가격 : ");
		label5.setBounds(150, 430, 80, 20);

		JLabel label6 = new JLabel();
		label6.setText(cleaning);
		label6.setBounds(20, 430, 80, 20);
		JLabel label7 = new JLabel();
		label7.setText(cleaning);
		label7.setBounds(20, 430, 80, 20);

		JTextArea text3 = new JTextArea(Integer.toString(price));
		text3.setBounds(230, 430, 100, 25);
		JTextArea text1 = new JTextArea();
		text1.setBounds(230, 500, 100, 35);
		text1.setEnabled(false);
		JTextArea text2 = new JTextArea();
		text2.setBounds(230, 560, 100, 35);
		text2.setEnabled(false);

		if (roomList.get(roomNo - 1).getCheckInDate() != null)
			inTime = sdf.format(checkInDate);
		text1.setText(inTime);

		if (roomList.get(roomNo - 1).getCheckOutDate() != null)
			outTime = sdf.format(checkOutDate);
		text2.setText(outTime);

		JButton btnpay = new JButton("결제");
		btnpay.setBounds(370, 450, 100, 100);
		btnpay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int payment = 0;
				try {
					payment = Integer.parseInt(text3.getText());
					situationTime = new Date();
					logList.add(new Log("방" + String.valueOf(roomNo), "결제", situationTime, payment));
					JOptionPane.showMessageDialog(null, "결제되었습니다.");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "허용되지 않은 입력 입니다.");
				}
			}
		});
		JButton btn1 = new JButton("입실");
		btn1.setBounds(100, 630, 80, 40);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cleaning.equals("공실")) {
					cleaning = "입실";
					JOptionPane.showMessageDialog(null, "입실되었습니다.");
					Calendar c = Calendar.getInstance();
					inTime = c.get(Calendar.YEAR) + "년 " + (c.get(Calendar.MONTH) + 1) + "월 " + c.get(Calendar.DATE)
							+ "일\n " + c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 "
							+ c.get(Calendar.SECOND) + "초";
					text1.setText(inTime);
					checkInDate = new Date();
					situationTime = new Date();
					logList.add(new Log("방" + String.valueOf(roomNo), cleaning, situationTime, 0));
					cleaning = "재실";
				} else
					JOptionPane.showMessageDialog(null, "방이 재실 상태이거나, 청소가 필요한 상태 입니다.");
			}
		});
		JButton btn2 = new JButton("퇴실");
		btn2.setBounds(250, 630, 80, 40);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cleaning.equals("재실")) {
					cleaning = "퇴실";
					JOptionPane.showMessageDialog(null, "퇴실되었습니다.");
					Calendar c = Calendar.getInstance();
					outTime = c.get(Calendar.YEAR) + "년 " + (c.get(Calendar.MONTH) + 1) + "월 " + c.get(Calendar.DATE)
							+ "일\n " + c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 "
							+ c.get(Calendar.SECOND) + "초";
					text2.setText(outTime);
					checkOutDate = new Date();
					situationTime = new Date();
					logList.add(new Log("방" + String.valueOf(roomNo), cleaning, situationTime, 0));
					cleaning = "청소";
				} else
					JOptionPane.showMessageDialog(null, "방이 공실이거나, 청소가 필요한 상태입니다.");
			}
		});
		JButton btn3 = new JButton("청소");
		btn3.setBounds(400, 630, 80, 40);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cleaning.equals("청소")) {
					cleaning = "청소";
					JOptionPane.showMessageDialog(null, "청소되었습니다.");
					situationTime = new Date();
					checkInDate = null;
					checkOutDate = null;
					logList.add(new Log("방" + String.valueOf(roomNo), cleaning, situationTime, 0));
					cleaning = "공실";
				} else
					JOptionPane.showMessageDialog(null, "방이 청소가 완료된 공실 상태이거나, 재실 상태입니다.");
			}
		});
		JButton btnExit = new JButton("저장/닫기");
		btnExit.setBounds(215, 700, 150, 60);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nic = roomList.get(roomNo - 1).getNickname();
				roomList.remove(roomNo - 1);
				roomList.add(roomNo - 1, new Room("방" + String.valueOf(roomNo), nic, cleaning, price, roomImg,
						checkInDate, checkOutDate));
				Comparator<Log> dateSort = new LogDateSort();
				Collections.sort(logList, dateSort);
				io.saveObject(roomList, "files/membersDataFiles/" + id + "/roomFile.dat");
				io.saveObject(logList,
						"files/membersDataFiles/" + id + "/logs/" + logDateFormat.format(new Date()) + "log.dat");
				BridgeController.fg.refreshRoomsInfo();
				BridgeController.fg.refreshLogsInfo();
				dispose();
			}
		});
		panel.add(btnpay);
		panel.add(label5);
		panel.add(text3);
		panel.add(label5);
		panel.add(text2);
		panel.add(label4);
		panel.add(label3);
		panel.add(label2);
		panel.add(text1);
		panel.add(btn3);
		panel.add(btn2);
		panel.add(label1);
		panel.add(btn1);
		panel.add(btnExit);
		add(ImagePanel);
		add(panel);
		setVisible(true);
	}

	public String getCleaning() {
		return cleaning;
	}

	public void setCleaning(String cleaning) {
		this.cleaning = cleaning;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
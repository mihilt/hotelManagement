package project.gui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import project.gui.additional.ChangeRoomSettingGui;
import project.gui.eventlog.EventLogGui;
import project.gui.eventlog.RoomLogGui;
import project.gui.login.LoginGui;
import project.io.ObjectIO;
import project.vo.Log;
import project.vo.Room;

public class FrontGui extends JFrame {
	private String id_field;
	private String logFilePath;

	private JPanel mainPanel = new JPanel();
	private JPanel sideSubPanel = new JPanel();
	private JPanel statePanel = new JPanel();

	private JLabel emptyRoomLabel = new JLabel();
	private JLabel needCleanRoomLabel = new JLabel();
	private JLabel filledRoomLabel = new JLabel();

	private List<Room> roomList;
	private List<Log> logList;

	private ObjectIO io = new ObjectIO();

	public FrontGui(String id) {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		id_field = id;

		roomList = new ArrayList<Room>();

		refreshRoomsInfo();

		logList = new ArrayList<Log>();

		SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyyMMdd");
		logFilePath = "files/membersDataFiles/" + id_field + "/logs/" + logDateFormat.format(new Date()) + "log.dat";
		File logFile = new File(logFilePath);

		if (logFile.exists()) {
		} else {
			new ObjectIO().saveObject(logList, logFilePath);
		}

		logList = (List<Log>) io.loadObject(logList, logFilePath);
		setBounds(0, 0, 1570, 1000);
		setLocationRelativeTo(null);
		setTitle("KH Hotel 객실 관리 프로그램");
		setLayout(null);
		getContentPane().setBackground(new Color(50, 50, 50));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		createMenu();

		mainPanel.setBounds(15, 15, 1190, 920);
		mainPanel.setBackground(new Color(100, 100, 100));
		mainPanel.setLayout(null);

		JPanel sidePanel = new JPanel();
		sidePanel.setBounds(1220, 15, 330, 920);
		sidePanel.setBackground(new Color(100, 100, 100));
		sidePanel.setLayout(null);

		JPanel timePanel = new JPanel();
		timePanel.setBounds(10, 10, 300, 100);
		timePanel.setBackground(new Color(222, 222, 222));
		sidePanel.add(timePanel);

		JTextArea timeTextArea = new JTextArea();
		timeTextArea.setBounds(0, 0, 230, 80);
		timeTextArea.setBackground(new Color(222, 222, 222));
		timeTextArea.setEditable(false); // timeTextArea 변경불가
		timeTextArea.setFont(new Font("HY견고딕", Font.BOLD, 35));
		timePanel.add(timeTextArea);

		sideSubPanel.setBounds(10, 120, 300, 790);
		sideSubPanel.setBackground(new Color(222, 222, 222));
		sideSubPanel.setLayout(null);
		sidePanel.add(sideSubPanel);

		JLabel stateLabel = new JLabel("객실 현황");
		stateLabel.setBounds(90, 0, 200, 50);
		stateLabel.setFont(new Font("HY견고딕", Font.BOLD, 25));
		sideSubPanel.add(stateLabel);

		statePanel.setLayout(null);
		statePanel.setBounds(20, 45, 263, 100);
		statePanel.setBackground(new Color(242, 242, 242));
		sideSubPanel.add(statePanel);

		emptyRoomLabel.setBounds(60, 10, 150, 20);
		emptyRoomLabel.setFont(new Font("HY견고딕", Font.BOLD, 23));
		needCleanRoomLabel.setBounds(60, 40, 150, 20);
		needCleanRoomLabel.setFont(new Font("HY견고딕", Font.BOLD, 23));
		filledRoomLabel.setBounds(60, 70, 150, 20);
		filledRoomLabel.setFont(new Font("HY견고딕", Font.BOLD, 23));

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM월 dd일 로그");

		JLabel eventLogLabel = new JLabel(dateFormat1.format(new Date()));
		eventLogLabel.setFont(new Font("HY견고딕", Font.BOLD, 25));
		eventLogLabel.setBounds(70, 130, 263, 100);
		sideSubPanel.add(eventLogLabel);

		refreshLogsInfo();

		new ThreadClock(timeTextArea).start();

		add(mainPanel);
		add(sidePanel);
		setVisible(true);
	}

	private LayoutManager GridLayout(int i, int j) {
		return null;
	}

	public void refreshLogsInfo() {
		logList = (List<Log>) io.loadObject(logList, logFilePath);

		try {
			sideSubPanel.remove(3);
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		Object[] columnNames = { "방", "시간", "상황" };
		Object[][] rowData = new Object[logList.size()][columnNames.length];

		JTable logTable = new JTable(rowData, columnNames);
		JScrollPane scr = new JScrollPane(logTable);

		for (int i = 0; i < logList.size(); i++) {
			Log l = logList.get(i);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd k:m:s");
			rowData[i][0] = l.getRoomName();
			rowData[i][1] = dateFormat2.format(l.getSituationTime());
			rowData[i][2] = l.getSituationName();

		}

		scr.setBounds(20, 203, 263, 570);
		logTable.setPreferredScrollableViewportSize(new Dimension(263, 735));
		logTable.setAutoCreateRowSorter(true);

		logTable.getColumn("시간").setPreferredWidth(200); // 시간 Column 크기 크게
		sideSubPanel.add(scr);
	}

	public void refreshRoomsInfo() {

		String roomFilePath = "files/membersDataFiles/" + id_field + "/roomFile.dat";
		roomList = (List<Room>) io.loadObject(roomList, roomFilePath);

		JPanel[] roomPanel = new JPanel[roomList.size()];
		mainPanel.removeAll();

		int x = 17;
		int y = 17;
		int roomNum = 0;

		int numOfemptyRoom = 0;
		int numOffilledRoom = 0;
		int numOfneedCleanRoom = 0;

		try {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 10; j++) {

					roomPanel[roomNum] = new MyPanel(this, roomList.get(roomNum).getName(), id_field);
					roomPanel[roomNum].setBounds(x, y, 100, 135);
					roomPanel[roomNum].setLayout(null);

					JLabel roomName = new JLabel(roomList.get(roomNum).getName());
					roomName.setBounds(5, 5, 100, 11);
					roomName.setFont(new Font("돋움", Font.BOLD, 11));
					roomPanel[roomNum].add(roomName);

					JLabel roomNickname = new JLabel(roomList.get(roomNum).getNickname());

					roomNickname.setHorizontalAlignment(JLabel.CENTER);// 중앙정렬 알아냄!

					roomNickname.setBounds(0, 24, 100, 13);
					roomNickname.setFont(new Font("돋움", Font.BOLD, 13));
					roomPanel[roomNum].add(roomNickname);

					ImageIcon needCleanPic = new ImageIcon(("files/RoomStateImages/needClean.png"));
					ImageIcon emptyPic = new ImageIcon(("files/RoomStateImages/empty.png"));
					ImageIcon filledPic = new ImageIcon(("files/RoomStateImages/filled.png"));

					JLabel roomPicture = new JLabel();
					switch (roomList.get(roomNum).getState()) {
					case "공실":
						numOfemptyRoom++;
						roomPicture = new JLabel(emptyPic);
						roomPanel[roomNum].setBackground(new Color(180, 200, 180));
						break;

					case "재실":
						numOffilledRoom++;
						roomPicture = new JLabel(filledPic);
						roomPanel[roomNum].setBackground(new Color(200, 180, 180));
						break;

					case "청소":
						numOfneedCleanRoom++;
						roomPicture = new JLabel(needCleanPic);
						roomPanel[roomNum].setBackground(new Color(170, 200, 200));
						break;

					}
					roomPicture.setBounds(10, 40, 80, 50);
					roomPicture.setBackground(new Color(255, 255, 255));

					roomPanel[roomNum].add(roomPicture);

					JLabel roomPrice = new JLabel(String.valueOf(roomList.get(roomNum).getPrice()) + " 원");
					roomPrice.setHorizontalAlignment(JLabel.CENTER);
					roomPrice.setBounds(0, 97, 100, 20);
					roomPanel[roomNum].add(roomPrice);

					JLabel roomState = new JLabel(roomList.get(roomNum).getState());
					roomState.setHorizontalAlignment(JLabel.CENTER);
					roomState.setBounds(0, 112, 100, 20);
					roomPanel[roomNum].add(roomState);

					x += 117;

					mainPanel.add(roomPanel[roomNum]);

					roomNum++;
				}
				x = 17;
				y += 150;
			}

		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (IndexOutOfBoundsException e) {

		}

		statePanel.removeAll();
		emptyRoomLabel.setText("공실   :   " + numOfemptyRoom);
		needCleanRoomLabel.setText("재실   :   " + numOffilledRoom);
		filledRoomLabel.setText("청소   :   " + numOfneedCleanRoom);

		statePanel.add(emptyRoomLabel);
		statePanel.add(needCleanRoomLabel);
		statePanel.add(filledRoomLabel);

		revalidate();
		repaint();

	}

	class ThreadClock extends Thread {
		JTextArea target;

		public ThreadClock(JTextArea t) {
			target = t;
		}

		public void run() {
			Calendar c = null;
			String time = null;
			while (true) {
				c = Calendar.getInstance();
				time = c.get(Calendar.YEAR) + "년 " + (c.get(Calendar.MONTH) + 1) + "월 " + c.get(Calendar.DATE) + "일\n "
						+ c.get(Calendar.HOUR_OF_DAY) + "시 " + c.get(Calendar.MINUTE) + "분 " + c.get(Calendar.SECOND)
						+ "초";
				target.setText(time);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenuItem[] menu1_itemArr = new JMenuItem[4];
		JMenuItem[] menu2_itemArr = new JMenuItem[1];
		String[] menu1_item = { "날짜 로그 확인", "방 로그 확인", "로그아웃", "종료" };
		String[] menu2_item = { "방 정보 변경" };

		JMenu menu1 = new JMenu("메뉴");
		JMenu menu2 = new JMenu("편집");

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();

				switch (cmd) {

				case "날짜 로그 확인":
					new EventLogGui(id_field);
					break;

				case "방 로그 확인":
					new RoomLogGui(id_field);
					break;

				case "로그아웃":
					new LoginGui();
					dispose();
					break;

				case "종료":
					System.exit(0);
					return;

				case "방 정보 변경":
					new ChangeRoomSettingGui(id_field);
					break;
				}

			}
		};

		for (int i = 0; i < menu1_itemArr.length; i++) {
			menu1_itemArr[i] = new JMenuItem(menu1_item[i]);
			menu1_itemArr[i].addActionListener(listener);
			if (i == 2)
				menu1.addSeparator();
			menu1.add(menu1_itemArr[i]);
		}

		for (int i = 0; i < menu2_itemArr.length; i++) {
			menu2_itemArr[i] = new JMenuItem(menu2_item[i]);
			menu2_itemArr[i].addActionListener(listener);
			menu2.add(menu2_itemArr[i]);
		}

		menuBar.add(menu1);
		menuBar.add(menu2);

		setJMenuBar(menuBar);
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

}

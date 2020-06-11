package project.gui.eventlog;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import project.io.ObjectIO;
import project.vo.Log;

public class EventLogGui extends JFrame {

	private JPanel dataPanel = new JPanel();
	private List<Log> logList = new ArrayList<Log>();

	public EventLogGui(String id) {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		setLayout(null);
		setResizable(false);
		setBounds(0, 0, 1000, 600);
		setLocationRelativeTo(null);
		setTitle("날짜 로그 확인");

		dataPanel.setBounds(30, 70, 930, 480);
		dataPanel.setLayout(null);

		SimpleDateFormat logDateFormat = new SimpleDateFormat("yyyyMMdd");
		String logFilePath = "files/membersDataFiles/" + id + "/logs/" + logDateFormat.format(new Date()) + "log.dat";
		logList = (List<Log>) new ObjectIO().loadObject(logList, logFilePath);

		String FolderPath = "files/membersDataFiles/" + id + "/logs/";

		File f = new File(FolderPath);
		File[] files = f.listFiles();

		String logFileArr[] = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			String year = files[i].getName().substring(0, 4);
			String month = files[i].getName().substring(4, 6);
			String day = files[i].getName().substring(6, 8);

			logFileArr[files.length - i - 1] = year + "년" + month + "월" + day + "일";

		}

		JComboBox comboBox = new JComboBox(logFileArr);
		comboBox.setBounds(400, 30, 200, 30);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year = String.valueOf(comboBox.getSelectedItem()).substring(0, 4);
				String month = String.valueOf(comboBox.getSelectedItem()).substring(5, 7);
				String day = String.valueOf(comboBox.getSelectedItem()).substring(8, 10);
				String selectdLogFileName = year + month + day + "log.dat";

				String logFilePath = "files/membersDataFiles/" + id + "/logs/" + selectdLogFileName;
				logList = (List<Log>) new ObjectIO().loadObject(logList, logFilePath);
				visualizeData();
			}
		});

		add(comboBox);

		add(dataPanel);
		visualizeData();

		setVisible(true);

	}

	public void visualizeData() {

		dataPanel.removeAll();
		revalidate();
		repaint();

		Object[] columnNames = { "방 이름", "상황 발생 시각", "상황", "결제 금액" };
		Object[][] rowData = new Object[logList.size()][columnNames.length];

		JTable logTable = new JTable(rowData, columnNames);
		JScrollPane scr = new JScrollPane(logTable);

		int sum = 0;

		for (int i = 0; i < logList.size(); i++) {
			Log l = logList.get(i);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy년 MM월 dd일 k시 m분 s초");
			rowData[i][0] = l.getRoomName();
			rowData[i][1] = dateFormat2.format(l.getSituationTime());
			rowData[i][2] = l.getSituationName();
			rowData[i][3] = l.getPayment();
			sum += l.getPayment();
		}

		scr.setBounds(10, 20, 900, 400);
		logTable.setPreferredScrollableViewportSize(new Dimension(263, 735));
		logTable.setAutoCreateRowSorter(true);
		logTable.getColumn("상황 발생 시각").setPreferredWidth(200);
		dataPanel.add(scr);

		JLabel sumLabel = new JLabel("매출 총액 : " + sum + " 원");
		sumLabel.setFont(new Font("HY견고딕", Font.BOLD, 25));
		sumLabel.setBounds(650, 425, 500, 50);

		dataPanel.add(sumLabel);

	}

}

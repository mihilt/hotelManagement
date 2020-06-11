package project.gui.administratormode;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import project.io.ObjectIO;
import project.vo.Member;

public class ShowMembersGui extends JFrame {

	public ShowMembersGui() {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		setBounds(0, 0, 700, 500);
		setLocationRelativeTo(null);
		setTitle("회원 열람");

		Map<String, Member> memberMap = new HashMap<>();

		String mListPath = "files/memberFile/member.dat";

		ObjectIO io = new ObjectIO();
		memberMap = (Map<String, Member>) io.loadObject(memberMap, mListPath);

		Object[] columnNames = { "아이디", "비밀번호", "이름", "주소", "추가사항" };
		Object[][] rowData = new Object[memberMap.size()][columnNames.length];

		Set<String> keySet = memberMap.keySet();

		int i = 0;
		for (String key : keySet) {

			Member value = memberMap.get(key);

			int numOfStars = value.getPassword().length();
			StringBuffer stars = new StringBuffer("");
			for (int j = 0; j < numOfStars; j++)
				stars.append("*");

			rowData[i][0] = value.getId();
			rowData[i][1] = stars;
			rowData[i][2] = value.getName();
			rowData[i][3] = value.getAddress();
			rowData[i][4] = value.getAdditions();
			i++;
		}

		JTable table = new JTable(rowData, columnNames);
		JScrollPane scr = new JScrollPane(table);

		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setAutoCreateRowSorter(true);

		table.setAutoCreateRowSorter(true);
		setResizable(false);

		add(scr);

		setVisible(true);

	}
}
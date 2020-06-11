package project.gui.administratormode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.gui.eventlog.EventLogGui;
import project.gui.eventlog.RoomLogGui;
import project.io.InitMemberDataFiles;
import project.io.ObjectIO;
import project.vo.Member;

public class AdminGui extends JFrame {

	public AdminGui() {
		setIconImage(new ImageIcon("files/otherImages/kh_logo.png").getImage());
		setBounds(0, 0, 373, 300);
		setLocationRelativeTo(null);
		setTitle("관리자 모드");
		setLayout(null);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 700, 500);

		JButton showMembersButton = new JButton();
		showMembersButton.setBounds(20, 20, 160, 60);
		showMembersButton.setText("회원 열람");
		mainPanel.add(showMembersButton);

		JButton deleteMembers = new JButton();
		deleteMembers.setBounds(190, 20, 160, 60);
		deleteMembers.setText("회원 초기화");
		mainPanel.add(deleteMembers);

		JButton showMembersLogButton = new JButton();
		showMembersLogButton.setBounds(20, 90, 160, 60);
		showMembersLogButton.setText("회원 날짜 로그 열람");
		mainPanel.add(showMembersLogButton);

		JButton showMembersRoomLogButton = new JButton();
		showMembersRoomLogButton.setBounds(190, 90, 160, 60);
		showMembersRoomLogButton.setText("회원 방 로그 열람");
		mainPanel.add(showMembersRoomLogButton);

		JButton editInitializeImageButton = new JButton();
		editInitializeImageButton.setBounds(20, 190, 160, 60);
		editInitializeImageButton.setText("기본 방 이미지 변경");
		mainPanel.add(editInitializeImageButton);

		JButton programInfoButton = new JButton();
		programInfoButton.setBounds(190, 190, 160, 60);
		programInfoButton.setText("프로그램 정보");
		mainPanel.add(programInfoButton);

		showMembersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowMembersGui();
			}
		});

		deleteMembers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "방의 데이터와 로그파일을 포함한 모든 회원들의 정보를 삭제하시겠습니까?", "주의!",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					new InitMemberDataFiles();
					deleteFolderAndFiles("files/membersDataFiles");
					JOptionPane.showMessageDialog(null, "초기화를 완료했습니다.");
				}

			}
		});

		showMembersLogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("열람하실 회원의 아이디를 입력해주세요.");
				if (id != null) {
					Map<String, Member> memberMap = new HashMap<>();

					ObjectIO io = new ObjectIO();
					memberMap = (Map<String, Member>) io.loadObject(memberMap, "files/memberFile/member.dat");

					if (memberMap.containsKey(id))
						try {
							new EventLogGui(id);
						} catch (Exception exception) {
							JOptionPane.showMessageDialog(null, "아이디는 존재하지만, 아직 로그파일이 존재하지 않습니다.");
						}
					else
						JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.");
				}

			}
		});

		showMembersRoomLogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("열람하실 회원의 아이디를 입력해주세요.");
				if (id != null) {
					Map<String, Member> memberMap = new HashMap<>();

					ObjectIO io = new ObjectIO();
					memberMap = (Map<String, Member>) io.loadObject(memberMap, "files/memberFile/member.dat");

					if (memberMap.containsKey(id))
						try {
							new RoomLogGui(id);
						} catch (Exception exception) {
							JOptionPane.showMessageDialog(null, "아이디는 존재하지만, 아직 로그파일이 존재하지 않습니다.");
						}
					else
						JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.");
				}

			}
		});

		editInitializeImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditInitRoomImageGui();

			}
		});

		programInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProgramInfoGui();

			}
		});

		add(mainPanel);
		setVisible(true);
	}

	public void deleteFolderAndFiles(String path) {
		File folder = new File(path);
		try {
			if (folder.exists()) {
				File[] folder_list = folder.listFiles();

				for (int i = 0; i < folder_list.length; i++) {
					if (folder_list[i].isFile()) {
						folder_list[i].delete();
					} else {
						deleteFolderAndFiles(folder_list[i].getPath());
					}
					folder_list[i].delete();
				}
			}
		} catch (Exception e) {
		}
	}

}

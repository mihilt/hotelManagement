package project.io;

import java.util.HashMap;
import java.util.Map;

import project.vo.Member;

public class InitMemberDataFiles {

	public InitMemberDataFiles() {

		Map<String, Member> memberMap = new HashMap<>();

		ObjectIO ioTest = new ObjectIO();
		ioTest.saveObject(memberMap, "files/memberFile/member.dat");

	}
}

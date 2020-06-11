package project.controller;

import java.util.ArrayList;
import java.util.List;

import project.io.ObjectIO;
import project.vo.Program;
import project.vo.Room;

public class InitRoom {

	public List<Room> roomSet(int numOfRooms, int price) {

		List<Room> roomList = new ArrayList<Room>();
		Program program = new Program();
		program = (Program) new ObjectIO().loadObject(program, "files/programFile/programSettingData.dat");

		for (int i = 0; i < numOfRooms; i++) {
			roomList.add(new Room("방" + String.valueOf(i + 1), "공실", price, program.getRoomImgPath()));
		}

		return roomList;
	}

}

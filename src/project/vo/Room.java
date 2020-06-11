package project.vo;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {

	private String name; 
	private String nickname;
	private String state;
	private int price;
	private String roomImg;

	private Date checkInDate;
	private Date checkOutDate;

	public Room() {
	}

	public Room(String name, String state, int price, String roomImg) {
		super();
		this.name = name;
		this.state = state;
		this.price = price;
		this.roomImg = roomImg;
	}

	public Room(String name, String nickname, String state, int price, String roomimg, Date checkInDate,
			Date checkOutDate) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.state = state;
		this.price = price;
		this.roomImg = roomimg;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getRoomImg() {
		return roomImg;
	}

	public void setRoomImg(String roomimg) {
		this.roomImg = roomimg;
	}

}

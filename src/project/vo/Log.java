package project.vo;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
	private String roomName;
	private String situationName;
	private Date situationTime;
	private int payment;

	public Log() {

	}

	public Log(String roomName, String situationName, Date situationTime, int payment) {
		super();
		this.roomName = roomName;
		this.situationName = situationName;
		this.situationTime = situationTime;
		this.payment = payment;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getSituationName() {
		return situationName;
	}

	public void setSituationName(String situationName) {
		this.situationName = situationName;
	}

	public Date getSituationTime() {
		return situationTime;
	}

	public void setSituationTime(Date situationTime) {
		this.situationTime = situationTime;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Log [roomName=" + roomName + ", situationName=" + situationName + ", situationTime=" + situationTime
				+ ", payment=" + payment + "]";
	}

}

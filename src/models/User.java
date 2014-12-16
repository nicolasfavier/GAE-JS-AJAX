package models;

import java.util.List;

public class User {
	private String nickname;
	private List<PendingTrainning> pendingTrainnings;
	
	public User(){}
	
	public User(String nickname, List<PendingTrainning> pendingTrainnings) {
		super();
		this.nickname = nickname;
		this.pendingTrainnings = pendingTrainnings;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<PendingTrainning> getPendingTrainnings() {
		return pendingTrainnings;
	}
	public void setPendingTrainnings(List<PendingTrainning> pendingTrainnings) {
		this.pendingTrainnings = pendingTrainnings;
	}
	
	
}

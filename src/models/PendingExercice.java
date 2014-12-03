package models;

public class PendingExercice {
	
	private int time;
	private boolean finish;
	private String KeyExercice; 
	private String Key;
	
	public String getKeyExercice() {
		return KeyExercice;
	}
	public void setKeyExercice(String keyExercice) {
		KeyExercice = keyExercice;
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	public PendingExercice(){}
	
	public PendingExercice(int time, boolean finish, String keyExercice,
			String key) {
		super();
		this.time = time;
		this.finish = finish;
		KeyExercice = keyExercice;
		Key = key;
	}
}

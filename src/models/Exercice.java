package models;

public class Exercice {
	
	private String key;
	private String title;
	private String description;
	private int duration;
	private int repetition;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getRepetition() {
		return repetition;
	}
	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}
	
	public Exercice(){
	}
	
	public Exercice(String key, String title, String description, int duration,
			int repetition) {
		super();
		this.key = key;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.repetition = repetition;
	}
}

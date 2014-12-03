package models;

public class Exercice {
	
	private String title;
	private String description;
	private int duration;
	private int repetition;
	private boolean finish;
	
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
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
	
	public Exercice(String title, String description, int duration,
			int repetition, boolean finish) {
		super();
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.repetition = repetition;
		this.finish = finish;
	}
}

package models;

public class PendingExercice {
	
	private int time;
	private boolean finish;
	private Long id;
	private Exercice exercice;
	
	public PendingExercice(){}
	
	public PendingExercice(int time, boolean finish, Long id, Exercice exercice) {
		super();
		this.time = time;
		this.finish = finish;
		this.id = id;
		this.exercice = exercice;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Exercice getExercice() {
		return exercice;
	}
	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}
	
	
}

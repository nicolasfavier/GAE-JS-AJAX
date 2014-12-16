package models;

import java.util.List;

public class PendingTrainning {
	private Long id;
	private Trainning trainning;
	private List<PendingExercice> pendingExercice;
	private boolean completed;
	private int time;
	
	public PendingTrainning(){
		this.time = 0;
	}

	public PendingTrainning(Long id, Trainning trainning,
			List<PendingExercice> pendingExercice, boolean completed, int time) {
		super();
		this.id = id;
		this.trainning = trainning;
		this.pendingExercice = pendingExercice;
		this.completed = completed;
		this.time = time;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Trainning getTrainning() {
		return trainning;
	}
	public void setTrainning(Trainning trainning) {
		this.trainning = trainning;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public List<PendingExercice> getPendingExercice() {
		return pendingExercice;
	}
	public void setPendingExercice(List<PendingExercice> pendingExercice) {
		this.pendingExercice = pendingExercice;
		this.completed = true;
		for (PendingExercice pendingEx : this.pendingExercice){
			if (!pendingEx.isFinish())
				this.completed = false;
			
			this.time += pendingEx.getTime();
		}
	}
	
}

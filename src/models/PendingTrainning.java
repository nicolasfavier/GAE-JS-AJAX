package models;

import java.util.Date;
import java.util.List;

public class PendingTrainning {
	private Long id;
	private Long trainningId;
	private Date date;
	private String title;
	private String description;
	private int expectedTime;
	private List<PendingExercice> pendingExercice;
	private boolean completed;
	private int time;
	
	
	
	public Long getTrainningId() {
		return trainningId;
	}

	public void setTrainningId(Long trainningId) {
		this.trainningId = trainningId;
	}

	public PendingTrainning(){
		this.time = 0;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(int expectedTime) {
		this.expectedTime = expectedTime;
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

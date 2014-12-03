package models;

import java.util.List;

public class PendingTrainning {
	private String KeyTrainning;
	private String Key;
	private List<PendingExercice> pendingExercice;
	
	public String getKeyTrainning() {
		return KeyTrainning;
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public void setKeyTrainning(String keyTrainning) {
		KeyTrainning = keyTrainning;
	}
	public List<PendingExercice> getPendingExercice() {
		return pendingExercice;
	}
	public void setPendingExercice(List<PendingExercice> pendingExercice) {
		this.pendingExercice = pendingExercice;
	}
	
	public PendingTrainning(String keyTrainning, String key,
			List<PendingExercice> pendingExercice) {
		super();
		KeyTrainning = keyTrainning;
		Key = key;
		this.pendingExercice = pendingExercice;
	}
	
	public boolean isFinished(){
		boolean res = true;
		int i =0;
		
		for(i=0; i< pendingExercice.size(); i++)
		{
			if (pendingExercice.get(i).isFinish() == false){
				res = false;
			}
		}
		return res;
	}
	
	public int timeSpend(){
		int res = 0;
		int i =0;
		
		for(i=0; i< pendingExercice.size(); i++)
		{
			res += pendingExercice.get(i).getTime();
		}
		return res;
	}
	
}

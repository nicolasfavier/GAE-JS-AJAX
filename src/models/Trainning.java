package models;

import java.util.Date;
import java.util.List;



public class Trainning {
		public enum Kind {
		  RUN,
		  FITNESS,
		  SIMMING,
		  TENNIS;	
		}
	
		private Date date;
		private int expectedTime;
		private Kind kind;
		private List<Exercice> exercices;
		private String key;
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getExpectedTime() {
			return expectedTime;
		}
		public void setExpectedTime(int expectedTime) {
			this.expectedTime = expectedTime;
		}
		public Kind getKind() {
			return kind;
		}
		public void setKind(Kind kind) {
			this.kind = kind;
		}
		public List<Exercice> getExercices() {
			return exercices;
		}
		public void setExercices(List<Exercice> exercices) {
			this.exercices = exercices;
		}
		public Trainning(Date date, int expectedTime, Kind kind,
				List<Exercice> exercices, String key) {
			super();
			this.date = date;
			this.expectedTime = expectedTime;
			this.kind = kind;
			this.exercices = exercices;
			this.key = key;
		}
}

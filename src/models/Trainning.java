package models;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;



public class Trainning {
		public enum Kind {
		  @SerializedName("Running")
		  RUNNING("Running"),
		  
		  @SerializedName("Fitness")
		  FITNESS("Fitness"),
		  
		  @SerializedName("Swimming")
		  SWIMMING("Swimming"),
		  
		  @SerializedName("Tennis")
		  TENNIS("Tennis");
		  
		   private final String value;
		   public String getValue() {
		        return value;
		   }

		   private Kind(String value) {
		        this.value = value;
		   }
		}
	
		private Date date;
		private String title;
		private String description;
		private int expectedTime;
		private Kind kind;
		private List<Exercice> exercices;
		private String key;
		
		public Trainning(){}
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public Date getDate() {
			return date;
		}
		
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
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
				List<Exercice> exercices, String key, String title, String description) {
			super();
			this.date = date;
			this.expectedTime = expectedTime;
			this.kind = kind;
			this.exercices = exercices;
			this.key = key;
			this.title = title;
			this.description = description;
		}
}

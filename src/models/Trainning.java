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
		private int time;
		private boolean finish;
		private Kind kind;
		private List<Exercice> exercices;
		
		
}

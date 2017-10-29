package ar.edu.itba.pod.tpe.utils;

import java.io.Serializable;

public class EmploymentStatistics implements Serializable{

	private long serialVersionUID = 1L;

	private int employed;
	private int unemployed;

	public EmploymentStatistics(){
		this.employed = 0;
		this.unemployed = 0;
	}

	public void addEmployed(){
		this.addEmployed(1);
	}

	public void addEmployed(int amount){
		this.employed += amount;
	}

	public void addUnemployed(){
		this.addUnemployed(1);
	}

	public void addUnemployed(int amount){
		this.unemployed += amount;
	}

	public int getEmployed(){
		return this.employed;
	}

	public int getUnemployed(){
		return this.unemployed;
	}
}

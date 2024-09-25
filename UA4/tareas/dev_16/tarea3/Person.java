package com.mycompany.tarea3;

public class Person {
	private int id;
	private String name;
	private String about;
	private int birthYear;
	
	public Person(int id, String name, String about, int birthYear) {
		this.id=id;
		this.name = name;
		this.about = about;
		this.birthYear = birthYear;
	}

	public Person(){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAbout() {
		return about;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setId(int id){
		this.id=id;
	}

    public void setName(String name){
        this.name=name;
    }

    public void setAbout(String about){
        this.about=about;
    }

    public void setBirthYear(int birthYear){
        this.birthYear=birthYear;
    }

}
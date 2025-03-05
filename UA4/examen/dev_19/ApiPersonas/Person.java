package main.com.example.personapi;

public class Person {
    private int id;
    private String name;
    private String about;
    private int birthYear;

    // Constructor
    public Person(int id, String name, String about, int birthYear) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.birthYear = birthYear;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}

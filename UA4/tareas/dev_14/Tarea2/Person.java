public class Person {
	private String name;
	private String about;
	private int birthYear;
	
	public Person(String name, String about, int birthYear) {
		this.name = name;
		this.about = about;
		this.birthYear = birthYear;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
}
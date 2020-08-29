package dataStructures;

public class Student implements Comparable<Student> {
	
	private String firstName;
	private String lastName;
	private int id;
	
	//Constructor
	Student(String firstN, String lastN, int idNumber){
		firstName = firstN;
		lastName = lastN;
		id = idNumber;
	}
	
	//Getters
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public int 	getID() {return id;}
	
	//Setters
	public void setFirstName(String firstN) {firstName = firstN;}
	public void setLastName(String lastN) {lastName = lastN;}
	public void setID(int idNumber) {id = idNumber;}
	
	
	public String toString() {
		return "[" + id + "]" + "\t " + firstName + " "+ lastName; 
	}
	
	//Equals() compares students id
	public boolean equals(Student other) {
		return (other != null && this.id == other.getID());
	}
	
	//Compares last names and if needed first names, too
	@Override  
	public int compareTo(Student other) {
		int i = this.lastName.compareTo(other.getLastName());
		if(i != 0) return i;
		i = this.firstName.compareTo(other.getFirstName());
		if(i != 0) return i;
		return this.id-other.id;
	}
	
	public int compareByName(String name, String lastName) {
		int i = this.lastName.compareTo(lastName);
		if(i != 0) return i;
		i = this.firstName.compareTo(name);
		return i;
	}
	
}

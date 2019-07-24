package students;

public class Student {

	String firstname;
	String lastname;
	String studymajor;
	double grade;

	
	public Student() {
		super();
		this.firstname ="Boilerplate";
		this.lastname = "Student";
		this.studymajor = "AllStudies";
		this.grade = 5.0;
	}
		

	public Student(String firstname, String lastname, String studymajor, double grade) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.studymajor = studymajor;
		this.grade = grade;
	}


	public String getStudymajor() {
		return studymajor;
	}


	public void setStudymajor(String studymajor) {
		this.studymajor = studymajor;
	}


	@Override
	public String toString() {
		return "Student [firstname=" + firstname + ", lastname=" + lastname + ", studymajor=" + studymajor + ", grade="
				+ grade + "]";
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}


}

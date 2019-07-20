package students;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentMain {


	public static void main(String[] args) {
		List<Student> students = generateStudents();

		// filter
		System.out.println("Wi-Inf students with a grade of 4.0 or better:");
		students.stream()
				.filter(byWiInfs())
				.collect(Collectors.toList())
				.forEach(s -> System.out.println(s.toString()));
		// Prädikat lohnt sich nur bei komplexeren ausdrücken und bei Generalisierung; sonst einfache boolean Methode.

		// reduce
		System.out.println("\nStudent with the best grade:");
		System.out.println(
				students.stream()
						.reduce((s1, s2) -> {
							return (s1.grade < s2.grade) ? s1 : s2;
						}).get()
						.toString()
		);
		// besser: mit Methodenreferenz
		System.out.println(
				students.stream()
					.reduce(Student::lowestGrade)
					.get()
					.toString()
		);
		// noch schöner, gibt aber double zurück
		System.out.println(
				students.stream()
					.map(Student::getGrade)
					.reduce(Math::min)
					.get()
					.toString()
		);
	}

	private static Predicate<Student> byWiInfs() {
		return student -> student.studymajor.equals("Wi-Inf") && student.grade <= 4.0;
	}

	public static List<Student> generateStudents() {
		List<Student> students = new ArrayList<Student>();
		Student student = new Student("John", "Doe","Wi-Inf",1.0);
		Student student1 = new Student("Jack", "Doe", "Wi-Inf", 5.0);
		Student student2 = new Student("Chuck", "Norris", "Everything", 0.9);
		Student student3 = new Student("John", "Hancock", "DrugPrevention", 1.3);
		Student student4 = new Student("Peter", "Pan", "PiracyStudies", 3.7);
		Student student5 = new Student("Tony", "Stark", "ElectricalEngineering", 1.7);
		Student student6 = new Student("Peter", "Parker", "Biology", 5.0);
		Student student7 = new Student("Bruce", "Wayne", "BusinessAdministration", 4.0);
		Student student8 = new Student("Clark", "Kent", "Journalism", 1.0);
		Student student9 = new Student("Logan", "Howlett", "IndustrialDesign", 2.7);
		Student student10 = new Student("Robert Bruce", "Banner", "Physics", 5.0);
		Student student11 = new Student("Arthur", "Dent", "Ultimate Question of Life, the Universe, and Everything",42.0);
		Student student12 = new Student("Alice", "Wonderland", "Wi-Inf", 2.0);
		Student student13 = new Student("Bob", "Wonderland", "Wi-Inf", 3.0);
		
		students.add(student);
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);
		students.add(student8);
		students.add(student9);
		students.add(student10);
		students.add(student11);
		students.add(student12);
		students.add(student13);
		return students;
	}
	
}

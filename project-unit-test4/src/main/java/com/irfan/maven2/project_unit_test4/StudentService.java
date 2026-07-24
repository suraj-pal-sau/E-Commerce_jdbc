package com.irfan.maven2.project_unit_test4;

import java.util.*;
import java.util.stream.*;

public class StudentService
{
	private List<Student> students;

    public StudentService(List<Student> students)
    {
        this.students = students;
    }
    
    
	public Student findStudentById(int id) {
		for(Student std: students) {
			if(std.getId() == id) {
				return std;
			}
		}
		return null;
	}

	public Student findStudentByEmail(String email) {
		for(Student std: students) {
			if(std.getEmail().equals(email)) {
				return std;
			}
		}
		return null;
	}

	public List<Student> getAllStudents(){
		return students;
	}

	public List<Student> getStudentsByDepartment(String department){
		List<Student> depart = new ArrayList<Student>();
		for(Student std: students) {
			if(std.getDepartment().equals(department)) {
				depart.add(std);
			}
		}
		return depart;
	}

	public List<Student> getPassedStudents(){
		List<Student> depart = new ArrayList<Student>();
		for(Student std: students) {
			if(std.getMarks() >= 60) {
				depart.add(std);
			}
		}
		return depart;
	}

	public List<Student> getFailedStudents(){
		List<Student> depart = new ArrayList<Student>();
		for(Student std: students) {
			if(std.getMarks() < 60) {
				depart.add(std);
			}
		}
		return depart;
	}

	public Student getTopper() {
		Student topper = students.stream().sorted( (s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()) ).findFirst().get();
		return topper;
	}

	public Student getLowestScorer() {
		Student bottom = students.stream().sorted( (s1, s2) -> Double.compare(s1.getMarks(), s2.getMarks()) ).findFirst().get();
		return bottom;
	}

	public double calculateAverageMarks() {
		return students.stream().collect(Collectors.averagingDouble(s -> s.getMarks()));
	}

	public double calculateHighestMarks() {
		return students.stream().sorted( (s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()) ).findFirst().get().getMarks();
	}

	public double calculateLowestMarks() {
		return students.stream().sorted( (s1, s2) -> Double.compare(s1.getMarks(), s2.getMarks()) ).findFirst().get().getMarks();
	}

	public List<Student> sortByMarks(){
		return students.stream().sorted( (s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()) ).collect(Collectors.toList());
	}

	public List<Student> sortByName(){
		students.sort(Comparator.comparing(Student::getName));
		return students;
	}

	public List<Student> sortByAge(){
		return students.stream().sorted( (s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()) ).collect(Collectors.toList());
	}

	public long countMaleStudents() {
		return students.stream().filter(s -> s.getGender().equals("Male")).count();
	}

	public long countFemaleStudents() {
		return students.stream().filter(s -> s.getGender().equals("Female")).count();
	}

	public Map<String, Long> countDepartmentWise(){
		return students.stream().collect(Collectors.groupingBy(s -> s.getDepartment(), Collectors.counting()));
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public boolean deleteStudent(int id) {
		for(Student std: students) {
			if(std.getId() == id) {
				students.remove(std);
				return true;
			}
		}
		return false;
	}

	public boolean updateMarks(int id, double newMarks) {
		for(Student std: students) {
			if(std.getId() == id) {
				std.setMarks(newMarks);
				return true;
			}
		}
		return false;
	}

	public boolean updateEmail(int id, String email) {
		for(Student std: students) {
			if(std.getId() == id) {
				std.setEmail(email);
				return true;
			}
		}
		return false;
	}

	public List<Student> searchByName(String keyword){
		List<Student> names = new ArrayList<Student>();

		for(Student std: students) {
			if(std.getName().contains(keyword)) {
				names.add(std);
			}
		}
		return names;
	}

	public List<Student> getStudentsAboveMarks(double marks){
		return students.stream().filter(s -> s.getMarks() > marks).collect(Collectors.toList());
	}

	public List<Student> getStudentsBelowMarks(double marks){
		return students.stream().filter(s -> s.getMarks() < marks).collect(Collectors.toList());
	}
}

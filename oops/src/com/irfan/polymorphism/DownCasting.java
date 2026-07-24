package com.irfan.polymorphism;

class Teacher
{
	public void doTeaching()
	{
		System.out.println("Teacher.doTeaching()");
	}
}

class MathTeacher extends Teacher
{
	@Override
	public void doTeaching()
	{
		System.out.println("MathTeacher.doTeaching()");
	}
}
public class DownCasting {
	public static void main(String[] args) {
		// MathTeacher mathTeacher0 = (MathTeacher) new Teacher();	/// this downcasting will give runtime error of 'ClassCastException'
		
		Teacher teacher = new MathTeacher();	/// upcasting
		//MathTeacher mathTeacher1 = teacher;	//teacher is of type Teacher(parent) but object type is of MathTeacher(child)
		// here we are doing downcasting and so that's why we have to give (target_type) operator
						
		/// giving (target_type) -> (MathTeacher)
		MathTeacher mathTeacher2 = (MathTeacher) teacher;	/// this downcasting we mean it here, it won't give 
														/// runtime exception like for the above downcasting because 'teacher' is object of child class already
														/// here only converting type of 'teacher' 
		System.out.println("DownCasting.main()");
		
	}
}

package com.irfan.inheritance.override;

//20.04.26

class Animal {
    Animal get() { return new Animal(); }
}

class Dog extends Animal {
    @Override
    Dog get() { 
    	System.out.println("Dog.get()...Here, Covariant return: Dog is a subtype of Animal");
    	return new Dog(); 
    } 
    
    // Covariant return: Dog is a subtype of Animal
}


public class CovariantReturnType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog = new Dog();
		dog.get();
		
	}

}

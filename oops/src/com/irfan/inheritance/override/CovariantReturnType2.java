package com.irfan.inheritance.override;

class Shape {
    Shape draw() {
        return new Shape();
    }
}

class Circle extends Shape {
    @Override
    Circle draw() {
        return new Circle();
    }
}

public class CovariantReturnType2 {
	public static void main(String[] args) {
        Circle c = new Circle();
        c.draw();  // No compilation issue
        System.out.println("Covariant return type example");
    }
}

package com.irfan.singleton;

/*
 * In this code, the constructor Singleton() is private, so no other class can instantiate it directly.
 * The static method getInstance() creates the instance the first time it’s needed,
 * and after that, it just returns the same instance.
 * 
 * This approach guarantees a single, globally accessible instance of the class for the entire application.
 */

public class Singleton {
    // The single instance is stored in a private static variable
    private static Singleton instance;

    // Private constructor prevents external instantiation
    private Singleton() {
        // Initialization code here
    }

    // Public method to provide access to the single instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Other useful methods of the Singleton class can follow here...
}

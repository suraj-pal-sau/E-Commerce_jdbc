package com.irfan.singleton;

/*
 * Let’s look at a real-world example of the Singleton design pattern in Java.
 * A very common use case is a Logger — you want only one logging system in your application so that all parts of the program write to the same log file consistently.
 * 
 * Why this is a good real-world example:
 * Consistency: All parts of the app write to the same log.
 * Resource management: Only one logger object is created, saving memory.
 * Global access: Any class can access the logger via Logger.getInstance().
 */

 class Logger {
    // Single instance of Logger
    private static Logger instance;

    // Private constructor prevents external instantiation
    private Logger() {
        System.out.println("Logger initialized.");
    }

    // Public method to provide access to the single instance
     static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Method to log messages
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}

// Usage in another class
public class ApplicationLogger {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started.");

        Logger logger2 = Logger.getInstance();
        logger2.log("Another part of the app is running.");

        // Both logger1 and logger2 point to the same instance
        System.out.println(logger1 == logger2); // true
    }
}

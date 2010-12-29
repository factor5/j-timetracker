package com.sve.timetracker;

import java.util.Observable;
import java.util.Observer;

public class MessageBoard extends Observable {
	private String message;

	public String getMessage() {
		return message;
	}

	public void changeMessage(final String message) {
		this.message = message;
		setChanged();
		notifyObservers(message);
	}

	public static void main(final String[] args) {
		final MessageBoard board = new MessageBoard();
		final Student bob = new Student("Bob");
		final Student joe = new Student("Joe");
		board.addObserver(bob);
		board.addObserver(joe);
		board.changeMessage("More Homework!");
	}
}

class Student implements Observer {
	private String name;

	public Student(final String name) {
		this.name = name;
	}

	public void update(final Observable o, final Object arg) {
		System.out.println("Message board changed: " + arg + " said " + name);
	}
}
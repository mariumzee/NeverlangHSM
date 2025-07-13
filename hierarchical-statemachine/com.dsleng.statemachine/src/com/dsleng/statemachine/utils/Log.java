/**
 * 
 */
package com.dsleng.statemachine.utils;

import java.util.List;

import com.dsleng.statemachine.IState;

/**
 * @
 *
 */
public class Log {

	/**
	 * 
	 */
	public Log() {
		// TODO Auto-generated constructor stub
	}

	public static void out(String data) {
		System.out.println(data);
	}

	public static void currentState(List<IState> states) {
		System.out.println("Current State of Statemachine");
		for (IState s : states) {
			System.out.println("Current State: " + s.getName());
		}
	}

	public static void finalState(IState s) {
		System.out.println("Sucessful Execution reached final state: " + s.getName());
	}
}

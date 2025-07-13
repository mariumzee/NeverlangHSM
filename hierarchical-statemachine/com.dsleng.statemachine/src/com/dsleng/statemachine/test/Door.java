/**
 * 
 */
package com.dsleng.statemachine.test;

import com.dsleng.statemachine.impl.Effect;
import com.dsleng.statemachine.impl.Guard;
import com.dsleng.statemachine.impl.SimulEvents;
import com.dsleng.statemachine.impl.MultiTriggerForkState;
import com.dsleng.statemachine.impl.State;
import com.dsleng.statemachine.impl.StateMachine;
import com.dsleng.statemachine.impl.Trigger;

/**
 * @
 *
 */
public class Door extends StateMachine {

	public Door() {
		init();
	}

	class OpenState extends State {

		public OpenState() {
			super("open");
		}

	}

	class LockState extends State {

		public LockState() {
			super("lock");
		}

	}

	private void init() {
		OpenState s1 = new OpenState();
		s1.makeInitial();
		addState(s1);

		ClosedState s2 = new ClosedState();
		addState(s2);

		LockState s3 = new LockState();
		addState(s3);

		State s4 = new State("s4");
		addState(s4);

		MultiTriggerForkState s5 = new MultiTriggerForkState("fork");
		addState(s5);
		State s6 = new State("left");
		State s7 = new State("right");

		s5.setForkTransition(new Trigger("f1"), new Guard(""), new Effect(""));
		s5.setLeftTransition(s6, new Trigger("f2"), new Guard(""), new Effect(""));
		s5.setRightTransition(s7, new Trigger("f3"), new Guard(""), new Effect(""));

		s1.createTransition(s2, new Trigger("close"), new Guard(""), new Effect(""));
		s1.createTransition(s4, new Trigger("t3"), new Guard(""), new Effect(""));

		s2.createTransition(s1, new Trigger("open"), new Guard(""), new Effect(""));

		s2.createTransition(s3, new Trigger("lock"), new Guard(""), new Effect(""));

		s4.createTransition(s5, new Trigger("f0"), new Guard(""), new Effect(""));

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Door d = new Door();
		try {
			d.Start();
			// d.processExternalEvent(new Trigger("close"));
			// d.processExternalEvent(new Trigger("open"));
			SimulEvents s = new SimulEvents();
			s.add(new Trigger("close"));
			s.add(new Trigger("lock"));
			s.add(new Trigger("t3"));
			d.processExternalEvents(s);
			d.processExternalEvent(new Trigger("f0"));
			SimulEvents s1 = new SimulEvents();
			s1.add(new Trigger("f1"));
			s1.add(new Trigger("f2"));
			s1.add(new Trigger("f3"));
			d.processExternalEvents(s1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

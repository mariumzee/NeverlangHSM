/**
 * 
 */
package com.dsleng.statemachine.test;



import com.dsleng.statemachine.impl.CompositeState;
import com.dsleng.statemachine.impl.Effect;
import com.dsleng.statemachine.impl.Guard;
import com.dsleng.statemachine.impl.State;
import com.dsleng.statemachine.impl.SimulEvents;
import com.dsleng.statemachine.impl.MultiTriggerForkState;
import com.dsleng.statemachine.impl.SingleTriggerForkState;
import com.dsleng.statemachine.impl.StateMachine;
import com.dsleng.statemachine.impl.Trigger;

public class STest extends StateMachine {
	public STest(){
		State S0 = new State("S0");
		S0.makeInitial();
		addState(S0);

		State S1 = new State("S1");
		addState(S1);
		
		CompositeState S2 = new CompositeState("S2");
		State S3 = new State("S3");
		S2.addState(S3);
		S2.setStart(S3);
		
		State S4 = new State("S4");
		S3.createTransition(S4,new Trigger("t3"),new Guard("g1"),new Effect("e1"));
		S2.addState(S4);
		addState(S2);
		
		State S5 = new State("S5");
		S5.makeFinal();
		addState(S5);
		
		S4.createTransition(S5,new Trigger("t4"),new Guard("g1"),new Effect("e1"));
		
		S0.createTransition(S1,new Trigger("t1"),new Guard("g1"),new Effect("e1"));
		S1.createTransition(S2,new Trigger("t2"),new Guard("g1"),new Effect("e1"));
/*
		State S2 = new State("S3");
		S2.makeFinal();
		addState(S2);

		State S3 = new State("S5");
		addState(S3);

		State S4 = new State("S6");
		S4.makeFinal();
		addState(S4);

		MultiTriggerForkState S5 = new MultiTriggerForkState("S4");
		addState(S5);
		S5.setForkTransition(new Trigger("t4"),new Guard("g1"),new Effect("e1"));;

		S5.setLeftTransition(S3,new Trigger("t5"),new Guard("g1"),new Effect("e1"));;

		S5.setRightTransition(S4,new Trigger("t6"),new Guard("g1"),new Effect("e1"));;




		S0.createTransition(S1,new Trigger("t1"),new Guard("g1"),new Effect("e1"));
		S1.createTransition(S2,new Trigger("t2"),new Guard("g1"),new Effect("e1"));
		S1.createTransition(S5,new Trigger("t3"),new Guard("g1"),new Effect("e1"));
*/
	}
	public static void main(String[] args) {
		STest s = new STest();
		try {

			System.out.println("Processing:PS1");
			s.Start();
			s.processExternalEvent(new Trigger("t1"));

			s.processExternalEvent(new Trigger("t2"));

			s.processExternalEvent(new Trigger("t3"));
			
			s.processExternalEvent(new Trigger("t4"));
/*
			System.out.println("Processing:PS2");
			s.Start();
			s.processExternalEvent(new Trigger("t1"));

			s.processExternalEvent(new Trigger("t3"));

			s.processExternalEvent(new Trigger("t4"));

			SimulEvents E0 = new SimulEvents();
			E0.add(new Trigger("t5"));

			E0.add(new Trigger("t7"));
			s.processExternalEvents(E0);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


/**
 * 
 */
package com.dsleng.statemachine.impl;

import java.util.ArrayList;
import java.util.List;

import com.dsleng.statemachine.IEvent;
import com.dsleng.statemachine.IState;

/**
 * @
 *
 */
public class SingleTriggerForkState extends MultiTriggerForkState {

	/**
	 * @param name
	 */
	public SingleTriggerForkState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IState> process(IEvent e) {
		List<IState> states = new ArrayList<IState>();
		if (forkTransition.getTrigger().equals(e) && !forkTransition.isUsed()) {
			forkTransition.setUsed();
			states.add(leftTransition.getTgt());
			states.add(rightTransition.getTgt());
		}
		return states;
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dsleng.statemachine.IPseudoState#process(java.util.List)
	 */
	@Override
	public List<IState> process(List<IEvent> se) {
		List<IState> states = new ArrayList<IState>();
		for (IEvent e : se) {
			if (forkTransition.getTrigger().equals(e)) {
				_used.add(e);
				states.add(leftTransition.getTgt());
				states.add(rightTransition.getTgt());
			}
		}
		return states;
	}

}

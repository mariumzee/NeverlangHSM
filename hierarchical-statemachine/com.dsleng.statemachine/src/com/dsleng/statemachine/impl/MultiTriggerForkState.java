/**
 * 
 */
package com.dsleng.statemachine.impl;

import java.util.ArrayList;
import java.util.List;

import com.dsleng.statemachine.IEvent;
import com.dsleng.statemachine.IState;
import com.dsleng.statemachine.StateTypes;

/**
 * @
 *
 */
public class MultiTriggerForkState extends State {

	protected Transition forkTransition;
	protected Transition leftTransition;
	protected Transition rightTransition;
	protected List<IEvent> _used;

	/**
	 * @param name
	 */
	public MultiTriggerForkState(String name) {
		super(name);
		Id = StateTypes.SimulFork;
		_used = new ArrayList<IEvent>();
	}

	@Override
	public List<IState> process(IEvent e) {
		List<IState> states = new ArrayList<IState>();
		if (forkTransition.getTrigger().equals(e) && !forkTransition.isUsed()) {
			forkTransition.setUsed();
			states.add(this);
		}
		// TODO: Need to check behaviour for different implementations
		if (leftTransition.getTrigger().equals(e) && !leftTransition.isUsed()) {
			states.add(leftTransition.getTgt());
		}
		if (rightTransition.getTrigger().equals(e) && !rightTransition.isUsed()) {
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
			}
			if (leftTransition.getTrigger().equals(e)) {
				_used.add(e);
				states.add(leftTransition.getTgt());
			}
			if (rightTransition.getTrigger().equals(e)) {
				_used.add(e);
				states.add(rightTransition.getTgt());
			}
		}
		return states;
	}

	@Override
	public boolean canProcess(IEvent e) {
		if (forkTransition.getTrigger().equals(e)) {
			return true;
		}
		return false;
	};

	public boolean canProcess(List<IEvent> evts) {
		for (IEvent e : evts) {
			if (canProcess(e)) {
				return true;
			}
		}
		return false;
	}

	public void setForkTransition(Trigger t, Guard g, Effect e) {
		forkTransition = new Transition(this, null);
		forkTransition.setEffect(e);
		forkTransition.setTrigger(t);
		forkTransition.setGuard(g);
	}

	public void setLeftTransition(State tgt, Trigger t, Guard g, Effect e) {
		leftTransition = new Transition(this, tgt);
		leftTransition.setEffect(e);
		leftTransition.setTrigger(t);
		leftTransition.setGuard(g);
	}

	public void setRightTransition(State tgt, Trigger t, Guard g, Effect e) {
		rightTransition = new Transition(this, tgt);
		rightTransition.setEffect(e);
		rightTransition.setTrigger(t);
		rightTransition.setGuard(g);
	}

	@Override
	public List<IEvent> getUsedEvents() {
		return _used;
	}

}

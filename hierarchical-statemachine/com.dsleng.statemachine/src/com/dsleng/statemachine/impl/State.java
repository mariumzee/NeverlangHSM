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
public class State implements IState {
	protected StateTypes Id;
	protected String name;
	protected List<Transition> _transitions;
	protected boolean _final;
	protected boolean _initial;
	protected IState _parent;
	protected boolean _used;

	protected State(StateTypes Id, String name) {
		this.Id = Id;
		this.name = name;
		_transitions = new ArrayList<Transition>();
		_final = false;
		_initial = false;
		_used = false;
	}

	public State(String name) {
		this.Id = StateTypes.Simple;
		this.name = name;
		_transitions = new ArrayList<Transition>();
		_final = false;
		_initial = false;
		_used = false;
	}

	public void makeFinal() {
		_final = true;
		Id = StateTypes.Final;
	}

	public void makeInitial() {
		_initial = true;
		Id = StateTypes.Initial;
	}

	public boolean isFinal() {
		return _final;
	}

	public boolean isInitial() {
		return _initial;
	}

	public List<Transition> getTransitions() {
		return _transitions;
	}

	public void Reset() {
		for (Transition t : _transitions) {
			t.resetUsed();
		}
	}

	public void createTransition(State tgt, Trigger t, Guard g, Effect e) {
		Transition trans = new Transition(this, tgt);
		trans.setEffect(e);
		trans.setTrigger(t);
		trans.setGuard(g);
		_transitions.add(trans);
	}

	public List<IState> process(IEvent e) {
		List<IState> states = new ArrayList<IState>();
		// Add Code to Handle different types of transitions such as for transition
		for (Transition t : _transitions) {
			if (t.getTrigger().equals(e) && !t.isUsed()) {
				t.setUsed();
				states.add(t.getTgt());
				return states;
			}
		}
		return null;
	}

	@Override
	public boolean canProcess(IEvent e) {
		for (Transition t : _transitions) {
			if (t.getTrigger().equals(e) && !t.isUsed()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public StateTypes getId() {
		return Id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<IState> process(List<IEvent> se) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canProcess(List<IEvent> e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IEvent> getUsedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(IState s) {
		_parent = s;

	}

	@Override
	public IState getParent() {
		return _parent;
	}

	@Override
	public void setUsed() {
		_used = true;

	}

	@Override
	public boolean getUsed() {
		return _used;
	}

	@Override
	public void ResetUsed() {
		_used = false;

	}

}

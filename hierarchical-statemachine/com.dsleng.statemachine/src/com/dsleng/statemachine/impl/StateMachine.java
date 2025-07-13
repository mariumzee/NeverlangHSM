/**
 * 
 */
package com.dsleng.statemachine.impl;

import java.util.ArrayList;
import java.util.List;

import com.dsleng.statemachine.IState;
import com.dsleng.statemachine.utils.Log;

/**
 * @
 *
 */
public class StateMachine {
	private List<IState> _current;
	private List<IState> _states;
	private List<IState> _tmpCurrent;

	public StateMachine() {
		_current = new ArrayList<IState>();
		_tmpCurrent = new ArrayList<IState>();
		_states = new ArrayList<IState>();
	}

	public void Start() throws Exception {
		if (_current == null)
			throw new Exception("Initialize Statemachine");
		_current.clear();
		for (IState s : _states) {
			s.Reset();
			if (s.isInitial()) {
				_current.add(s);
			}
		}
	}

	public void addState(State s) {
		_states.add(s);
	}

	// Loops through events and processes first state that can accept the event
	public IState canProcess(Event e) {
		for (IState s : _current) {
			if (s.canProcess(e)) {
				return s;
			}
		}
		return null;
	}

	public void UpdateStates(List<IState> newcurr) {
		// Remove first all other states from list and add only this one
		_current.clear();
		for (IState s : newcurr) {
			_current.add(s);
			System.out.println("New Current State: " + s.getName());
			if (s.isFinal()) {
				System.out.println("Sucessful Execution reached final state: " + s.getName());
			}
		}

	}

	public void processExternalEvent(Event e) throws Exception {
		if (_current == null)
			throw new Exception("Initialize Statemachine");
		System.out.println("Received Event: " + e.getName());
		IState curr = canProcess(e);
		if (curr != null) {
			System.out.println("Processing State: " + curr.getName());
			List<IState> newcurr = curr.process(e);
			if (newcurr != null) {
				UpdateStates(newcurr);
			}
		}

	}

	public void processExternalEvents(SimulEvents se) throws Exception {
		if (_current == null)
			throw new Exception("Initialize Statemachine");
		Log.currentState(_current);
		_tmpCurrent.clear();

		for (IState s : _current) {
			switch (s.getId()) {
				case Initial:
				case Final:
				case Simple:
					for (Event ev : se) {
						IState curr = canProcess(ev);
						if (curr != null) {
							List<IState> newcurr = curr.process(ev);
							for (IState sc : newcurr) {
								_tmpCurrent.add(sc);
							}
							se.Update(ev);
						}

					}
					se.Commit();
					break;
				case SimulFork:
					if (s.canProcess(se.getEvents())) {
						List<IState> newcurr = s.process(se.getEvents());
						for (IState sc : newcurr) {
							_tmpCurrent.add(sc);
						}
						se.Update(s.getUsedEvents());
					}
					break;
				default:
					break;

			}
		}
		// Update current after all is processed
		_current.clear();
		for (IState s : _tmpCurrent) {
			s.Reset();
			_current.add(s);
			if (s.isFinal()) {
				Log.finalState(s);
			}
		}
		Log.currentState(_current);
	}
	/*
	 * private void processInternalEvent(IEvent e){
	 * if ( _current.canProcess(e) ){
	 * _current = _current.process(e);
	 * }
	 * }
	 */
}

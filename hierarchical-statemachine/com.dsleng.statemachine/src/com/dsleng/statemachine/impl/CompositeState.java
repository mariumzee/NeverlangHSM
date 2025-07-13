/**
 * 
 */
package com.dsleng.statemachine.impl;

import java.util.ArrayList;
import java.util.List;

import com.dsleng.statemachine.IEvent;
import com.dsleng.statemachine.IState;
import com.dsleng.statemachine.StateTypes;
import com.dsleng.statemachine.utils.Log;

/**
 * @
 *
 */
public class CompositeState extends State {
	private List<IState> _current;
	protected List<IState> _containedStates;

	/**
	 * @param Id
	 * @param name
	 */
	public CompositeState(String name) {
		super(StateTypes.Composite, name);
		_containedStates = new ArrayList<IState>();
		_current = new ArrayList<IState>();
	}

	public void setStart(IState s) {
		_current.add(s);
	}

	public void addState(IState s) {
		s.setParent(this);
		_containedStates.add(s);
	}

	@Override
	public boolean canProcess(IEvent e) {
		for (IState s : _current) {
			if (s.canProcess(e)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<IState> process(IEvent e) {
		List<IState> newstates = new ArrayList<IState>();
		List<IState> tmp = new ArrayList<IState>();
		for (IState s : _current) {
			if (s.canProcess(e)) {
				List<IState> newcurrent = s.process(e);
				for (IState ns : newcurrent) {
					if (!_containedStates.contains(ns)) {
						newstates.add(ns);
					} else {
						tmp.add(ns);
					}
				}
				_current = tmp;
				Log.out("Composite State: " + this.getName());
				Log.currentState(_current);
				if (newstates.size() != 0) {
					return newstates;
				}
			}
		}
		return null;
	}

	@Override
	public void Reset() {
		for (IState s : _current) {
			s.ResetUsed();
		}
		super.Reset();
	}

	@Override
	public List<IState> process(List<IEvent> se) {
		List<IState> tmp = new ArrayList<IState>();
		for (IEvent e : se) {
			for (IState s : _current) {
				if (s.canProcess(e) && !s.getUsed()) {
					tmp.addAll(s.process(e));
					s.setUsed();
				}
			}
		}
		_current.clear();
		List<IState> newstates = new ArrayList<IState>();
		for (IState ns : tmp) {
			if (!_containedStates.contains(ns)) {
				newstates.add(ns);
			} else {
				_current.add(ns);
			}
		}
		this.Reset();
		if (newstates.size() != 0) {
			return newstates;
		} else {
			return null;
		}
	}

	@Override
	public boolean canProcess(List<IEvent> se) {
		for (IEvent e : se) {
			for (IState s : _current) {
				if (s.canProcess(e)) {
					return true;
				}
			}
		}
		return false;
	}
}

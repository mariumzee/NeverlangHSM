
package com.dsleng.statemachine.impl;

import java.util.ArrayList;
import java.util.List;

import com.dsleng.statemachine.IEvent;

public class SimulEvents extends ArrayList<Event> {

	List<IEvent> processedEvts;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for simultaneous events
	 */
	public SimulEvents() {
		processedEvts = new ArrayList<IEvent>();
	}

	public List<IEvent> getEvents() {
		List<IEvent> evts = new ArrayList<IEvent>();
		for (Event e : this) {
			evts.add(e);
		}
		return evts;
	}

	public void Update(List<IEvent> evts) {
		for (IEvent u : evts) {
			this.remove(u);
		}
	}

	public void Update(IEvent ev) {
		processedEvts.add(ev);
	}

	public void Commit() {
		for (IEvent e : processedEvts) {
			this.remove(e);
		}
	}
}

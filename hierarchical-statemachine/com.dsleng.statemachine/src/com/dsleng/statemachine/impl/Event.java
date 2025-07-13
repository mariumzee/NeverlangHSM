/**
 * 
 */
package com.dsleng.statemachine.impl;

import com.dsleng.statemachine.IEvent;

/**
 * @
 *
 */
public class Event implements IEvent {
	private String _name;
	private int _Id;

	public Event(int id, String name) {
		_name = name;
		_Id = id;
	}

	public Event(String name) {
		_name = name;
		_Id = 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dsleng.statemachine.IEvent#getName()
	 */
	@Override
	public String getName() {
		return _name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dsleng.statemachine.IEvent#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_name = name;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dsleng.statemachine.IEvent#getId()
	 */
	@Override
	public int getId() {
		return _Id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dsleng.statemachine.IEvent#setId(int)
	 */
	@Override
	public void setId(int id) {
		_Id = id;

	}

}

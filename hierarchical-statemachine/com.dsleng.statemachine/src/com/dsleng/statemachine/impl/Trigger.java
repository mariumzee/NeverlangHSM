/**
 * 
 */
package com.dsleng.statemachine.impl;

/**
 * @
 *
 */
public class Trigger extends Event {

	public Trigger(String name) {
		super(name);
	}

	public Trigger(int Id, String name) {
		super(Id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Trigger) {
			if (this.getName().compareTo(((Trigger) obj).getName()) == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}

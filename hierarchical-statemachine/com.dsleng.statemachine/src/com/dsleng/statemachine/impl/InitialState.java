/**
 * 
 */
package com.dsleng.statemachine.impl;

import com.dsleng.statemachine.StateTypes;

/**
 * @
 *
 */
public class InitialState extends State {

	public InitialState(State tgt) {
		super(StateTypes.Special, "Initial");
		this.createTransition(tgt, new Trigger("-2"), new Guard("-2"), new Effect("-2"));
	}
}

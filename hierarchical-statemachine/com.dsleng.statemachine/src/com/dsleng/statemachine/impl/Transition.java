package com.dsleng.statemachine.impl;

public class Transition {
	private State _src, _tgt;
	private Trigger _trigger;
	private Guard _guard;
	private Effect _effect;
	private boolean _used;

	public Transition(State src, State tgt) {
		_src = src;
		_tgt = tgt;
		_used = false;
	}

	public boolean isUsed() {
		return _used;
	}

	public void setUsed() {
		_used = true;
	}

	public void resetUsed() {
		_used = false;
	}

	public State getSrc() {
		return _src;
	}

	public State getTgt() {
		return _tgt;
	}

	public Trigger getTrigger() {
		return _trigger;
	}

	public Guard getGuard() {
		return _guard;
	}

	public Effect getEffect() {
		return _effect;
	}

	public void setTrigger(Trigger e) {
		_trigger = e;

	}

	public void setGuard(Guard e) {
		_guard = e;

	}

	public void setEffect(Effect e) {
		_effect = e;

	}

}

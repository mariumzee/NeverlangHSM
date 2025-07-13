/**
 * 
 */
package com.dsleng.statemachine;

import java.util.List;

import com.dsleng.statemachine.impl.Transition;



/**
 * @author suresh
 *
 */

public interface IState {
	public StateTypes getId();
	public String getName();
	
	public List<IState> process(List<IEvent> se);
	public List<IState> process(IEvent e);
	
	public boolean canProcess(IEvent e);
	public boolean canProcess(List<IEvent> e);
	
	public List<IEvent> getUsedEvents();
	public void Reset();
	
	public void makeFinal();
	public void makeInitial();
	public boolean isFinal();
	public boolean isInitial();
	public List<Transition> getTransitions();
	
	public void setParent(IState s);
	public IState getParent();
	
	// Used to check if in the current step if a state has been used
	public void setUsed();
	public boolean getUsed();
	public void ResetUsed();
}

/**
 * 
 */
package com.dsleng.statemachine.utils;

import java.util.HashMap;

/**
 * @
 *
 */
public class StateCnt {

	int cnt;
	int evtCnt;
	HashMap<String, String> stateContainer;
	String _currentEvt;

	public StateCnt() {
		cnt = 0;
		evtCnt = 0;
		stateContainer = new HashMap<String, String>();
	}

	public String getValue(String stateClass) {
		String val = "S" + cnt;
		stateContainer.put(stateClass, val);
		cnt++;
		return val;
	}

	public String getVariable(String stateClass) {
		return stateContainer.get(stateClass);
	}

	public String getEventValue() {
		String val = "E" + evtCnt;
		_currentEvt = val;
		evtCnt++;
		return val;
	}

	public String getCurrentEventSet() {
		return _currentEvt;
	}
}

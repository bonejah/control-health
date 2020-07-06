package br.com.controlHealth.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 2221631364176545911L;

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Before phase: " + event.getPhaseId());

	}

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("After phase: " + event.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}

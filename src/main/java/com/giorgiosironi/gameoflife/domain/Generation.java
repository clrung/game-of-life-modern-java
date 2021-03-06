package com.giorgiosironi.gameoflife.domain;

import java.util.HashSet;
import java.util.Set;

public final class Generation implements Plane {

	// TODO: rename to aliveCells?
	private Set<Cell> aliveCells;
	private ClassicRules rules;

	private Generation(Set<Cell> set) {
		this.aliveCells = set;
		this.rules = new ClassicRules();
	}

	public static Generation withAliveCells(Cell... cells) {
		Set<Cell> set = new HashSet<Cell>();
		for (int i = 0; i < cells.length; i++) {
			set.add(cells[i]);
		}
		return new Generation(set);
	}
	
	public State state(int x, int y) {
		return state(Cell.onXAndY(x, y));
	}
	
	private State state(Cell cell) {
		return isAlive(cell) ? State.ALIVE : State.DEAD;
	}

	private boolean isAlive(Cell candidate) {
		return aliveCells.contains(candidate);
	}

	public Generation evolve() {
		Set<Cell> newGeneration = new HashSet<Cell>();
		allCandidateToBeAliveInTheNextGeneration().forEach((Cell candidate) -> {
			int aliveNeighbors = candidate.neighbors().count((Cell c) -> isAlive(c));
			State candidateStateInNewGeneration = rules.nextState(state(candidate), aliveNeighbors);
			if (candidateStateInNewGeneration == State.ALIVE) {
				newGeneration.add(candidate);
			}
		}); 
		return new Generation(newGeneration);
	}

	private Zone allCandidateToBeAliveInTheNextGeneration() {
		Zone toCalculate = aliveCells
			.stream()
			.map((Cell alive) -> alive.block())
			.reduce(
				Zone.empty(), 
				(Zone z1, Zone z2) -> z1.union(z2)
			);
		return toCalculate;
	}

	public int countAlive() {
		return aliveCells.size();
	}

}

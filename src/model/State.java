package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class State implements Comparable<State> {
    private ArrayList<Integer> circuit;
    private int nodeCount;
    private static int[][] costMatrix;

    public State(int nodeCount) {
	this.nodeCount = nodeCount;

	circuit = new ArrayList<Integer>(nodeCount);
	Random rand = new Random();
	for (int i = 0; i < nodeCount;) {
	    int randomNumber = rand.nextInt(nodeCount);
	    if (!circuit.contains(randomNumber)) {
		circuit.add(randomNumber);
		i++;
	    }
	}
    }

    @SuppressWarnings("static-access")
    public State(int nodeCount, int[][] costMatrix) {
	this(nodeCount);
	this.costMatrix = costMatrix;
    }

    public State(State state) {
	this.circuit = new ArrayList<Integer>();
	this.circuit.addAll(state.getCircuit());
	this.nodeCount = state.getNodeCount();
    }

    /**
     * @return the circuit
     */
    public ArrayList<Integer> getCircuit() {
	return circuit;
    }

    /**
     * @param circuit
     *            the circuit to set
     */
    public void setCircuit(ArrayList<Integer> circuit) {
	this.circuit = circuit;
    }

    /**
     * @return the nodeCount
     */
    public int getNodeCount() {
	return nodeCount;
    }

    /**
     * @param nodeCount
     *            the nodeCount to set
     */
    public void setNodeCount(int nodeCount) {
	this.nodeCount = nodeCount;
    }

    /**
     * @return the costMatrix
     */
    public static int[][] getCostMatrix() {
	return costMatrix;
    }

    /**
     * @param costMatrix
     *            the costMatrix to set
     */
    public static void setCostMatrix(int[][] costMatrix) {
	State.costMatrix = costMatrix;
    }

    public State getNextState() {
	State nextState = new State(this);
	Random rand = new Random();
	int i = rand.nextInt(100) % nodeCount;
	int j = i;
	while (i == j) {
	    j = rand.nextInt(100) % nodeCount;
	}

	ArrayList<Integer> nextPerm = nextState.getCircuit();
	int aux = nextPerm.get(i);
	nextPerm.set(i, nextPerm.get(j));
	nextPerm.set(j, aux);

	nextState.setCircuit(nextPerm);
	return nextState;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((circuit == null) ? 0 : circuit.hashCode());
	result = prime * result + nodeCount;
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	State other = (State) obj;
	if (circuit == null) {
	    if (other.circuit != null) return false;
	} else if (!circuit.equals(other.circuit)) return false;
	if (nodeCount != other.nodeCount) return false;
	return true;
    }

    private int eval() {
	int sum = 0;
	int i, j;
	for (i = 0, j = 1; j < nodeCount; i++, j++) {
	    sum += costMatrix[circuit.get(i)][circuit.get(j)];
	}
	sum += costMatrix[circuit.get(j - 1)][circuit.get(0)];
	return sum;
    }

    @Override
    public int compareTo(State state) {
	return eval() - state.eval();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "[" + circuit + ", " + circuit.get(0) + ", " + eval() + "]";
    }

    public TreeSet<State> getNextStates(long tries) {
	TreeSet<State> nextStates = new TreeSet<State>();
	ArrayList<State> list = new ArrayList<State>();
	
	while (list.size() < tries) {
	    State nextState = new State(getNextState());
	    if (!list.contains(nextState)) {
		list.add(nextState);
	    }
	}
	nextStates.addAll(list);
	return nextStates;
    }
}

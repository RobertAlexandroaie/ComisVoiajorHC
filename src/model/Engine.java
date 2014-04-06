package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Engine {

    int[][] costMatrix;
    int nodeCount;

    public Engine(String filename) {
	try {
	    BufferedReader buffReader = new BufferedReader(new FileReader(filename));
	    nodeCount = Integer.parseInt(buffReader.readLine());

	    costMatrix = new int[nodeCount][nodeCount];
	    for (int i = 0; i < nodeCount; i++) {
		for (int j = i + 1; j < nodeCount; j++) {
		    costMatrix[i][j] = costMatrix[j][i] = Integer.parseInt(buffReader.readLine().trim());
		}
	    }

	    buffReader.close();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NumberFormatException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void clean(ArrayList<State> visited, TreeSet<State> list, State currentState) {
	TreeSet<State> backupList = new TreeSet<State>();
	backupList.addAll(list);
	for (State state : backupList) {
	    if (visited.contains(state)) {
		list.remove(state);
	    }
	    if (state.compareTo(currentState) > 0) {
		list.remove(state);
	    }
	}
    }

    private long getFact(int val) {
	long fact = 1;
	while (val > 1) {
	    fact *= val--;
	}
	return fact;
    }

    public long hillClimb(int tries) {
	State currentState = new State(nodeCount, costMatrix);
	State lastState = currentState;

	ArrayList<State> visited = new ArrayList<State>();
	visited.add(currentState);

	TreeSet<State> newNeighbours = new TreeSet<State>();

	long iterations = tries;
	long fact = getFact(nodeCount);
	if (tries > fact) {
	    iterations = fact;
	}

	long startTime = System.currentTimeMillis();
	newNeighbours = currentState.getNextStates(iterations);
	while (!newNeighbours.isEmpty()) {
	    clean(visited, newNeighbours, currentState);
	    if (!newNeighbours.isEmpty()) {
		State poll = newNeighbours.pollFirst();
		currentState = poll;
		newNeighbours.clear();
		newNeighbours.addAll(poll.getNextStates(iterations));
		visited.add(poll);
		lastState = poll;
	    }
	}
	long stopTime = System.currentTimeMillis();

	//System.out.println(lastState);
	//System.out.println("time = " + (stopTime - startTime));
	return (stopTime - startTime);
    }

}

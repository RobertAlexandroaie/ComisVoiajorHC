/**
 * 
 */
package main;

import model.Engine;

/**
 * @author Robert
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Engine engine = new Engine("test.gph");
	double medTime = 0;
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime /= 5;
	System.out.println("Test.gph: " + medTime);

	engine = new Engine("test2.gph");
	medTime = 0;
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime /= 5;
	System.out.println("Test2.gph: " + medTime);

	engine = new Engine("test3.gph");
	medTime = 0;
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime += engine.hillClimb(5);
	medTime /= 5;
	System.out.println("Test3.gph: " + medTime);
    }

}

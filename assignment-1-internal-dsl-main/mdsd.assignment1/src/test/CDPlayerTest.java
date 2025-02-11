package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MachineInterpreter;
import main.StateMachine;
import main.metamodel.Machine;

public class CDPlayerTest {
	
	public MachineInterpreter interpreter;
	
	@BeforeEach
	public void init() {
		StateMachine stateMachine = new StateMachine();
		final int NUMBER_TRACKS= 10;
		Machine m = stateMachine.
					integer("track").
					state("STOP").initial().
						when("PLAY").to("PLAYING").set("track", 1).ifEquals("track", 0).
						when("PLAY").to("PLAYING").
					state("PLAYING").
						when("STOP").to("STOP").
						when("PAUSE").to("PAUSED").
						when("TRACK_END").to("STOP").ifEquals("track", NUMBER_TRACKS).
						when("TRACK_END").to("PLAYING").increment("track").
					state("PAUSED").
						when("STOP").to("STOP").
						when("PLAY").to("PLAYING").
						when("FORWARD").to("PAUSED").increment("track").ifLessThan("track", NUMBER_TRACKS + 1).
						when("BACK").to("PAUSED").decrement("track").ifGreaterThan("track", 1).
					build();
		interpreter = new MachineInterpreter();
		interpreter.run(m);
	}
	
	@Test
	public void playMusic() {
		
		System.out.println("-- Test 1 --");
		interpreter.processEvent("PLAY");
		assertEquals(1, interpreter.getInteger("track"));
		assertEquals("PLAYING", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 2 --");
		interpreter.processEvent("TRACK_END");
		assertEquals(2, interpreter.getInteger("track"));
		assertEquals("PLAYING", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 3 --");
		interpreter.processEvent("STOP");
		assertEquals(2, interpreter.getInteger("track"));
		assertEquals("STOP", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 4 --");
		interpreter.processEvent("PLAY");
		assertEquals(2, interpreter.getInteger("track"));
		assertEquals("PLAYING", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 5 --");
		interpreter.processEvent("PAUSE");
		assertEquals(2, interpreter.getInteger("track"));
		assertEquals("PAUSED", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 6 --");
		interpreter.processEvent("BACK");
		assertEquals(1, interpreter.getInteger("track"));
		assertEquals("PAUSED", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 7 --");
		interpreter.processEvent("FORWARD");
		assertEquals(2, interpreter.getInteger("track"));
		assertEquals("PAUSED", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 8 --");
		interpreter.processEvent("FORWARD");
		interpreter.processEvent("FORWARD");
		interpreter.processEvent("FORWARD");
		interpreter.processEvent("FORWARD");
		interpreter.processEvent("FORWARD");
		interpreter.processEvent("FORWARD");
		interpreter.processEvent("FORWARD");
		interpreter.processEvent("FORWARD");
		assertEquals(10, interpreter.getInteger("track"));
		assertEquals("PAUSED", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 9 --");
		interpreter.processEvent("PLAY");
		assertEquals(10, interpreter.getInteger("track"));
		assertEquals("PLAYING", interpreter.getCurrentState().getName());
		
		System.out.println("-- Test 10 --");
		interpreter.processEvent("TRACK_END");
		assertEquals(10, interpreter.getInteger("track"));
		assertEquals("STOP", interpreter.getCurrentState().getName());
		
		
	}
}

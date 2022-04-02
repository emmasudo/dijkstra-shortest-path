//Emma Sudo

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

class PriorityQueueTest {
	
	ArrayList<Integer> prioritiesIntegerList = new ArrayList<Integer>();
	ArrayList<Integer> prioritiesStringList = new ArrayList<Integer>();
	
	//returns integer priorityqueue with random priorities from -1000 to 1000 inclusive and with data that is equal to the priorities
	private PriorityQueue<Integer> getRandomIntegerQueue() {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		for(int i = 0; i<10000; i++) {
			int p = (int) (Math.random()*(2001))-1000;
			prioritiesIntegerList.add(p);
			queue.push(p, p);
		}
		
		return queue;
	}
	
	//returns integer priorityqueue with known priorities (0-9) and with data corresponding to the order of insertion
	private PriorityQueue<Integer> getKnownIntegerQueue(){
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		for(int i = 0; i<10; i++) {
			queue.push(i, i);
		}
		
		return queue;
	}
	
	//returns string priorityqueue with random priorities from -1000 to 1000 inclusive and with data that is the string form of the priority
	private PriorityQueue<String> getRandomStringQueue() {
		PriorityQueue<String> queue = new PriorityQueue<String>();
		
		for(int i = 0; i<10000; i++) {
			int p = (int) (Math.random()*(2001))-1000;
			prioritiesStringList.add(p);
			queue.push(""+p, p);
		}
		
		return queue;
	}
	
	//returns string priorityqueue with known priorities (0-9) and with data that is the string form of the number corresponding to the order of insertion
	private PriorityQueue<String> getKnownStringQueue(){
		PriorityQueue<String> queue = new PriorityQueue<String>();
		
		for(int i = 0; i<10; i++) {
			queue.push(""+i, i);
		}
		
		return queue;
	}
	
	//returns integer priorityqueue with all cells with the same priority and with data corresponding to order of insertion, but starting at -10000
	private PriorityQueue<Integer> samePriorityQueue(){
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		for(int i = -10000; i<10000; i++) {
			queue.push(i, 0);
		}
		
		return queue;
	}
	
	@Test
	void constructorTest() {
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
		PriorityQueue<String> pq2 = new PriorityQueue<String>();
		PriorityQueue<ArrayList<Integer>> pq3 = new PriorityQueue<ArrayList<Integer>>();
	}
	
	@Test
	void pushTest() {
		//tests negative, zero, and positive integers
		PriorityQueue queue = getRandomIntegerQueue();
		
		getKnownIntegerQueue();
		getRandomStringQueue();
		getKnownStringQueue();
		
		//test pushing null
		queue.push(null, 1);
		
		//test pushing same priority
		queue.push(0, 1);
		queue.push(1, 1);
		queue.push(2, 1);
	}
	
	@Test
	void pollTest() {
		
		//these ignore instances where the priority is the same; that will be tested below.
		PriorityQueue integerQueue = getRandomIntegerQueue();
		Collections.sort(prioritiesIntegerList);
		
		PriorityQueue stringQueue = getRandomStringQueue();
		Collections.sort(prioritiesStringList);
		
		for(int i = 0; i<10000; i++) {
			assertEquals(prioritiesIntegerList.get(i), integerQueue.poll());
		}
		
		for(int i = 0; i<10000; i++) {
			assertEquals(Integer.toString(prioritiesStringList.get(i)).compareTo((String) stringQueue.poll()), 0);
		}
		
		//testing instances where the cells have the same priority
		PriorityQueue sameQueue = samePriorityQueue();
		assertEquals(-10000,sameQueue.poll());
		
		for(int i = 9999; i> -10000; i--) {
			assertEquals(i, sameQueue.poll());
		}
		
	}
	
	@Test
	void peekTest() {
		//testing known queues
		PriorityQueue integerQueue = getKnownIntegerQueue();
		PriorityQueue stringQueue = getKnownStringQueue();
		
		for(int i = 0; i<10; i++) {
			assertEquals(integerQueue.peek(), i);
			integerQueue.poll();
		}
		
		for(int i = 0; i<10; i++) {
			assertEquals(stringQueue.peek(), ""+i);
			stringQueue.poll();
		}
		
		//queues will be empty
		assertEquals(integerQueue.peek(), null);
		assertEquals(stringQueue.peek(), null);
		
		//testing null that is pushed instead of empty queue
		integerQueue.push(null, 0);
		assertEquals(integerQueue.peek(), null);
	}

}

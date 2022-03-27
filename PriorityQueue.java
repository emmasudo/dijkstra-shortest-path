//Emma Sudo

public class PriorityQueue<V>{
	
	private class Cell<V>{
		private V data;
		private int priority;
		
		private Cell(V data, int priority) {
			this.data = data;
			this.priority = priority;
		}
	}
	
	private Cell[] heap;
	private int size;
	
	public PriorityQueue() {
		heap = new Cell[7];
		size = 0;
	}
	
	public boolean push(V data, int priority) {
		
		if(size == heap.length) {
			Cell[] temp = new Cell[(heap.length*2)+1];
			
			for(int i = 0; i < heap.length; i++) {
				temp[i] = heap[i];
			}
			
			heap = temp;
		}
		
		heap[size] = new Cell(data, priority);
		
		int cur = size;
		
		//percolates until there are no children with smaller priorities than that of their parent
		while(heap[cur].priority < heap[getParent(cur)].priority) {
			percolate(cur);
			cur = getParent(cur);
		}
		
		size++;
		
		return true;

	}
	
	public V peek() {
		//if heap is empty
		if(heap[0] == null) {
			return null;
		}
		else {
			return (V) heap[0].data;
		}
	}
	
	public V poll() {
		
		V pollValue = peek();
		
		if(pollValue == null) {
			return null;
		}
		
		size--;
		
		heap[0] = heap[size];
		heap[size] = null;
		
		heapify(0);
		
		return pollValue;
	}
	
	private int getParent(int index) {
		if(index == 0) {
			return 0;
		}
		if(index % 2 == 0) {
			return (index / 2)-1;
		}
		else {
			return (index / 2);
		}
	}
	
	
	private int getLeftChild(int index) {
		return (2*index)+1;
	}
	
	private int getRightChild(int index) {
		return (2*index)+2;
	}
	
	private void heapify(int index) {
		
		//current is leaf/has no children
		if(index >= (size/2) || heap[getLeftChild(index)]==null) {
			return;
		}
		
		//current only has left child
		if(heap[getRightChild(index)]==null) {
			
			if(heap[index].priority > heap[getLeftChild(index)].priority) {
				percolate(getLeftChild(index));
				heapify(getLeftChild(index));
			}
			
		}
		
		//current has both children
		else if(heap[index].priority > heap[getLeftChild(index)].priority || heap[index].priority > heap[getRightChild(index)].priority) {
			
			if(heap[getLeftChild(index)].priority < heap[getRightChild(index)].priority) {
				percolate(getLeftChild(index));
				heapify(getLeftChild(index));
			}
			
			else {
				percolate(getRightChild(index));
				heapify(getRightChild(index));
			}
			
		}
		
	}
	
	private void percolate(int current) {
		
		Cell temp = heap[current];
		heap[current] = heap[getParent(current)];
		heap[getParent(current)] = temp;
		
	}
	
	public int peekPriority() {
		return heap[0].priority;
	}
}

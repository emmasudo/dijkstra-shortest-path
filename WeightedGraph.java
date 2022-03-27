//Emma Sudo

public class WeightedGraph extends Graph{
	
	public WeightedGraph(WeightedEdge[] edges) {
		super(edges);
	}
	
	public int shortestPath(int v, int w) {
		boolean[] isVisited = new boolean[vertMapSize()];
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		queue.push(v, 0);
		
		return (shortestPathHelper(v, w, isVisited, queue));
	}
	
	private int shortestPathHelper(int v, int w, boolean[] isVisited, PriorityQueue<Integer> queue) {
		
		int currentDistance = queue.peekPriority();
		queue.poll();
		
		if(v==w) {
			return currentDistance;
		}
		
		for(Edge e : getAdjacencyList(v)) {
			
			//if e is an edge then it gets assigned weight 1
			WeightedEdge edge;
			if(e instanceof WeightedEdge) {
				edge = (WeightedEdge) e;
			}
			else {
				edge = new WeightedEdge(e.getV(), e.getW(), 1);
			}
			
			if(!isVisited[getVertIndex(edge.getW())]) {
				queue.push(edge.getW(), currentDistance + edge.getWeight());
			}
		}
		
		isVisited[getVertIndex(v)]= true;
				
		if(queue.peek() == null) {
			//no path
			return -1;
		}
	
		return (shortestPathHelper(queue.peek(), w, isVisited, queue));
	}
	
}

//Emma Sudo

import java.util.ArrayList;

public class Graph {
	
	private class Node{
		Edge edge;
		Node next;
		
		private Node(Edge e, Node n) {
			edge = e;
			next = n;
		}
	}
	
	private ArrayList<Integer> vertMap;
	private ArrayList<Node> adjList;
	
	
	public Graph(Edge[] edgeList) {
		vertMap = new ArrayList<Integer>();
		adjList= new ArrayList<Node>();
		
		for(Edge e: edgeList) {
			if(e != null) {
				addEdge(e);
			}
		}
	}
	
	public boolean addEdge(Edge e) {
		if(!containsEdge(e)) {
			addHelper(e);
			if(e.w != e.v) {
				if(e instanceof WeightedEdge){
					addHelper(new WeightedEdge(e.w, e.v, ((WeightedEdge)e).getWeight()));
				}else {
					addHelper(new Edge(e.w, e.v));
				}
					
			}		
			return true;
		}
		return false;	
	}
	
	public boolean addHelper(Edge e) {
		int index = vertMap.indexOf(e.v);
		if(index == -1) {
			vertMap.add(e.v);
			adjList.add(new Node(e, null));
		}else {
			Node n = adjList.get(index);
			adjList.set(index, new Node(e, n));
		}
		return true;
		
	}
	
	public boolean containsEdge(Edge e) {
		int index = vertMap.indexOf(e.v);
		if(index == -1) {
			return false;
		}
		Node n = adjList.get(index);
		while(n!= null) {
			if(n.edge.equals(e)) {
				return true;
			}
			n = n.next;
		}
		return false;
	}
	
	public Edge[] getAdjacencyList(int vert) {
		int index = vertMap.indexOf(vert);
		if(index == -1) {
			return null;
		}
		Node n = adjList.get(index);
		Edge[] returnList = new Edge[degree(vert)];
		int i = 0;
		while(n!=null) {
			returnList[i++] = n.edge;
			n = n.next;
		}
		
		return returnList;
	}
	
	public int degree(int vert) {
		int index = vertMap.indexOf(vert);
		if(index == -1) {
			return 0;
		}
		Node n = adjList.get(index);
		int i = 0;
		while(n != null) {
			i++;
			n = n.next;
		}
		return i;
	}
	
	public int getVertIndex(int vert) {
		return vertMap.indexOf(vert);
	}
	
	public int vertMapSize() {
		return vertMap.size();
	}
	
}

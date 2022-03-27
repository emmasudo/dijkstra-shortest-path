//Emma Sudo

public class Edge {

	public int v;
	public int w;
	
	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	public int getV(){
		return v;
	}
	
	public int getW() {
		return w;
	}
	
	public boolean incidentTo(int i) {
		return (i == v || i == w);
	}
	
	public boolean equals(Edge e) {
		return (e.incidentTo(v) && e.incidentTo(w));
	}
	
	public int[] toArray() {
		return new int[] {v,w};
	}
	
}

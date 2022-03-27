//Emma Sudo

public class WeightedEdge extends Edge{
	private int weight;
	
	public WeightedEdge(int v, int w, int weight) {
		super(v, w);
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}

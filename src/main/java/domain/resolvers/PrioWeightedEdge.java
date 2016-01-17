package domain.resolvers;

import org.jgrapht.graph.DefaultWeightedEdge;

public class PrioWeightedEdge extends DefaultWeightedEdge {

    public PrioWeightedEdge() { super();
    }

    @Override
    public String toString() {
        return super.toString() + " " +  getWeight();
    }
}

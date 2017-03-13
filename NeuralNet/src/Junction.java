import java.util.ArrayList;


public class Junction {
	ArrayList<Synapse> synapses = new ArrayList<Synapse>();
	
	public Junction(Layer l, Layer l2){
		for(Neuron n: l.neurons){
			for(Neuron n2: l2.neurons){
				this.add(new Synapse(n, n2, l.neurons.size()));
			}
		}
	}
	
	public void add(Synapse s){
		synapses.add(s);
	}
	
	public void calcIn(){
		for(Synapse s : synapses){
			s.n2.inValue += (s.n.outValue*s.weight) + s.n2.bias;
		}
	}
	
	public void calcErr(double learnRate){
		for(Synapse s : synapses){
			if(s.n.outValue == .5){
				s.n.error = 0;
			}
			else{
				s.n.error += s.n.outValue*(1-s.n.outValue)*s.n2.error*s.weight;
				s.weight *= (10000000-(learnRate))/10000000;
				s.weight += (s.n.outValue*s.n2.error)*learnRate;
			}
		}
	}
	
}

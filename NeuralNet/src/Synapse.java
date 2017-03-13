
public class Synapse {
	Neuron n;
	Neuron n2;
	double weight = 0;
	double gradient = 0;
	
	public Synapse(Neuron ne, Neuron ne2, int unitCount){
		n = ne;
		n2 = ne2;
		weight = (Math.random()*Math.sqrt(unitCount))+2;
		
		if(Math.random() > .5){
			weight *= -1;
		}
	}
}

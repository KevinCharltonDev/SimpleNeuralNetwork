import java.util.ArrayList;

public class Layer {
	ArrayList<Neuron> neurons = new ArrayList<Neuron>();
	public Layer(int n){
		for(int i = 0; i < n; i++){
			this.add(new Neuron());
		}
	}
	
	public void add(Neuron n){
		neurons.add(n);
	}
	
	
	public void calcOut(){
		for(Neuron n : neurons){
			n.outValue = 1/(1+Math.pow(2.71828, -n.inValue));
			if(Math.abs(n.outValue - .5) < .01){
				n.outValue = .5;
			}
		}
	}
	
	public void calcBias(double learnRate){
		for(Neuron n : neurons){
			n.bias += n.error*learnRate;
		}
	}
	
	public void resetVals(){
		for (Neuron n: neurons) {
			n.error = 0;
			n.inValue = 0;
		}
	}
}

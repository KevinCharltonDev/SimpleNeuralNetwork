
public class Neuron {
		double inValue = 0;
		double outValue = 0;
		double error = 0;
		double bias = .5;
		
		public Neuron(){
			
		}
		
		public Neuron(double v){
			inValue = v;
		}
		
		public void Regularize(){
			error += -inValue;
		}
		
}

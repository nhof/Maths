package statistic;

import java.util.ArrayList;
import java.util.Collections;

public class Density {

	RandVariable values;
	RandVariable probabilities;

	private Density(RandVariable values) {
		Collections.sort(values.getInput());
		this.values = new RandVariable(values.getInput());
		int sum = 0;
		int count = 0;
		double save;
		ArrayList<Double> prob = new ArrayList<Double>();
		for (int i = 0; i < values.size(); i++) {
			save = values.getInput().get(i);
			if (prob.indexOf(save) == -1) {
				count = Collections.frequency(values.getInput(), values.getInput().get(i));
				prob.add((double) count);
				sum += count;
			}

		}
		for (int i = 0; i < prob.size(); i++) {
			prob.set(i, prob.get(i) / sum);
		}
		for(int i = 0; i<values.size();i++){
			if(Collections.frequency(values.getInput(), values.getInput().get(i))>1){
				values.getInput().remove(i);
			}
		}
		this.probabilities = new RandVariable(prob);
	}
	
	public double mean(){
		int s = values.size();
		double sum = 0;
		for(int i = 0; i<values.size(); i++){
			sum +=values.get(i)*probabilities.get(i);
		}
		return sum/s;
	}
	
	public double expectation(){
		int s = values.size();
		double sum = 0;
		for(int i = 0; i<values.size(); i++){
			sum +=Math.pow(values.get(i),2)*probabilities.get(i);
		}
		return Math.sqrt(sum)/s;
	}
	
//	public double covariation(){
//		int s = values.size();
//		
//	}
	

}

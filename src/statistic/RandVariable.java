package statistic;

import java.util.ArrayList;

public class RandVariable {

	private ArrayList<Double> input;
	
	public RandVariable(ArrayList<Double> input){
		this.input = input;
	}
	
	public ArrayList<Double> getInput(){
		return input;
	}
	
	public RandVariable setInput(ArrayList<Double> input){
		return new RandVariable(input);
	}
	
	public int size(){
		return this.getInput().size();
	}
	
	public double get(int i){
		return this.getInput().get(i);
	}
	
	public RandVariable add(double d){
		ArrayList<Double> output = new ArrayList<Double>();
		for(int i =0; i<input.size();i++){
			output.add(input.get(i)+d);
		}
		return new RandVariable(output);
	}
	
}

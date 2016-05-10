package set;

import java.util.List;

public class FiniteSet implements Set{
	
	private List<Number> elements;
	
	public FiniteSet(Number...elements){
		for(Number element : elements){
			this.elements.add(element);
		}
	}
	
	public List<Number> getElements(){
		return elements;
	}
	
	public double getCardinality(){
		return elements.size();
	}

	@Override
	public double getDimension() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}

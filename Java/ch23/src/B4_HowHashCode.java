import java.util.HashSet;

class Car{
	private String model;
	private String color;
	
	public Car(String m, String c) {
		model = m; color = c;
	}

	@Override
	public String toString() {
		return model + " : " + color;
	}
	
	@Override
	public int hashCode() {
		return (model.hashCode() + color.hashCode()) / 2;
	}
	
	@Override
	public boolean equals(Object obj) {
		String m = ((Car)obj).model;
		String c = ((Car)obj).color;
		
		if(model.equals(m) && color.equals(c))
				return true;
		else	return false;
					
	}
}
public class B4_HowHashCode {

	public static void main(String[] args) {

		HashSet<Car> set = new HashSet<>();
		
		set.add(new Car("HY_MD_301", "Red"));
		set.add(new Car("HY_MD_301", "Black"));
		set.add(new Car("HY_MD_302", "Red"));
		set.add(new Car("HY_MD_302", "White"));
		set.add(new Car("HY_MD_301", "Red"));


		System.out.println("인스턴스 수: " + set.size());
		
		for(Car c : set)
			System.out.println(c.toString() + '\t');
	}

}

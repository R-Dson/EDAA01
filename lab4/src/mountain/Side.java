package mountain;

import java.util.HashMap;

public class Side {
	private Point p1;
	private Point p2;
	
	public Side (Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getPointOne() {
		return p1;
	}
	
	public Point getPointTwo() {
		return p2;
	}

	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	
	@Override
	public boolean equals(Object temp) {

		return this.hashCode() == temp.hashCode();
	}

}

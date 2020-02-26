package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class mountain extends Fractal {
	HashMap<Side, Point> map = new HashMap<>();
	private int length;
	Point p11;
	Point p22;
	Point p33;
	
	public mountain(int length) {
		this.length = length;
	}

	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		int alpha = 0;
		turtle.moveTo(turtle.getWidth() / 2.0 - length / 2.0,
				turtle.getHeight() / 2.0 + Math.sqrt(3.0) * length / 4.0);
		p11 = new Point((int)turtle.getX(), (int)turtle.getY());
		p22 = new Point((int)turtle.getX() + 350, (int)turtle.getY() + 50);
		p33 = new Point((int)turtle.getX() + 100, (int)turtle.getY() - 350);
		
		mountainLine(turtle, order, p11, p22, p33);	
	}
	
	private void mountainLine(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3) {
	
		
		if(order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		}else{
			int midYFromP1 = 1/3*( p3.getY()+ p2.getY() - 2*p1.getY()) + p1.getY();
			int random =(int) RandomUtilities.randFunc(midYFromP1/50);

			
			int x1 = (p2.getX()-p1.getX())/2 + p1.getX();
			int y1 = (int) ((p2.getY()-p1.getY())/2 + p1.getY() + random);
			int x2 = (p3.getX()-p2.getX())/2 + p2.getX();
			int y2 = (int) ((p3.getY()-p2.getY())/2 + p2.getY() - random);
			int x3 = (p1.getX()-p3.getX())/2 + p3.getX();
			int y3 = (int) ((p1.getY()-p3.getY())/2 + p3.getY() + random); 
			
			Point temp1 = new Point(x1, y1);
			Point temp2 = new Point(x2, y2);
			Point temp3 = new Point(x3, y3);
			Side s1 = new Side(p1, p2);
			Side s2 = new Side(p2, p3);
			Side s3 = new Side(p3, p1);
			
			if(map.containsKey(s1)) {
				temp1 = map.get(s1);
				map.remove(s1);
			}else {
				map.put(new Side(p1, p2), temp1);
			}
			if(map.containsKey(s2)) {
				temp2 = map.get(s2);
				map.remove(s2);
			}else {
				map.put(new Side(p2, p3), temp2);
			}
			if(map.containsKey(s3)) {
				temp3 = map.get(s3);
				map.remove(s3);
			}else {
				map.put(new Side(p3, p1), temp3);
			}
			
			
			mountainLine(turtle, order-1,p1 ,temp1, temp3);
			mountainLine(turtle, order-1,temp1 ,p2 , temp2);
			mountainLine(turtle, order-1,temp2 , p3, temp3);
			mountainLine(turtle, order-1,temp1 ,temp2, temp3);
		}
		
	}
}

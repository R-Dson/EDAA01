package fractal;

import koch.Koch;
import mountain.mountain;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		fractals[0] = new mountain(300);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}

//Just a class to represent a point so I could make objects out of it

public class Point {
	private double x;
	private double y;
	
	public Point(double d, double e){
		x = d;
		y = e;
	}
	
	public double x(){
		return x;
	}
	public double y(){
		return y;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}

import java.io.*;
import java.util.*;

/*
 * The format for the input file is as so:
 * 
 * First Line: N; the number of points there are.
 * 
 * The next N lines: One point per line, each line containing the x coordinate
 *                   of the point followed by the y coordinate separated by a single space.
 *                   
 * The given example has the points forming a five pointed star, and prints instructions for outlining it.
 * 
 * (Thought this would be the most efficient way to handle testing points)
 * 
 * */

public class PathSim {
	public static void main(String[] args) throws IOException{
		
		// Just some stuff for file reading and writing
		BufferedReader br = new BufferedReader(new FileReader("points.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("path.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ArrayList<Point> pointSet = new ArrayList<Point>(); // List of points
		int n = Integer.parseInt(st.nextToken()); // How many points there are
		
		for(int i = 0; i < n; i++){ // Reads points and loads them into the list
			st = new StringTokenizer(br.readLine());
			pointSet.add(new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
		}
		
		for(int i = n - 1; i > 0; i--) // Makes each point relative to the previous point
			pointSet.set(i, new Point(pointSet.get(i).x() - pointSet.get(i - 1).x(), pointSet.get(i).y() - pointSet.get(i - 1).y()));
		
		for(int i = 0; i < n; i++){ // Prints out instructions for moving to each point in the output file
			double a = (int)(angle(pointSet.get(i)) * 100) / 100.0; // These two lines are just for rounding to the hundredths
			double d = (int)(distance(pointSet.get(i)) * 100) / 100.0; // Make the prints look nicer!
			pw.println("Turn to " + a + " degrees then move forward by " + d + ".");
		}
		br.close();
		pw.close();
	}
	
	public static double angle(Point p){ // Can just directly use point instead of distance since everything's now relative
		return Math.toDegrees(Math.atan2(p.y(), p.x())) - 90; // Subtract 90 to compensate for how the pigeon handles angles
	}
	
	/*
	 * For the pigeon:
	 * Lefts turns are positive and right turns are negative.
	 * */
	
	public static double distance(Point p){
		return Math.sqrt(p.x() * p.x() + p.y() * p.y()); // Standard distance formula
	}
}

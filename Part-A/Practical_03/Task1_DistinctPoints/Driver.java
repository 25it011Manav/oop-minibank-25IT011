import java.util.*;
public class Driver { record Point(int x,int y){} public static void main(String[]a){ List<Point> points=List.of(new Point(1,2),new Point(3,4),new Point(1,2),new Point(5,6),new Point(3,4)); System.out.println("Total Points: "+points.size()); System.out.println("Distinct: "+new HashSet<>(points).size()); } }

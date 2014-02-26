package com.companyname.col;

import java.util.HashMap;
import java.util.Map;

class Point
{
	private int x, y;
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	int getX()
	{
		return x;
	}
	int getY()
	{
		return y;
	}
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Point))
			return false;
		Point p = (Point) o;
		return p.x == x && p.y == y;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * The following algorithm, which assumes the existence of an arbitrary class that is referred to as X,
	 * closely follows Bloch’s algorithm, but is not identical:
	 * 		1. Initialize int variable hashCode (the name is arbitrary) to an arbitrary nonzero
	 * 			integer value, such as 19. This variable is initialized to a nonzero value to
	 * 			ensure that it takes into account any initial fields whose hash codes are zeros.
	 * 			If you initialize hashCode to 0, the final hash code will be unaffected by such
	 * 			fields and you run the risk of increased collisions.
	 * 		2. For each field f that is also used in X’s equals() method, calculate f’s hash
	 * 			code and assign it to int variable hc as follows:
	 * 			a. If f is of Boolean type, calculate hc = f?1:0.
	 * 			b. If f is of byte integer, character, integer, or short integer type, calculate hc =
	 * 				(int) f. The integer value is the hash code.
	 * 			c. If f is of long integer type, calculate hc = (int) (f^(f>>>32)). This expression
	 * 				exclusive ORs the long integer’s least significant 32 bits with its most significant 32 bits.
	 * 			d. If f is of type floating-point, calculate hc = Float.floatToIntBits(f). This
	 * 				method takes +infinity, -infinity, and NaN into account.
	 * 			e. If f is of type double precision floating-point, 
	 * 				calculate long l = Double.doubleToLongBits(f); hc = (int) (l^(l>>>32)).
	 * 			f. If f is a reference field with a null reference, calculate hc = 0.
	 * 			g. If f is a reference field with a nonnull reference, and if X’s equals() method
	 * 				compares the field by recursively calling equals() (as in Listing 5-12’s Employee
	 * 				class), calculate hc = f.hashCode(). However, if equals() employs a more
	 * 				complex comparison, create a canonical (simplest possible) representation of
	 * 				the field and call hashCode() on this representation.
	 * 			h. If f is an array, treat each element as a separate field by applying this algorithm
	 * 				recursively and combining the hc values as shown in the next step.
	 * 		3. Combine hc with hashCode as follows: hashCode = hashCode*31+hc. Multiplying
	 * 			hashCode by 31 makes the resulting hash value dependent on the order in
	 * 			which fields appear in the class, which improves the hash value when a class
	 * 			contains multiple fields that are similar (several ints, for example). I chose 31
	 * 			to be consistent with the String class’s hashCode() method.
	 * 		4. Return hashCode from hashCode().
	 */
	@Override
	public int hashCode()
	{
		/*
		 * This method uses
		 * the aforementioned algorithm to ensure that logically equivalent Point objects hash to the same entry.
		 */
		int hashCode = 19;
		int hc = x;
		hashCode = hashCode*31+hc;
		hc = y;
		hashCode = hashCode*31+hc;
		return hc;
	}
	public static void main(String[] args)
	{
		Point p1 = new Point(10, 20);
		Point p2 = new Point(20, 30);
		Point p3 = new Point(10, 20);
		// Test reflexivity
		System.out.println(p1.equals(p1)); // Output: true
		// Test symmetry
		System.out.println(p1.equals(p2)); // Output: false
		System.out.println(p2.equals(p1)); // Output: false
		// Test transitivity
		System.out.println(p2.equals(p3)); // Output: false
		System.out.println(p1.equals(p3)); // Output: true
		// Test nullability
		System.out.println(p1.equals(null)); // Output: false
		// Extra test to further prove the instanceof operator's usefulness.
		System.out.println(p1.equals("abc")); // Output: false
		Map<Point, String> map = new HashMap<Point, String>();
		map.put(p1, "first point");
		System.out.println(map.get(p1)); // Output: first point
		System.out.println(map.get(new Point(10, 20))); // Output: first point
	}
}
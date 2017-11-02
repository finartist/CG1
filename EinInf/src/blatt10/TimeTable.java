package blatt10;

import java.util.Arrays;

public class TimeTable {

	public static void main(String[] args) {

		double[][] table = new double[9][7];
		double k;
		System.out.println("  k   A1   A2   A3   A4   A5   A6");
		for (int i = 0; i < table.length; i++) {
			k = i + 1;
			table[i][0] = k;
			table[i][1] = k;
			table[i][2] = k;
			table[i][3] = Math.pow(2, k) * 2;
			table[i][4] = Math.pow(2, 2 * k);
			table[i][5] = Math.pow(2, 3 * k);
			table[i][6] = Math.pow(2, Math.pow(2, k));

			System.out.println(Arrays.toString(table[i]));
		}

	}
}

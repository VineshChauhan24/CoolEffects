package Montecarlo;

import java.util.Random;

public class Montecarlo {
	public static void main(String[] args) {
		Random rnd = new Random();
		float sample_x, sample_y;
		float radius;
		int sum_circle = 1; // To avoid issues dividing by 0
		int sum_square = 1; // To avoid issues dividing by 0
		float pi;
		
		for (int i = 0; i < 2000000; i++) {
			sample_x = 2f * (rnd.nextFloat() - 0.5f); // -1 <= sample_x <= 1
			sample_y = 2f * (rnd.nextFloat() - 0.5f); // -1 <= sample_y <= 1
			radius = (float) Math.sqrt(sample_x * sample_x + sample_y * sample_y);
			if (radius <= 1) {
				sum_circle++;
				sum_square++;
			}
			else {
				sum_square++;
			}
			pi = 4f * sum_circle / sum_square;
			System.out.println(pi);
		}
	}
}

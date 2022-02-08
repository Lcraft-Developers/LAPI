package de.lcraft.api.java_utils.probability;

public class ProbabilityCalculator {

	public static void main(String[] args) {
		Proabilities<Integer> proabilities = new Proabilities<>();
		proabilities.addEntry(5, 20);
		proabilities.addEntry(30, 60);
		proabilities.addEntry(10, 20);
		proabilities.addEntry(20, 50);
		proabilities.addEntry(50, 120);

		int sum = 0;
		for(int i = 0; i < 5; i++) {
			int a = proabilities.getRandom();
			System.out.println(a);
			sum = sum + a;
		}
		System.out.println(sum + " / " + 270);
	}

}

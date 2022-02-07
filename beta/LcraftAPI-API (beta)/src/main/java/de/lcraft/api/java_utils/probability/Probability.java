package de.lcraft.api.java_utils.probability;

public class Probability {

	private long percent;

	/**
	 * @param percentAsComma
	 * For Prozent for comma.
	 * Like 0,2 or 1,5
	 * 0,9 = 90%
	 * 1,5 = 150%
	 */
	public Probability(long percentAsComma) {
		this.percent = percentAsComma * 100;
	}

	public long getPercent() {
		return percent;
	}
	public long getPercentAsComma() {
		return percent / 100;
	}

}

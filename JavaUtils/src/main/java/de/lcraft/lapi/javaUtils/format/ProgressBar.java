package de.lcraft.lapi.javaUtils.format;

import java.text.DecimalFormat;

class ProgressBar {

	private Integer minVal;
	private Integer val;
	private Integer maxVal;

	public ProgressBar(Integer minVal, Integer val, Integer maxVal) {
		init(minVal, val, maxVal);
	}
	public ProgressBar(Integer minVal, Integer maxVal) {
		init(minVal, null, maxVal);
	}
	public ProgressBar(Integer val) {
		init(null, val, null);
	}
	private void init(Integer minVal, Integer val, Integer maxVal) {
		if(minVal != null) setMinVal(minVal);
		else setMinVal(0);

		if(val != null) setVal(val);
		else setVal(0);

		if(maxVal != null) setMaxVal(maxVal);
		else setMaxVal(100);
	}

	public double getProcent() {
		int value = getVal() - getMinVal();
		int maxValue = getMaxVal() - getMinVal();
		double pro = (double) value / maxValue;

		return pro * 100;
	}
	public String getProcentStr() {
		String str = new DecimalFormat("###.##").format(getProcent());

		while(str.length() < 5) str = " " + str;

		return str + "%";
	}
	public String getProcentBar(String loaded, String unloaded, int length) {
		String bar = "";
		double divLength = (double) 100 / length;
		int maxPro = (int) (100 / divLength);
		int pro = (int) (getProcent() / divLength);

		for(int i = 0; i <= maxPro; i++) {
			if(i <= pro) bar = bar + loaded;
			else bar = bar + unloaded;
		}

		return bar;
	}
	public String getProcentStatusStr(String loaded, String unloaded, int length) {
		return "\r " + getProcentStr() + " | " + getProcentBar(loaded, unloaded, length) + " | ";
	}

	public Integer getMinVal() {
		return minVal;
	}
	public Integer getVal() {
		return val;
	}
	public Integer getMaxVal() {
		return maxVal;
	}

	private void setMinVal(Integer minVal) {
		this.minVal = minVal;
	}
	public void setVal(Integer val) {
		this.val = val;
	}
	private void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

}
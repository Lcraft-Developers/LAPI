package de.lcraft.lapi.javaUtils.format;

public class Format {

	private int formatX;
	private int formatY;
	private int width;
	private int height;

	public Format(Integer formatX, Integer formatY, Integer width, Integer height) {
		init(formatX, formatY, width, height);
	}
	public Format(Integer width, Integer height) {
		init(null, null, width, height);
	}
	private void init(Integer formatX, Integer formatY, Integer width, Integer height) {
		if(formatX != null && formatY != null && width != null && height != null && FormatUtils.isFormatLegal(new int[]{formatX, formatY}, width, height)) {
			setFormatX(formatX);
			setFormatY(formatY);
		} else {
			setFormatX(FormatUtils.getFormatInt(width, height)[0]);
			setFormatY(FormatUtils.getFormatInt(width, height)[1]);
		}

		if(width != null) setWidth(width);
		else setWidth(0);

		if(height != null) setHeight(height);
		else setHeight(0);
	}

	public int getFormatX() {
		return formatX;
	}
	public int getFormatY() {
		return formatY;
	}
	public int[] getFormat() {
		return new int[]{getFormatX(), getFormatY()};
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	private void setFormatX(int formatX) {
		this.formatX = formatX;
	}
	private void setFormatY(int formatY) {
		this.formatY = formatY;
	}
	private void setWidth(int width) {
		this.width = width;
	}
	private void setHeight(int height) {
		this.height = height;
	}

}
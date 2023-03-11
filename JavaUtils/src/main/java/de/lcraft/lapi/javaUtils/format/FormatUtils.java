package de.lcraft.lapi.javaUtils.format;

import de.lcraft.lapi.javaUtils.NumberUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormatUtils {

	public static List<Dimension> getAllDimensionsFromFormat(int[] format, int minDWidth, int maxDWidth, int minDHeight, int maxDHeight) {
		return getAllDimensionsFromFormat(format, new Dimension(minDWidth, minDHeight), new Dimension(maxDWidth, maxDHeight), false);
	}
	public static List<Dimension> getAllDimensionsFromFormat(int[] format, int minDWidth, int maxDWidth, int minDHeight, int maxDHeight, boolean log) {
		return getAllDimensionsFromFormat(format, new Dimension(minDWidth, minDHeight), new Dimension(maxDWidth, maxDHeight), log);
	}
	public static List<Dimension> getAllDimensionsFromFormat(int[] format, Dimension minD, Dimension maxD) {
		return getAllDimensionsFromFormat(format, minD, maxD, false);
	}
	public static List<Dimension> getAllDimensionsFromFormat(int[] format, Dimension minD, Dimension maxD, boolean log) {
		long currentTime = System.currentTimeMillis();

		List<Dimension> dimensions = new ArrayList<>();
		long amount = (long) (maxD.width - minD.width) * (long) (maxD.height - minD.height);

		ProgressBar progressBar = new ProgressBar(minD.width, maxD.height);
		if(log) System.out.print("\n Calculation Format: " + format[0] + ":" + format[1] + " \n with Amount: " + new DecimalFormat("###,###").format(amount) + " \n");

		for(int width = minD.width; width <= maxD.width; width++) {
			progressBar.setVal(width);
			for (int height = minD.height; height <= maxD.height; height++) {
				if (isFormatLegal(format, width, height)) {
					dimensions.add(new Dimension(width, height));
				}
			}
			if(log) System.out.print(progressBar.getProcentStatusStr("X", "-", 285));
		}
		long timeDuration = System.currentTimeMillis() - currentTime;

		if(log) {
			System.out.print(System.lineSeparator());
			System.out.println("Time(ms): " + new DecimalFormat("###,###").format(timeDuration) + "ms");
			System.out.println("Time: " + DurationFormatUtils.formatDuration(timeDuration, "**H:mm:ss**", true));
			System.out.print(System.lineSeparator());
		}

		return dimensions;
	}

	public static int[] getFormatInt(double width, double height) {
		int[] format = null;
		int scale = (int) (width + height);

		while (format == null) {
			scale--;
			double widthCalc = width / scale;
			double heightCalc = height / scale;

			if(NumberUtils.isInteger(widthCalc) && NumberUtils.isInteger(heightCalc)) format = new int[] { (int) widthCalc, (int) heightCalc };
		}

		return format;
	}
	public static boolean isFormatLegal(int[] format, double width, double height) {
		return (width / format[0]) == (height / format[1]) && (width > 0 || height > 0);
	}
	public static boolean isFormatLegalSlow(int[] format, double width, double height) {
		return Arrays.toString(getFormatInt(width, height)).equals(Arrays.toString(format));
	}

}


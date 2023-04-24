package de.lcraft.lapi.javaUtils;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

public class XPSCounterTest {

	@Test
	public void test() {
		int repeats = 5;

		int[] framerates = {
				30, 60, 90,
				144, 300
		};
		long f = 0L;
		int maxScore = 0;

		for(int fps : framerates) {
			maxScore = maxScore + fps * repeats;
		}

		for(int msDuration = 1000; msDuration <= (1000 * repeats); msDuration = msDuration + 1000) {
			for(int fps : framerates) {
				try {
					f = f + makeTest(fps, msDuration);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}

		System.out.println(System.lineSeparator() + "Score: " + f);
		System.out.println("Max Score: " + maxScore);
	}

	public static void main(String[] args) throws InterruptedException {
		makeTest(144, 5000);
	}

	public static Long makeTest(int fps, int msDuration) throws InterruptedException {
		XPSCounter xpsCounter = new XPSCounter(TimeUnit.MILLISECONDS.toNanos(msDuration));
		int msLength = 1000 / fps;

		System.out.println(System.lineSeparator() + "Testing...");
		System.out.println(System.lineSeparator() + "Time Duration: " + msDuration + "ms");
		System.out.println("Expected result: " + fps + "FPS");

		for(int i = 0; i < msDuration; i = i + msLength) {
			Thread.sleep(msLength);
			xpsCounter.nextFrame();
		}
		Long avgFPS = xpsCounter.getAverageFPS();

		System.out.println("Result: " + avgFPS + "FPS");
		return avgFPS;
	}

}

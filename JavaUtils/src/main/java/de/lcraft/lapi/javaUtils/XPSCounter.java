package de.lcraft.lapi.javaUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class XPSCounter {

	private Long lastFrameTime;
	private List<Long> frameTimes;
	private Long maxNanoSeconds;

	public XPSCounter(Long maxNanoSeconds) {
		init(maxNanoSeconds);
	}
	public XPSCounter() {
		init(null);
	}

	private void init(Long maxNanoSeconds) {
		setFrameTimes(new ArrayList<>());
		setLastFrameTime(0L);

		if(maxNanoSeconds != null) setMaxNanoSeconds(maxNanoSeconds);
		else setMaxNanoSeconds(TimeUnit.MILLISECONDS.toNanos(1000));
	}

	public void nextFrame() {
		addFrameTime(System.nanoTime());
	}
	public Long getAverageFrameTime_ns() {
		long averageFrameTime = 0L;

		for(Long frameTime : getFrameTimes()) {
			averageFrameTime = averageFrameTime + (frameTime / getFrameTimes().size());
		}

		return averageFrameTime;
	}
	public Long getAverageFrameTime_ms() {
		return TimeUnit.NANOSECONDS.toMillis(getAverageFrameTime_ns());
	}
	public Long getAverageFrameTime_sec() {
		return TimeUnit.NANOSECONDS.toSeconds(getAverageFrameTime_ns());
	}
	public Long getAverageFPS() {
		long fps = 1000 / getAverageFrameTime_ms();
		if(fps < 0) fps = 0L;

		return fps;
	}

	private void addFrameTime(Long frameSystemNanoTime) {
		refreshFrameTimes();
		long frameTime = frameSystemNanoTime - getLastFrameTime();

		getFrameTimes().add(frameTime);
		setLastFrameTime(frameSystemNanoTime);
	}
	private void refreshFrameTimes() {
		List<Long> newFrameTimes = new ArrayList<>();

		for(Long frameTime_ns : getFrameTimes()) {
			if(frameTime_ns <= getMaxNanoSeconds()) {
				newFrameTimes.add(frameTime_ns);
			}
		}

		setFrameTimes(newFrameTimes);
	}

	public Long getLastFrameTime() {
		return lastFrameTime;
	}
	public List<Long> getFrameTimes() {
		return frameTimes;
	}
	public Long getMaxNanoSeconds() {
		return maxNanoSeconds;
	}

	private void setLastFrameTime(Long lastFrameTime) {
		this.lastFrameTime = lastFrameTime;
	}
	private void setFrameTimes(List<Long> frameTimes) {
		this.frameTimes = frameTimes;
	}
	private void setMaxNanoSeconds(Long maxNanoSeconds) {
		this.maxNanoSeconds = maxNanoSeconds;
	}

}

package de.lcraft.api.java_utils.probability;

import de.lcraft.api.java_utils.CodeHelper;
import org.checkerframework.checker.units.qual.K;

import java.util.*;

public class ProbabilityCalculator {

	/*public int getRandomInteger(Map<Integer, Probability> allNumbers) {
		allNumbers = maxIntegerOutIt(allNumbers);
		HashMap<Probability, Set<Integer>> allProbabilities = new HashMap<>();
		for(Integer current : allNumbers.keySet()) {
			Probability probability = allNumbers.get(current);
			Set<Integer> currentSet = new HashSet<>();
			if(allProbabilities.containsKey(probability)) currentSet = allProbabilities.get(probability);
			currentSet.add(current);
			allProbabilities.put(probability, currentSet);
		}

		int random = new CodeHelper().getRandom(0, (allProbabilities.size() - 1));

	}

	public Map<Integer, Probability> maxIntegerOutIt(Map<Integer, Probability> allObjects) {
		long nowMaxedOut = 0;
		for(Probability c : allObjects.values()) {
			if(c.getPercent() > nowMaxedOut) nowMaxedOut = c.getPercent();
		}
		Map<Integer, Probability> newValues = new HashMap<>();
		for(int obj : allObjects.keySet()) {
			Probability probability = allObjects.get(obj);
			newValues.put(obj, new Probability((probability.getPercent() / nowMaxedOut)));
		}
		return newValues;
	}*/

}

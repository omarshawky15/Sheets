package eg.edu.alexu.csd.datastructure.iceHockey;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author omara
 *
 */
public class Cs implements IPlayersFinder {
	// function to - i named it because its like arrow keys ->_<- - go in all
	// directions of "int" team as long as it finds one
	public final int[] directions(final String[] photo, final int row, final int column, final int target,
			int counter) {
		final int[] arr1 = { counter, row, column, row, column };
		int[] arrTemp = { -1, 0, 0 };
		if (row >= photo.length || column >= photo[0].length() || row < 0 || column < 0
				|| photo[row].charAt(column) - 48 != target) {
			return arrTemp;
		}
		arrTemp[0] = counter;
		// marking/replacing the visited number with x
		photo[row] = photo[row].substring(0, column) + 'x' + photo[row].substring(column + 1);
		// down-arrow key
		arrTemp = directions(photo, row + 1, column, target, counter + 1);
		if (arrTemp[0] != -1) {
			minMaxValue(arr1, arrTemp);
			counter = arr1[0];
		}
		// up-arrow key
		arrTemp = directions(photo, row - 1, column, target, counter + 1);
		if (arrTemp[0] != -1) {
			minMaxValue(arr1, arrTemp);
			counter = arr1[0];
		}
		// right-arrow key
		arrTemp = directions(photo, row, column + 1, target, counter + 1);
		if (arrTemp[0] != -1) {
			minMaxValue(arr1, arrTemp);
			counter = arr1[0];
		}
		// left-arrow key
		arrTemp = directions(photo, row, column - 1, target, counter + 1);
		if (arrTemp[0] != -1) {
			minMaxValue(arr1, arrTemp);
			counter = arr1[0];
		}
		return arr1;
	}

	@Override
	public final java.awt.Point[] findPlayers(String[] photo, final int team, final int threshold) {
		String[] photoTemp = new String[photo.length];
		photoTemp = photo;
		final ArrayList<ArrayList<Integer>> pointsArr = new ArrayList<ArrayList<Integer>>();
		int counter = 0;
		if (photo[0] == null) {
			return new Point[0];
		}

		for (int i = 0; i < photo.length; i++) {
			for (int j = 0; j < photo[i].length(); j++) {
				int[] arr1 = { 0, 0, 0, 0, 0 };
				if (photo[i].charAt(j) - 48 == team) {
					arr1 = directions(photo, i, j, team, 1);
				}
				if (arr1[0] * 4 >= threshold) {
					pointsArr.add(new ArrayList<Integer>());
					pointsArr.get(counter).add(arr1[2] + arr1[4] + 1);
					pointsArr.get(counter).add(arr1[1] + arr1[3] + 1);
					counter++;
				}
			}
		}
		sort(pointsArr);
		// any name :\
		final Point[] omar = new Point[pointsArr.size()];
		for (int i = 0; i < pointsArr.size(); i++) {
			omar[i] = new Point();
			omar[i].setLocation(pointsArr.get(i).get(0), pointsArr.get(i).get(1));
		}
		// returning photo to its original because it's now all full of "x"'s :p
		photo = photoTemp;
		return omar;
	}

	// Just to minimize the directions method
	void minMaxValue(final int[] arr1, final int[] arrTemp) {
		if (arrTemp[0] != -1) {
			arr1[0] = Math.max(arr1[0], arrTemp[0]);
			arr1[1] = Math.max(arr1[1], arrTemp[1]);
			arr1[2] = Math.max(arr1[2], arrTemp[2]);
			arr1[3] = Math.min(arr1[3], arrTemp[3]);
			arr1[4] = Math.min(arr1[4], arrTemp[4]);

		}
	}

	/*
	 * insertion sort on ListedArrays(took only the algorithm , hand edited to
	 * handle ListArrays)
	 */
	void sort(final ArrayList<ArrayList<Integer>> arr) {
		final int n = arr.size();
		for (int i = 1; i < n; ++i) {
			final ArrayList<Integer> key = arr.get(i);
			int j = i - 1;
			while (j >= 0 && arr.get(j).get(0) >= key.get(0)) {
				if (arr.get(j).get(0) == key.get(0) && arr.get(j).get(1) < key.get(1)) {
					break;
				}
				arr.set(j + 1, arr.get(j));
				j = j - 1;
			}
			arr.set(j + 1, key);
		}
	}
}

interface IPlayersFinder {
	java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}

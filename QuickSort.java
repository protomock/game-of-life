public class QuickSort {
	private int[] numbers;
	private int number;

	public void sort(int[] values) {
		if (values == null || values.length == 0) {
			return;
		}
		this.numbers = values;
		number = values.length;
		quicksort(0, number - 1);
	}

	private void quicksort(int low, int high) {
		int i = low, j = high;
		int pivot = numbers[low + (high - low) / 2];

		while (i <= j) {
			while (numbers[i] < pivot) {
				i++;
			}
			while (numbers[j] > pivot) {
				j--;
			}
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}

	private void exchange(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();

		int N = 1000;

	    long start = System.currentTimeMillis();
	    int[] a = new int[N];
	    for (int i = 0; i < N; i++)
	      a[i] = (int) Math.random();
	    long stop = System.currentTimeMillis();
	    double elapsed = (stop - start) / 1000.0;
	    System.out.println("Generating input:  " + elapsed + " seconds");

		start = System.currentTimeMillis();
		qs.sort(a);
		stop = System.currentTimeMillis();

		elapsed = ((stop - start) / 1000.0);
		System.out.println("Quicksort:   " + elapsed + " seconds");

	}
}

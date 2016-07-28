public class ParallelQuick extends Thread {
	private int[] numbers;
	private int number;
	private int HIGH, LOW, I, J;
	private static int Processor_Name = 0;
	
	public ParallelQuick(){
	}
	
	public ParallelQuick(int w, int x, int y, int z, int[] array){
		super(""+Processor_Name++);
		this.HIGH = w;
		this.LOW = x;
		this.I = y;
		this.J = z;
		this.numbers = array;
		
	}
	
	public void run(){
		//System.out.println(Thread.activeCount());
		if(LOW < J){
			try {
				quicksort(LOW, J);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(I < HIGH){
			try {
				quicksort(I, HIGH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sort(int[] values) throws InterruptedException {
		if (values == null || values.length == 0) {
			return;
		}
		this.numbers = values;
		number = values.length;
		quicksort(0, number - 1);
	}

	private void quicksort(int low, int high) throws InterruptedException {
		int i = low, j = high;
		int pivot = numbers[low + (high - low) / 2];
		/*System.out.println("High=" +high+"\n" +
							"Low = " + low+"\n" +
							"i = " + i + "\n" + 
							"j = " + j +"\n" +
							"Pivot= " + pivot);*/

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
		/*if (low < j)
			
		//System.out.println("low less the J");
		if (i < high)
			
		//System.out.println("I less than high");
		 **/
		ParallelQuick pq = new ParallelQuick(high, low, i, j, numbers);
		pq.start();
		pq.join();
	}

	private void exchange(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

	public static void main(String[] args) throws InterruptedException {
		ParallelQuick qs = new ParallelQuick();

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
		
		
		/*for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+ ",");
		}*/

	}
}

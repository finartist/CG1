package Sortieralgorithmen;
import java.util.Random;

/** Interface for testing and visualizing sorting algorithms.

    The prettyPrint option Uses ANSI codes for output, will not work
    properly on terminals without ANSI support. 
    (<a href="http://adoxa.3eeweb.com/ansicon/"> here</a> is a tool
    for providing ANSI escape sequence recognition for the Windows 
    console.)
*/
public abstract class Sort {
  
    /** p() prints (partial) result
     */
    public boolean print = false;
    /** p() pretty prints ASCII diagrams
     */
    public boolean prettyPrint = false;
    /** p() causes delay in milliseconds and clear screen.
	(no effect if delay==0 or !prettyPrint)
     */
    public int delay = 0;
      
    private void sleep() {
	try {
	    Thread.currentThread().sleep(delay); 
	} catch (InterruptedException e) {
	}
    }

    /** Print array a to System.out; used by sorting algorithms;
	repects attributes print, prettyPrint, and delay.
     */
    public void p(int[] a) {
	if (print) {
	    if (prettyPrint) {
		if (delay>0) {
		    sleep(); 
		    System.out.printf("\033[2J\033[1;1f"); // clear terminal
		}
		for (int i=0;i<a.length;++i) {
		    System.out.printf("\033[1m%2d:\033[0m\033[34m |",i);
		    for (int j=0;j<a[i];++j)
			System.out.print("*");
		    System.out.printf("\033[0m[%d]\n",a[i]);
		}
		System.out.print("\033[1m====|\n\033[0m");
	    }
	    else {
		System.out.print("{");
		if (a.length>0) {
		    for (int i=0;i<a.length-1;++i)
			System.out.printf("%d, ",a[i]);
		    System.out.printf("%d",a[a.length-1]);
	    }
		System.out.println("}");
	    }
	}
    }

    /** Create a random permutation of intergers 1,...,length.
     */
    public static int[] randomPermutation(int length) {
	int[] a=new int[length];
	for (int i=0;i<length;++i)
	    a[i]=i+1;

	Random ran=new Random();
	for (int i=0;i<length-1;++i) {
	    int j=ran.nextInt(length);
	    int t=a[i]; a[i]=a[j]; a[j]=t;
	}
	return a;
    }
  
    /** Check if a is sorted.
	@param array
	@return true if a is soreted, else false.
     */
    public static boolean isSorted(int[] a) {
	for (int i=1;i<a.length;++i)
	    if (a[i-i]>a[i])
		return false;
	return true;
    }
         
    /** Sort array a. 
	This method is implemented by subclasses.
     */
    abstract public void sort(int[] a);  


    /** Create a Sort object from String description.
	@param which class name, e.g., "SelectionSort"
	@return Sort instance
	@throws RuntimeException if which is invalid 
     */
    public static Sort createSort(String which) {
	if (which.equals("SelectionSort"))
	    return new SelectionSort();
	if (which.equals("InsertionSort"))
	    return new InsertionSort();
	if (which.equals("BubbleSort"))
	    return new BubbleSort();
	if (which.equals("Quicksort"))
	    return new Quicksort();
	if (which.equals("Mergesort"))
	    return new Mergesort();
	else
	    throw new RuntimeException("invalid algorithm '"+which+"'");
    }

    /** Simple test program.
	Usage:  java -ea Sort n [which]<p>
	where n is the lentth of the array (argument to randomPermutation()), 
	and which is argument to createSort().
     */
    public static void main(String[] args) {
	int[] a=randomPermutation(Integer.parseInt(args[0]));

	Sort s=createSort(args.length>1 ? args[1] : "SelectionSort");
	s.print=true;
	s.prettyPrint=true;
	s.delay=200;
	s.sort(a);	
    }
}
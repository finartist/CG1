package Sortieralgorithmen;
import java.util.Random;

public class SelectionSort extends Sort {
  
    public void sort(int[] a) {
	p(a);

	int n=a.length;
	for (int i=0;i<n-1;++i) {                // all entries
	    int imin=i;
	    for (int j=i+1;j<n;++j)              // find minimum (index)
		if (a[j]<a[imin])                    
		    imin=j;                          
	    int t=a[imin]; a[imin]=a[i]; a[i]=t; // swap

	    p(a);
	}	
	assert isSorted(a);
	p(a);
    }
}
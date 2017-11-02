package Sortieralgorithmen;
import java.util.Random;

public class Quicksort extends Sort {
  
    public void sort(int[] a) {
	p(a);

	_quicksort(a,0,a.length-1);    // $[a_0,\dots,a_n-1]$

	assert isSorted(a);
	p(a);
    }

   
    void _quicksort(int[] a,int l,int r) {
	if (r>l) {                     // $[a_l,\dots,a_r]$
	    int m=partition(a,l,r);      // pivot $p=a_m$	    	   

	    p(a);
	    
	    _quicksort(a,l,m-1);
	    _quicksort(a,m+1,r);
	}
    }
    
    int partition(int[] a,int l,int r) {
	assert (l<=r);
	System.out.println(l+" "+r);

	int p=a[r], t;                       // pivot a[r]
	int i=l-1, j=r;
	do {	    
	    do ++i; while (a[i]<p);         // find
	    do --j; while (j>0 && a[j]>p);    
	    t=a[i]; a[i]=a[j]; a[j]=t;       // swap
	} while (i<j);
	
	a[j]=a[i]; a[i]=a[r]; a[r]=t;
	
	return i;             // new index of pivot
    }

    public static void main(String[] args) {
	int[] a={9,3,2,4,2,7,1,2};

	Sort s=new Quicksort();

	s.sort(a);

	for (int i=0;i<a.length;++i)
	    System.out.println(a[i]);
    }
}
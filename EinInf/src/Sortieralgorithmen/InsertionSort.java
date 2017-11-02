package Sortieralgorithmen;
import java.util.Random;

public class InsertionSort extends Sort {
  
    public void sort(int[] a) {
	p(a);

	int n=a.length;
	for (int i=1;i<n;++i) {

	    int x=a[i];      // insert $x$ into $(a_0,\dots,a_{i-1})$
	    int j;
	    for (j=i;j>0 && a[j-1]>x;--j)
		a[j]=a[j-1];   
	    
	    a[j]=x;          
	    
	    p(a);
	}

	assert isSorted(a);
	p(a);
    }   
}
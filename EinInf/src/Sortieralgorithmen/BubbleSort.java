package Sortieralgorithmen;
import java.util.Random;

public class BubbleSort extends Sort {
  
    public void sort(int[] a) {
	p(a);

	int n=a.length, swaps=1;	
	for (int i=n-1;i>=0 && swaps>0;--i) {
	    swaps=0;
	    for (int j=1;j<=i;++j) 
		if (a[j-1]>a[j]) {
		    int t=a[j]; a[j]=a[j-1]; a[j-1]=t;
		    ++swaps;

		    p(a);
		}
	}

	assert isSorted(a);
	p(a);
    }   
}
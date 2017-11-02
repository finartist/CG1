package Sortieralgorithmen;
import java.util.Random;

public class Mergesort extends Sort {
  
    public void sort(int[] a) {
	p(a);

	mergesort(a);

	assert isSorted(a);
	p(a);
    }

    void mergesort(int[] c) {
	int na=c.length/2;
	int nb=c.length-na;  // mind integer division
	
	int[] a=new int[na]; // split c: copy parts
	for (int i=0;i<na;++i) a[i]=c[i];
	
	int[] b=new int[nb];
	for (int j=0;j<nb;++j) b[j]=c[j+na];
	
	if (a.length>1) mergesort(a); // recursive
	if (b.length>1) mergesort(b); //  sort
	
	p(a);	// NOTE: p() prints partial arrays!

	merge(a,b,c);                 // merge
    }     

    void merge(int[] a,int[] b,int[] c) {
	assert (c.length>=a.length+b.length);

	int i=0,j=0;
	for (int k=0;k<a.length+b.length;++k) {
	    if      (i>=a.length) c[k]=b[j++];
	    else if (j>=b.length) c[k]=a[i++];
	    else if (a[i]<=b[j])  c[k]=a[i++];
	    else                  c[k]=b[j++];
	} 
    }           
}
package blatt12;

//if using lecture pseudo code -> 4 times same object

//seems to work now

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KnapsackDyn {
	int m_capacity;
	int m_numberOfObjects;
	int m_weight;
	ArrayList<Integer> m_inventory;
	float m_value;
	
	public KnapsackDyn(int c){
		m_capacity = c;
		m_numberOfObjects = 0;
		m_weight = 0;
		m_value = 0.0f;
		m_inventory = new ArrayList<Integer>();
	}

	public int getCapacity() {
		return m_capacity;
	}

	public int getNumberOfObjects() {
		return m_numberOfObjects;
	}

	public int getWeight() {
		return m_weight;
	}

	public float getValue() {
		return m_value;
	}
	
	public ArrayList<Integer> getInventory(){
		return m_inventory;
	}
	
	//filling a knapsack will always replace the old inventory!
	public void fill(int numObjects, int[] weight, float[] value) throws Exception{
		
		//throw exception if number of objects and value or weights do not match
		if(weight == null || value == null || weight.length != numObjects || value.length != numObjects){
			throw new Exception("Invalid argument detected. Number of objects and number of weights/values does not match or is null.");
		}
		
		//ask if it is ok to replace old inventory
		Scanner s = new Scanner(System.in);
		System.out.println("Filling a knapsack will always replace old inventory...\nDo you want to continue?\nY/N\n");
		String answer = s.next();
		if(!answer.equals("Y") && !answer.equals("y")){
			System.out.println("Filling Knapsack aborted...");
			return;
		}
		System.out.println("Filling in progress...");
		
		m_inventory = new ArrayList<Integer>();
		
		float[][] profit = new float[numObjects+1][this.m_capacity+1];
		int[] best = new int[m_capacity+1];
		for(int i = 0; i <= m_capacity; ++i){
			best[i] = -1;
		}
		
		for(int i = 1; i <= numObjects; ++i){
			float[][] pk = profit;
			for(int j = 0; j <= m_capacity; ++j){
				float leave = profit[i-1][j];
				float take;
				if(j < weight[i-1]){
					take = profit[i-1][j];
				}
				else{
					take = profit[i-1][j-weight[i-1]]+value[i-1];
				}
				if(take > pk[i-1][j]){
					pk[i][j] = take;
					best[j] = i-1;
				}
				else{
					pk[i][j] = leave;
				}
			}
			profit = pk;
		}
		System.out.println(Arrays.deepToString(profit));
		//g:=C; while g>0 do k:=best[g]; g:=g-WEIGHT[k]; od
		int g = m_capacity-1;
		while(g > 0){
			int k = best[g];
			if(k >= 0){
				m_inventory.add(k);
				g = g - weight[k];
			}
			else{
				g = k;
			}
			
		}
		System.out.println("Profit: " + profit[numObjects][m_capacity]);
		System.out.println(m_inventory.toString());
	}
	
	public static KnapsackDyn EinbruchinLinearerZeitApptm(int capacity, int objects, int[] weight, float[] value) throws Exception{
		KnapsackDyn myKnapsack = new KnapsackDyn(capacity);
		myKnapsack.fill(objects, weight, value);
		return myKnapsack;
	}

	public static void main(String[] args) {
		try {
			int[] weights = {2, 3, 7, 4};
			float[] val = {4.2f, 3.1f, 10.0f, 3.6f};
			KnapsackDyn aKnapsack = EinbruchinLinearerZeitApptm(10, 4, weights, val);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
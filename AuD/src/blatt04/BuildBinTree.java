package blatt04;

import aud.BinaryTree;
import aud.util.*;

public class BuildBinTree {
	public static void main(String[] args) {
		SingleStepper step = new SingleStepper("Stepper");
		
		//SimpleDecorator dec = new SimpleDecorator();
		
		BinaryTree<Integer> number7 = new BinaryTree<Integer>(7);
		BinaryTree<Integer> number5 = new BinaryTree<Integer>(5);
		BinaryTree<Integer> number_11 = new BinaryTree<Integer>(-11);
		BinaryTree<Integer> number6 = new BinaryTree<Integer>(6);
		BinaryTree<Integer> number1 = new BinaryTree<Integer> (1, number5, number7);
		BinaryTree<Integer> number4 = new BinaryTree<Integer>( 4, number6, number_11);
		BinaryTree<Integer> root = new BinaryTree<Integer>(-8, number4, number1);
		
		DotViewer viewer = DotViewer.displayWindow(root, "Binary Tree");
		
		step.halt("PREORDER");
		for(BinaryTree<Integer> a : root.preorder()){
			step.halt(a.toString());
		}
		
		step.halt("POSTORDER");
		for(BinaryTree<Integer> a : root.postorder()){
			step.halt(a.toString());
		}
		
		step.halt("INORDER");
		for(BinaryTree<Integer> a : root.inorder()){
			step.halt(a.toString());
		}
		
		step.halt("LEVELORDER");
		for(BinaryTree<Integer> a : root.levelorder()){
			step.halt(a.toString());
		}
	}
}


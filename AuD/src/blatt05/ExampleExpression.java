package blatt05;

import aud.BinaryTree;
import aud.example.expr.AtomicExpression;
import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;


import aud.util.*;

public class ExampleExpression {
   public static void main(String[] args) {
	   //Scanner scn = new Scanner(System.in);
	   //String input = scn.nextLine();
	   
	   String input = "234*(323-23)+23-3";
	   
	   ExpressionParser p = new ExpressionParser();
	    p.setVerbose(true);
	    ExpressionTree tree=p.parse(input);
	    
	    DotViewer view = DotViewer.displayWindow(tree, "Expressiontree");
		SingleStepper step = new SingleStepper("Stepper");
	    
		for(BinaryTree<AtomicExpression> t : tree.inorder()){
			step.halt(t.getData().toString());
		}
		
		for(BinaryTree<AtomicExpression> t : tree.preorder()){
			step.halt(t.getData().toString());
		}
		
		for(BinaryTree<AtomicExpression> t : tree.postorder()){
			step.halt(t.getData().toString());
		}
	    System.out.println("output = '"+tree+"'");
	    
	    System.out.println("Value: " + tree.getValue());
	    System.out.println("Inorder: " + tree.inorder().toString());
	    System.out.println("Preorder: " + tree.preorder().toString());
	    System.out.println("Postorder: " + tree.postorder().toString());
	    
	    
   }
}
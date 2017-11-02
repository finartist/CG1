package blatt06;

import aud.BinaryTree;
import aud.Queue;
import aud.example.expr.*;
import aud.util.DotViewer;
import aud.util.SingleStepper;

public class ExprWithPow extends ExpressionParser2{
	
	protected ExpressionTree power(int level) {
	    verbose(level,"<power>");
	   
	    // temporary storage
	    Queue<ExpressionTree> expr = new Queue<ExpressionTree>();
	    Queue<Integer>        op   = new Queue<Integer>();
	    
	    ExpressionTree tree=factor(level+1);
	    
	    // parse iteratively
	    while (lookahead()==Tokenizer.POWER) {
	      op.enqueue(lookahead());
	      nextToken();      
	      expr.enqueue(factor(level+1));      
	    }
	          
	    // setup tree
	    while (!op.is_empty()) {
	    	if(op.dequeue()==Tokenizer.POWER)
	    		tree=new ExpressionTree(new Power(),tree,expr.dequeue());
	    }
	    return tree;
	  }
	
	@Override
	protected ExpressionTree product(int level) {
	    verbose(level,"<product>");
	   
	    // temporary storage
	    Queue<ExpressionTree> expr = new Queue<ExpressionTree>();
	    Queue<Integer>        op   = new Queue<Integer>();
	    
	    ExpressionTree tree=power(level+1);
	    
	    // parse iteratively
	    while (lookahead()==Tokenizer.TIMES || lookahead()==Tokenizer.DIVIDE) {
	      op.enqueue(lookahead());
	      nextToken();      
	      expr.enqueue(power(level+1));      
	    }
	          
	    // setup tree
	    while (!op.is_empty()) {      
	      if (op.dequeue()==Tokenizer.TIMES) 
	        tree=new ExpressionTree(new Times(),tree,expr.dequeue());
	      else
	        tree=new ExpressionTree(new Divide(),tree,expr.dequeue());
	    }
	    return tree;
	  }
	
		
//	protected ExpressionTree power(int level) {
//	    verbose(level,"<power>");
//
//	    ExpressionTree left=factor(level+1);
//
//	    if (lookahead()==Tokenizer.POWER) {
//	      nextToken();
//	      ExpressionTree right=power(level+1);
//	      return new ExpressionTree(new Power(),left,right);
//	    }
//	    return left;
//	  }
//   
//   @Override
//   protected ExpressionTree product(int level) {
//	    verbose(level,"<product>");
//
//	    ExpressionTree left=power(level+1);
//
//	    if (lookahead()==Tokenizer.TIMES) {
//	      nextToken();
//	      ExpressionTree right=product(level+1);
//	      return new ExpressionTree(new Times(),left,right);
//	    }
//	    else if (lookahead()==Tokenizer.DIVIDE) {
//	      nextToken();
//	      ExpressionTree right=product(level+1);
//	      return new ExpressionTree(new Divide(),left,right);
//	    }
//
//	    return left;
//	  }
   
   public static void main(String[] args) {
	   String input = "234*(323-23)+(2^3)";

	   ExpressionParser p = new ExprWithPow();
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



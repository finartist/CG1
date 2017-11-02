package blatt06;

import aud.*;
import aud.example.expr.*;
import aud.example.expr.Number;
import aud.util.*;

public class Differentiation {
	
//	die einen gegebenen ExpressionTree t nach der Variablen var ableiten kann und als Ergebnis einen neuen ExpressionTree liefert. Realisieren Sie dabei nur die Summen- und die Produktregel:
//		(x+y)' = x' + y', (x-y)' = x' - y' bzw. (x*y)' = x'*y + x*y'
//
//		Hinweis:
//		BinaryTree<AtomicExpression> t;
//		Test z.B. auf Operation "+" mit
//		if(t.getData().getType() == AtomicExpression.Type.OpPlus)


	public static ExpressionTree differentiate(BinaryTree<AtomicExpression> t, String var) {
		String x = var;
		ExpressionTree newTree = (ExpressionTree) t;
		
		if(newTree.getParent().getData().getType() == AtomicExpression.Type.OpTimes){
			//do nothing
		}
		else if(newTree.getData().isTerminal() && newTree.getData().toString().equals(x)){
			newTree.setData(new Symbol("1"));
		}
		else if(newTree.getData().isTerminal() && !newTree.getData().toString().equals(x)){
			newTree.setData(new Symbol("0"));
		}
		else if(newTree.getData().getType() == AtomicExpression.Type.OpPlus || newTree.getData().getType() == AtomicExpression.Type.OpMinus){
			differentiate(newTree.getLeft(), var);
			differentiate(newTree.getRight(), var);
		}
		else if(newTree.getData().getType() == AtomicExpression.Type.OpTimes){
			ExpressionTree tree = new ExpressionTree(new Plus(), 
					new ExpressionTree(new Times(), differentiate(newTree.getLeft(), x), (ExpressionTree) newTree.getRight()), 
					new ExpressionTree(new Times(), (ExpressionTree) newTree.getLeft(), differentiate(newTree.getRight(), x)));
			newTree = tree;
		}
		
		return newTree;
	}

	public static void main(String[] args) {
		String input = "x+x*y";
		
		ExpressionParser p = new ExpressionParser();
	    p.setVerbose(true);
	    ExpressionTree tree=p.parse(input);
	    
	    DotViewer view = DotViewer.displayWindow(tree, "Expressiontree normal");
	    
	    ExpressionTree t = differentiate(tree, "x");
	    System.out.println(t.toTikZ());
	    DotViewer.displayWindow(t, "Expressiontree differ");
	}
}
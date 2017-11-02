package aud.example.expr;

import aud.example.expr.AtomicExpression.Type;

public class Power extends Operator {

	@Override public double getValue() {
		ExpressionTree left =(ExpressionTree) node_.getLeft();
		ExpressionTree right=(ExpressionTree) node_.getRight();
		return 
				Math.pow(left.getData().getValue(), right.getData().getValue());
	}
	@Override public Type getType() { return Type.OpPower; }
	@Override public String toString() { return "^"; }
}

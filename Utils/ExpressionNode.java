package Utils;

public class ExpressionNode {
    String value;
    ExpressionNode left, right;

    ExpressionNode(String item) {
        value = item;
        left = right = null;
    }
}

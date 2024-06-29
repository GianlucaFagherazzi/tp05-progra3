package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpressionEvaluatorGUI {
    private JFrame frame;
    private JTextField infixExpressionField;
    private JTextArea resultArea;

    public void createAndShowGUI() {
        frame = new JFrame("Evaluador de Expresiones Matemáticas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel infixLabel = new JLabel("Expresión Infija:");
        infixExpressionField = new JTextField(20);
        JButton evaluateButton = new JButton("Evaluar");

        inputPanel.add(infixLabel);
        inputPanel.add(infixExpressionField);
        inputPanel.add(evaluateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        evaluateButton.addActionListener(new EvaluateButtonListener());

        frame.setVisible(true);
    }

    private class EvaluateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String infix = infixExpressionField.getText();
            try {
                String postfix = InfixToPostfix.infixToPostfix(infix);

                ExpressionTree et = new ExpressionTree();
                ExpressionNode root = et.constructTree(postfix);

                StringBuilder inorderResult = new StringBuilder();
                et.inorder(root, inorderResult);

                StringBuilder preorderResult = new StringBuilder();
                et.preorder(root, preorderResult);

                StringBuilder postorderResult = new StringBuilder();
                et.postorder(root, postorderResult);

                double evaluationResult = et.evaluate(root);

                resultArea.setText("Recorrido Inorder: " + inorderResult.toString() + "\n"
                        + "Recorrido Preorder: " + preorderResult.toString() + "\n"
                        + "Recorrido Postorder: " + postorderResult.toString() + "\n"
                        + "Resultado de la Evaluación: " + evaluationResult);
            } catch (IllegalArgumentException ex) {
                resultArea.setText("Error: " + ex.getMessage());
            }
        }
    }
}

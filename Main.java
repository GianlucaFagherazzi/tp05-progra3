import javax.swing.*;
import Utils.*;

public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpressionEvaluatorGUI().createAndShowGUI());
    }
}

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
public class WarningDisplay extends JFrame implements ActionListener,DocumentListener   {
    private JFrame SmallFrame;
    private JButton Exit;
    private JTextArea TextBlock;
    private JLabel Title;
    private JLabel Presentation;
    private FileHandler QuestionGetter;
    public WarningDisplay(String ErrorMessage){

        QuestionGetter = new FileHandler("C:\\Users\\Matthew\\Documents\\Questions.txt",100);

        SmallFrame = new JFrame("Error!");
        SmallFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SmallFrame.setBounds(0, 0, 365, 250);
        SmallFrame.setLayout(null);
        SmallFrame.setBackground(Color.RED);
        TextBlock = new JTextArea(5,20);

        Title = new JLabel("Error!");
        Title.setFont(new Font("Arial", Font. BOLD, 24));
        Title.setBounds(0, 0, 365, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);
        SmallFrame.add(Title);

        Exit = new JButton("Okay");
        Exit.setBounds(107, 150, 150, 40);
        Exit.setHorizontalAlignment(JButton.CENTER);
        Exit.addActionListener(this);
        SmallFrame.add(Exit);

        //Receives all records matching question in an array
        Presentation = new JLabel(ErrorMessage);
        Presentation.setBounds(20,75,325,40);
        Presentation.setHorizontalAlignment(JLabel.CENTER);
        SmallFrame.add(Presentation);
        SmallFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Okay")){
            SmallFrame.dispose();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}

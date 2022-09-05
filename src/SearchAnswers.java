import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
public class SearchAnswers extends JFrame implements ActionListener,DocumentListener  {
    private JFrame SmallFrame;
    private JButton Exit;
    private JLabel Title;
    private String[] Answers;
    private JLabel[] Presentation;
    private FileHandler QuestionGetter;
    public SearchAnswers(String Inquisition){

        QuestionGetter = new FileHandler("C:\\Users\\Matthew\\Documents\\Questions.txt",50);

        SmallFrame = new JFrame("Teacher Menu");
        SmallFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SmallFrame.setBounds(0, 0, 750, 500);
        SmallFrame.setLayout(null);
        SmallFrame.setBackground(Color.RED);


        Title = new JLabel("Here's what was found:");
        Title.setFont(new Font("Arial", Font. BOLD, 24));
        Title.setBounds(0, 0, 750, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);
        SmallFrame.add(Title);

        Exit = new JButton("Done");
        Exit.setBounds(5, 300, 150, 40);
        Exit.setHorizontalAlignment(JButton.CENTER);
        Exit.addActionListener(this);

        Answers = QuestionGetter.findAllRecordsFromKeyword(Inquisition);
        System.out.println(Answers.length);
        Arrays.stream(Answers).sorted();
        //Receives all records matching question in an array
        Presentation = new JLabel[Answers.length];
        for(int i = 0;i < Answers.length;i++){
            if (Answers[i] == null){
                break;
            }
            Presentation[i] = new JLabel();
            Presentation[i].setText(Answers[i]);
            //Sets JLabel text to the According Answer
            Presentation[i].setBounds(20,((i*25)+100),530,40);
            Presentation[i].setHorizontalAlignment(JLabel.LEFT);
            SmallFrame.add(Presentation[i]);
            Presentation[i].setVisible(true);
            System.out.println("added " + Presentation[i].getText());
        }
        SmallFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Done")){
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

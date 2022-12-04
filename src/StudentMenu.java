import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;
public class StudentMenu extends JFrame implements ActionListener,DocumentListener {
    AccountHandler MainHandler = new AccountHandler();
    int EnterClick = 0;
    private JFrame StudentFrame;
    private JButton QuestionsButton;
    private JButton SearchButton;
    private JButton Exit;
    private JButton QuestionaireMaker;
    private JButton NextButton;
    private int NumberOfPresses;
    private String[] QuestionArray;
    private JButton EnterButton;
    private JTextField UserInputBox;
    private JComboBox QuestionList;
    private String WhichMenu;
    private JLabel QuestionDisplay;
    private JLabel Title;
    private String Question;
    private String Date;
    private String Subject;
    private String Topic;
    private FileHandler QuestionHandler;
    private String filename;

    public StudentMenu(String Username) {

        System.out.println("Opened teacher");
        StudentFrame = new JFrame("Student Menu");
        StudentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StudentFrame.setBounds(0, 0, 750, 500);
        StudentFrame.setLayout(null);
        StudentFrame.setBackground(Color.RED);

        QuestionDisplay = new JLabel("");
        QuestionDisplay.setBounds(180, 200, 530, 40);
        QuestionDisplay.setHorizontalAlignment(JLabel.CENTER);
        QuestionArray = new String[QuestionHandler.countLines()];

        NextButton = new JButton("Next");
        NextButton.setBounds(420,225,100,40);
        NextButton.setVisible(false);
        NextButton.addActionListener(this);

        QuestionsButton = new JButton("Add Question");
        QuestionsButton.setBounds(5, 100, 150, 40);
        QuestionsButton.addActionListener(this);

        Exit = new JButton("Exit");
        Exit.setBounds(5, 250, 150, 40);
        Exit.setHorizontalAlignment(JButton.CENTER);
        Exit.addActionListener(this);

        SearchButton = new JButton("Search for Question");
        SearchButton.setBounds(5, 200, 150, 40);
        SearchButton.addActionListener(this);

        EnterButton = new JButton("Enter");
        EnterButton.setBounds(605, 200, 100, 40);
        EnterButton.setVisible(false);
        EnterButton.addActionListener(this);

        QuestionaireMaker = new JButton("Make Questionaire");
        QuestionaireMaker.setBounds(5, 150, 150, 40);
        QuestionaireMaker.addActionListener(this);


        Title = new JLabel("Hello, " + Username);
        Title.setFont(new Font("Arial", Font. BOLD, 24));
        Title.setBounds(180, 0, 530, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);

        UserInputBox = new JTextField("");
        UserInputBox.setBounds(385,200,200, 40);
        UserInputBox.getDocument().addDocumentListener(this);
        UserInputBox.addActionListener(this);

        UserInputBox.setVisible(false);
        UserInputBox.addActionListener(this);

        filename = "C:\\Users\\Matthew\\Documents\\Questions.txt";
        QuestionHandler = new FileHandler(filename,100);
        StudentFrame.add(UserInputBox);
        StudentFrame.add(Exit);
        StudentFrame.add(QuestionsButton);
        StudentFrame.add(SearchButton);
        StudentFrame.add(Title);
        StudentFrame.add(QuestionaireMaker);
        StudentFrame.add(EnterButton);
        StudentFrame.setVisible(true);
        GUIMainMenu();

    }
    public void GUIMainMenu(){
        Exit.setVisible(true);
        SearchButton.setVisible(true);
        QuestionsButton.setVisible(false);
        UserInputBox.setVisible(false);
        EnterButton.setVisible(false);
        WhichMenu = "Main";
    }

    public void AllOf(){
        Title.setText("Select a button to begin");
        QuestionDisplay.setVisible(false);
        UserInputBox.setVisible(false);
        EnterButton.setVisible(false);
        if (QuestionList != null){
            QuestionList.setVisible(false);
        }
        NextButton.setVisible(false);

    }
    public void QuestionDisplayer(){
        AllOf();
        NextButton.setVisible(true);
        EnterButton.setVisible(true);
        EnterButton.setText("Go back");
        EnterButton.setBounds(205, 200, 100, 40);
        Title.setText("Answer the Questions!");
        NumberOfPresses = 0;
        QuestionList.setVisible(false);
        QuestionDisplay.setVisible(true);
        QuestionDisplay.setText(QuestionArray[0]);
        WhichMenu = "QuestionDisplayer";
        NextButton.setBounds(420,235,100,40);
    }
    public void QuestionnaireMaker(){
        AllOf();
        NextButton.setVisible(true);
        EnterButton.setVisible(true);
        String[] QuestionArray = new String[QuestionHandler.countLines()];
        for(int i = 0;i<QuestionHandler.countLines();i++){
            QuestionArray[i] = QuestionHandler.getRecord(i);
        }
        QuestionList = new JComboBox(QuestionArray);
        QuestionList.setEditable(true);
        QuestionList.addActionListener(this);
        QuestionList.setBounds(250,175,300, 40);
        Title.setText("Step 1. Add Questions");
        EnterButton.setText("Add");
        EnterButton.setBounds(560,175,100,40);
        EnterButton.setVisible(true);
        StudentFrame.add(QuestionList);
        WhichMenu = "QuestionnaireMaker";
        NextButton.setBounds(560,225,100,40);
    }

    public void SearchQuestionGui(){
        AllOf();
        Title.setText("Enter keyword eg Date/Question/Topic/Subject");
        UserInputBox.setVisible(true);
        EnterButton.setVisible(true);
        WhichMenu = "FindQuestion";
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().equals("Search for Question"))) {
            SearchQuestionGui();
        }

        if (Objects.equals(WhichMenu, "FindQuestion")) {
            if (e.getActionCommand().equals("Enter")) {
                new GUImain(3,UserInputBox.getText());
            }
        }
        if (e.getActionCommand().equals("Make Questionaire")){
            QuestionnaireMaker();
        }
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("Go back")) {
            GUIMainMenu();
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
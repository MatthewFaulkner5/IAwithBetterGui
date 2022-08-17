import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
public class TeacherMenu extends JFrame implements ActionListener,DocumentListener {
    AccountHandler MainHandler = new AccountHandler();
    int EnterClick = 0;
    private JFrame TeacherFrame;
    private JButton QuestionsButton;
    private JButton SearchButton;
    private JButton Exit;
    private JButton EnterButton;
    private JTextField UserInputBox;
    private JPasswordField passwordInput;
    private String WhichMenu;
    private String InputFromBox;
    private JLabel Title;

    private String Question;
    private String Date;
    private String Subject;
    private String Topic;


    public TeacherMenu(String Username) {

        System.out.println("Opened teacher");
        TeacherFrame = new JFrame("Teacher Menu");
        TeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TeacherFrame.setBounds(0, 0, 365, 250);
        TeacherFrame.setLayout(null);
        TeacherFrame.setBackground(Color.RED);
        TeacherFrame.setVisible(true);
        QuestionsButton = new JButton("Add Question");
        Exit = new JButton("Exit");
        SearchButton = new JButton("Search");
        EnterButton = new JButton("Enter");
        QuestionsButton.setBounds(5, 100, 100, 40);
        QuestionsButton.addActionListener(this);
        Exit.setBounds(250, 100, 100, 40);
        Exit.setHorizontalAlignment(JButton.CENTER);
        Exit.addActionListener(this);
        SearchButton.setBounds(125, 100, 100, 40);
        SearchButton.addActionListener(this);

        Title = new JLabel("Hello, " + Username);
        Title.setBounds(80, 0, 200, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);
        TeacherFrame.add(Exit);
        TeacherFrame.add(QuestionsButton);
        TeacherFrame.add(SearchButton);
        TeacherFrame.add(Title);
        TeacherFrame.add(EnterButton);
        EnterButton.setBounds(230, 60, 100, 40);
        EnterButton.setVisible(false);

        UserInputBox = new JTextField("");
        UserInputBox.setBounds(20, 60, 200, 40);
        UserInputBox.getDocument().addDocumentListener(this);
        UserInputBox.addActionListener(this);
        TeacherFrame.add(UserInputBox);
        UserInputBox.setVisible(false);
        UserInputBox.addActionListener(this);
        EnterButton.addActionListener(this);
        TeacherFrame.setVisible(true);
        GUIMainMenu();
    }
    public void GUIMainMenu(){
        Exit.setVisible(true);
        SearchButton.setVisible(true);
        QuestionsButton.setVisible(true);
        UserInputBox.setVisible(false);
        WhichMenu = "Main";
    }
    public void AddQuestion(String Subject,String Topic,String Date,String Question) {
        FileHandler.appendLine("C:\\Users\\Matthew\\Documents\\Questions.txt",Subject + "," +Topic + "," + Date + "," + Question);

    }
    public void AddQuestionGui(){
        Question = null;Date = null;Topic = null;Subject = null;
        UserInputBox.setVisible(true);
        Exit.setText("Go back");
        Title.setText("Enter Subject");
        Exit.setVisible(true);
        Exit.setBounds(125, 150, 100, 40);
        EnterButton.setVisible(true);
        SearchButton.setVisible(false);
        QuestionsButton.setVisible(false);
        WhichMenu = "AddQuestion";
    }
    public void AllOf(){
        UserInputBox.setVisible(false);
        SearchButton.setVisible(false);
        QuestionsButton.setVisible(false);
        EnterButton.setVisible(false);
    }

    public void DeleteQuestion() {
        //Deletes question from database using keywords
    }

    public void EditQuestion() {
        //Replaces question
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().equals("Find Question"))) {
            //FindQuestion();
        }
        if (e.getActionCommand().equals("Add Question")){
            AllOf();
            AddQuestionGui();

        }

        if (Objects.equals(WhichMenu, "FindQuestion")) {
            if (e.getActionCommand().equals("Enter")) {
               // myStudent.FindTopic(UserInputBox.getText());
            }
        }
        if (Objects.equals(WhichMenu,"AddQuestion") & e.getActionCommand().equals("Enter")) {
            if (Title.getText() == "Add Question Content") {
                Question = UserInputBox.getText();
                UserInputBox.setText("");

                System.out.println("QuestionDone");
                Title.setText("Added!");
                AddQuestion(Subject,Topic,Date,Question);

            }
            if (Title.getText() == "Enter Date") {
                Date = UserInputBox.getText();
                UserInputBox.setText("");
                Title.setText("Add Question Content");
                System.out.println("Done date");
            }
            if (Title.getText() == "Enter Topic"){

                Topic = UserInputBox.getText();
                UserInputBox.setText("");
                System.out.println("Done topic");
                Title.setText("Enter Date");
            }
            if (Title.getText() == "Enter Subject") {
                Subject = UserInputBox.getText();
                UserInputBox.setText("");
                System.out.println("Done subject");
                Title.setText("Enter Topic");
            }
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
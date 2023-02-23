import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;
public class TeacherMenu extends JFrame implements ActionListener,DocumentListener {
    AccountHandler MainHandler = new AccountHandler();
    int EnterClick = 0;
    private JFrame TeacherFrame;
    private JLabel QuestionCounter;
    private JButton QuestionsButton;
    private JButton SearchButton;
    private JButton Exit;
    private JButton NextButton;
    private JButton QuestionnaireMaker;
    private JButton EnterButton;
    private JTextField UserInputBox;
    private String WhichMenu;
    private JComboBox QuestionList;
    private JLabel QuestionDisplay;
    private JLabel Title;
    private String Question;private String Date;private String Subject;private String Topic;
    private int NumberOfPresses;
    private FileHandler QuestionHandler;
    private String filename;
    private String[] QuestionArray;
    public TeacherMenu(String Username) {

        System.out.println("Opened teacher");
        TeacherFrame = new JFrame("Teacher Menu");
        TeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TeacherFrame.setBounds(0, 0, 750, 500);
        TeacherFrame.setLayout(null);
        TeacherFrame.setBackground(Color.RED);

        QuestionCounter = new JLabel("Questions Added: ");
        QuestionCounter.setBounds(180, 55, 530, 40);
        QuestionCounter.setHorizontalAlignment(JLabel.CENTER);


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

        NextButton = new JButton("Next");
        NextButton.setBounds(420,225,100,40);
        NextButton.setVisible(false);
        NextButton.addActionListener(this);

        QuestionnaireMaker = new JButton("Make Questionnaire");
        QuestionnaireMaker.setBounds(5, 150, 150, 40);
        QuestionnaireMaker.addActionListener(this);

        QuestionDisplay = new JLabel("");
        QuestionDisplay.setBounds(180, 200, 530, 40);
        QuestionDisplay.setHorizontalAlignment(JLabel.CENTER);

        Title = new JLabel("Hello, " + Username);
        Title.setFont(new Font("Arial", Font. BOLD, 24));
        Title.setBounds(180, 0, 530, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);

        UserInputBox = new JTextField("");
        UserInputBox.setBounds(320,175,200, 40);
        UserInputBox.getDocument().addDocumentListener(this);
        UserInputBox.addActionListener(this);

        UserInputBox.setVisible(false);
        UserInputBox.addActionListener(this);



        filename = "Questions.txt";
        QuestionHandler = new FileHandler(filename,100);
        QuestionArray = new String[QuestionHandler.countLines()];

        TeacherFrame.add(UserInputBox);
        TeacherFrame.add(Exit);
        TeacherFrame.add(QuestionsButton);
        TeacherFrame.add(SearchButton);
        TeacherFrame.add(QuestionDisplay);
        TeacherFrame.add(NextButton);
        TeacherFrame.add(QuestionCounter);
        TeacherFrame.add(Title);
        TeacherFrame.add(QuestionnaireMaker);
        TeacherFrame.add(EnterButton);
        TeacherFrame.setVisible(true);
        GUIMainMenu();

    }
    public void GUIMainMenu(){
        Exit.setVisible(true);
        SearchButton.setVisible(true);
        QuestionsButton.setVisible(true);
        UserInputBox.setVisible(false);
        QuestionCounter.setVisible(false);
        EnterButton.setVisible(false);
        NextButton.setVisible(false);
        WhichMenu = "Main";
    }
    public void AddQuestion(String Subject,String Topic,String Date,String Question) {
        QuestionHandler.appendRecord(Subject + "," +Topic + "," + Date + "," + Question,100);

    }
    public void AddQuestionGui(){
        AllOf();
        EnterButton.setText("Enter");
        Question = null;Date = null;Topic = null;Subject = null;
        UserInputBox.setVisible(true);
        Title.setText("Enter Subject");
        Title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        EnterButton.setBounds(530,175,100,40);
        EnterButton.setVisible(true);
        WhichMenu = "AddQuestion";
        EnterButton.setVisible(true);
    }
    public void AllOf(){
        UserInputBox.setBounds(320,175,200, 40);       QuestionCounter.setVisible(false);
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
        EnterButton.setBounds(215, 250, 100, 40);
        Title.setText("Answer the Questions!");
        NumberOfPresses = 0;
        QuestionList.setVisible(false);
        QuestionDisplay.setVisible(true);
        QuestionDisplay.setFont(new Font("Arial", Font. BOLD, 18));
        QuestionDisplay.setText(QuestionArray[0]);
        WhichMenu = "QuestionDisplayer";
        NextButton.setBounds(530,250,100,40);
    }
    public void QuestionnaireMaker(){
        AllOf();
        QuestionCounter.setText("Questions Added: ");
        QuestionCounter.setVisible(true);
        NextButton.setVisible(true);
        EnterButton.setVisible(true);
        String[] QuestionArray = new String[QuestionHandler.countLines()];
        String[] tempArray = new String[3];
        for(int i = 0;i<QuestionHandler.countLines();i++){
            tempArray = QuestionHandler.getRecord(i).split(",");
            QuestionArray[i] = tempArray[3];
        }


        QuestionList = new JComboBox(QuestionArray);
        QuestionList.setEditable(true);
        QuestionList.addActionListener(this);
        QuestionList.setBounds(200,175,350, 40);
        Title.setText("Step 1. Add Questions");
        EnterButton.setText("Add");
        EnterButton.setBounds(560,175,100,40);
        EnterButton.setVisible(true);
        TeacherFrame.add(QuestionList);
        WhichMenu = "QuestionnaireMaker";
        NextButton.setBounds(560,225,100,40);
    }

    public void SearchQuestionGui(){
        AllOf();
        EnterButton.setText("Enter");
        Title.setText("Enter Keyword");
        UserInputBox.setVisible(true);
        EnterButton.setBounds(530,175,100,40);
        EnterButton.setVisible(true);
        WhichMenu = "FindQuestion";
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().equals("Search for Question"))) {
            SearchQuestionGui();
        }

        if (e.getActionCommand().equals("Add Question")){
            AllOf();
            AddQuestionGui();
        }


        if (e.getActionCommand().equals("Make Questionnaire")){
            QuestionnaireMaker();
        }
        if(Objects.equals(WhichMenu,"QuestionnaireMaker")){
            if (e.getActionCommand().equals("Add")){
                QuestionArray[NumberOfPresses] = QuestionList.getSelectedItem().toString();
                System.out.println(QuestionArray[NumberOfPresses]);
                NumberOfPresses++;
                QuestionCounter.setText("Questions Added: " + NumberOfPresses);
            }
            if (e.getActionCommand().equals("Next")){

                QuestionDisplayer();
            }
        }
        if(Objects.equals(WhichMenu,"QuestionDisplayer")){
            if(e.getActionCommand().equals("Next")){
                if(QuestionArray[NumberOfPresses+1] == null){
                    QuestionDisplay.setText("Out of Questions!");
                } else {
                    NumberOfPresses++;
                    QuestionDisplay.setText(QuestionArray[NumberOfPresses]);

                }
            }
            if (e.getActionCommand().equals("Go back")){
                if(QuestionDisplay.getText() == QuestionArray[0]){

                } else {
                    NumberOfPresses--;
                    QuestionDisplay.setText(QuestionArray[NumberOfPresses-1]);
                }
            }
        }else if (e.getActionCommand().equals("Go back")) {
            GUIMainMenu();
        }

        if (Objects.equals(WhichMenu, "FindQuestion")) {
            if (e.getActionCommand().equals("Enter")) {
                if (QuestionHandler.findRecordFromKeyword(UserInputBox.getText()) == true){
                    new GUImain(3,UserInputBox.getText());
                } else {
                    new WarningDisplay("No Results");
                }

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
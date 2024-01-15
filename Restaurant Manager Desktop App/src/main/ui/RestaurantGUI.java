package ui;



import model.Member;
import model.MemberList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

// Represent Restaurant UI Page
public class RestaurantGUI extends JFrame implements ActionListener {
    private JTextArea mainJta;
    private JTextArea firstNameJta;
    private JTextArea lastNameJta;
    private JTextArea emailJta;
    private JTextArea birthDayJta;
    private JTextArea pointsJta;
    private JTextArea lastNameUpdateJta;
    private JTextArea emailUpdateJta;
//    private JFrame window;
//    private JTextArea memberDeleteJta;

    private MemberList members;
    private JButton addButton;
    private JButton viewAllButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton deleteButton;
    private JButton updateButton;


    private static final String JSON_STORE = "./data/memberList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: construct constructor
    public RestaurantGUI() {

        members = new MemberList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        firstNameJta = new JTextArea();
        lastNameJta = new JTextArea();
        emailJta = new JTextArea();
        birthDayJta = new JTextArea();
        pointsJta = new JTextArea();
        lastNameUpdateJta = new JTextArea();
        emailUpdateJta = new JTextArea();
//        window = new JFrame();
//        memberDeleteJta = new JTextArea();
    }

    //EFFECTS:Create and set up the window
    public void initGUI() {
        this.getContentPane().setSize(1200, 1200);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                printLogs();
            }
        });
        mainJta = new JTextArea();
        mainJta.setPreferredSize(new Dimension(600, 400));
        mainJta.setEnabled(false);
        this.getContentPane().add(mainJta);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        addTextAreas();
        addButtons();
        addUpdatePanel();
        this.pack();
        this.setVisible(true);

    }

    // EFFECTS:add the input text area
    private void addTextAreas() {
        Panel panel = new Panel();
        ImageIcon icon = new ImageIcon("./data/Cat.jpeg");
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);
        JLabel label = new JLabel(icon);
        panel.add(label);

        panel.add(new JLabel("First Name"));
        firstNameJta.setColumns(10);
        panel.add(firstNameJta);

        panel.add(new JLabel("Last Name"));
        lastNameJta.setColumns(10);
        panel.add(lastNameJta);

        panel.add(new JLabel("Email"));
        emailJta.setColumns(10);
        panel.add(emailJta);

        panel.add(new JLabel("Birthday"));
        birthDayJta.setColumns(10);
        panel.add(birthDayJta);

        panel.add(new JLabel("Points"));
        pointsJta.setColumns(10);
        panel.add(pointsJta);

        this.getContentPane().add(panel);

    }

    //EFFECTS: add the buttons
    private void addButtons() {
        Panel panel = new Panel();
        addButton = new JButton("New Member");
        addButton.addActionListener(this);
        panel.add(addButton);

        viewAllButton = new JButton("View All Member");
        viewAllButton.addActionListener(this);
        panel.add(viewAllButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        panel.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        panel.add(loadButton);

        this.getContentPane().add(panel);
    }

    //EFFECTS: add the update panel, update button, delete button
    private void addUpdatePanel() {
        Panel panel = new Panel();
        panel.add(new JLabel("Please input the last name: "));
        lastNameUpdateJta.setColumns(10);
        lastNameUpdateJta.getDocument().addDocumentListener(new UpdatePointsDocumentListener());
        panel.add(lastNameUpdateJta);

        panel.add(new JLabel("Please input the email name: "));
        emailUpdateJta.setColumns(10);
        emailUpdateJta.getDocument().addDocumentListener(new UpdatePointsDocumentListener());
        panel.add(emailUpdateJta);

        updateButton = new JButton(("Update"));
        updateButton.addActionListener(this);
        panel.add(updateButton);
        deleteButton = new JButton(("Delete"));
        deleteButton.addActionListener(this);
        panel.add(deleteButton);
        this.getContentPane().add(panel);

    }


    //EFFECTS: UpdatePointsDocumentListener for lastNameUpdateJta & emailUpdateJta
    private class UpdatePointsDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            handleChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleChange();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            handleChange();
        }


        private void handleChange() {
            String lastName = lastNameUpdateJta.getText();
            String email = emailUpdateJta.getText();
            Member member = members.getMember(lastName, email);
//            System.out.println("member: " + member);
            refreshTextFields(member);
        }


    }

    //EFFECTS: Once update the point, then update the textfield
    private void refreshTextFields(Member member) {
        if (member != null) {
            firstNameJta.setText(member.getFirstName());
            lastNameJta.setText(member.getLastName() + "");
            emailJta.setText(member.getEmail() + "");
            birthDayJta.setText(member.getBirthDay() + "");
            pointsJta.setText(member.getPoints() + "");
        } else {
            firstNameJta.setText("");
            lastNameJta.setText("");
            emailJta.setText("");
            birthDayJta.setText("");
            pointsJta.setText("");
        }

    }

//    // EFFECTS: add delete button
//    private void addDeletePanel() {
//        Panel panel = new Panel();
//        panel.add(new JLabel("Please input the last name: "));
//        memberDeleteJta.setColumns(10);
//        panel.add(memberDeleteJta);
//        panel.add(new JLabel("Please input the email name: "));
//        memberDeleteJta1.setColumns(10);
//        panel.add(memberDeleteJta1);
//
//        deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(this);
//        panel.add(deleteButton);
//
//        this.getContentPane().add(panel);
//    }



    //Required by ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (addButton.equals(button)) {
            addMember();
        } else if (viewAllButton.equals(button)) {
            refreshJTextAreas();
        } else if (saveButton.equals(button)) {
            saveMemberList();
        } else if (loadButton.equals(button)) {
            loadMemberList();
            refreshJTextAreas();
        } else if (deleteButton.equals(button)) {
            removeMember();
        } else if (updateButton.equals(button)) {
            changePoints();

        }

    }



    //MODIFIES:this
    //EFFECTS：add new member
    public void addMember() {
        String firstName = firstNameJta.getText();
        String lastName = lastNameJta.getText();
        String email = emailJta.getText();
        String birthDay = birthDayJta.getText();
        int points = Integer.parseInt(pointsJta.getText());
        members.addMember(firstName, lastName, email, birthDay, points);
        refreshJTextAreas();
    }

    //MODIFIES:this
    //EFFECTS: input the last name and email to remove the member from the memberList
    private void removeMember() {
        String lastName = lastNameUpdateJta.getText();
        String email = emailUpdateJta.getText();
        Member member = members.getMember(lastName, email);
        if (member == null) {
            mainJta.setText("Member Not Existed");
            return;
        }
        members.removeMember(lastName, email);
        refreshJTextAreas();
    }

    // MODIFIES:this
    // EFFECTS: input the last name and email to change member's points
    private void changePoints() {
        String lastName = lastNameJta.getText();
        String email = emailJta.getText();
//      int points = Integer.parseInt(pointsUpdateJta.getText());
        Member member = members.getMember(lastName, email);
        if (member == null) {
            mainJta.setText("Member Not Existed");
            return;
        }
        member.setFirstName(firstNameJta.getText());
        member.setLastName(lastNameJta.getText());
        member.setEmail(emailJta.getText());
        member.setBirthDay(birthDayJta.getText());
        member.setPoints(Integer.parseInt(pointsJta.getText()));
        refreshJTextAreas();
    }

    //EFFECTS: refresh mainJta
    private void refreshJTextAreas() {
        mainJta.setText(members.toString());
        firstNameJta.setText("");
        lastNameJta.setText("");
        emailJta.setText("");
        birthDayJta.setText("");
        pointsJta.setText("");
        lastNameUpdateJta.setText("");
        emailUpdateJta.setText("");
//        memberDeleteJta.setText("");

    }

    //EFFECTS: saves the memberList to file
    private void saveMemberList() {
        try {
            jsonWriter.open();
            jsonWriter.write(members);
            jsonWriter.close();
            System.out.println("Write to file: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads memberList from file
    private void loadMemberList() {
        try {
            members = jsonReader.read();
            System.out.println("Loaded members from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: print the log info
    public void printLogs() {
        members.printLogs();
//        Iterator<Event> eventIterator = EventLog.getInstance().iterator();
//        while (eventIterator.hasNext()) {
//            System.out.println(eventIterator.next().toString());
//        }
    }




}



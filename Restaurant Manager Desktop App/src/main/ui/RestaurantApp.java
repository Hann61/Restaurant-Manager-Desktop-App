package ui;


import model.MemberList;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//RestaurantApp is designed for restaurant manager to manage their members.
// So it has 7 options including addMember,remove member, change points, view member list,
// save member list, load previous list and exit.
// The manager is able to choose the methods they want.

//  Represents the restaurant application
public class RestaurantApp {
    private static final String JSON_STORE = "./data/memberList.json";
    private MemberList members;
    private Scanner scanner;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    //EFFECTS: constructs MemberList
    public RestaurantApp() {
        members = new MemberList();
        scanner = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: processes user input and user command
    public void start() {
        while (true) {
            mainMenu();
            scanner = new Scanner(System.in);
            System.out.println("Please select an option: ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 1) {
                addNewMember();
            } else if (option == 2) {
                removeMember();
            } else if (option == 3) {
                changePoints();
            } else if (option == 4) {
                viewMemberList();
            } else if (option == 5) {
                saveMemberList();
            } else if (option == 6) {
                loadMemberList();
            } else {
                break;
            }
        }

    }

    //EFFECTS: displays menu of options to user
    private void mainMenu() {
        System.out.println("1. add new member");
        System.out.println("2. remove member");
        System.out.println("3. change points");
        System.out.println("4. view all the member");
        System.out.println("5. save member list to file");
        System.out.println("6. load member list from file");
        System.out.println("7. exit");
    }


    // MODIFIES: this
    // EFFECTS: according to the requests input the information then add the new member
    public void addNewMember() {
        System.out.println("Please input the first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Please input the last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Please input the email: ");
        String email = scanner.nextLine();

        System.out.println("Please input the birthday: ");
        String birthday = scanner.nextLine();

        System.out.println("Please input the points: ");
        int p = Integer.parseInt(scanner.nextLine());

        members.addMember(firstName, lastName,email,birthday,p);
    }

    //MODIFIES:this
    //EFFECTS: input the last name and email to remove the member from the memberList
    private void removeMember() {
        System.out.println("Please input the last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Please input the email name: ");
        String email = scanner.nextLine();

        members.removeMember(lastName, email);

    }

    // MODIFIES:this
    // EFFECTS: input the last name and email to change member's points
    private void changePoints() {
        System.out.println("Please input the last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Please input the email name: ");
        String email = scanner.nextLine();

        System.out.println("Please input the points: ");
        int p = Integer.parseInt(scanner.nextLine());

        members.changePoints(lastName, email, p);
    }

    //EFFECTS: print out the member list
    private void viewMemberList() {
        System.out.println(members.toString());
    }

    //EFFECTS: saves the memberList to file
    private void saveMemberList() {
        try {
            jsonWriter.open();
            jsonWriter.write(members);
            jsonWriter.close();
            //System.out.println("Write to file: " + JSON_STORE);
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

}






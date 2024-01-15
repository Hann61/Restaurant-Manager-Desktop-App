package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//This is a class called Member. So it contains the member for restaurant which a restaurant manager need to manage.
//The manager need to know customers' firstname, lastname, email, birthday of a member. The restaurant manager are
//able to change the members' points of given their receipt. And the manager are able to set and get members'
// information including firstName, lastName, email, birthday and points.
public class Member {

    private String firstName;
    private String lastName;
    private String email;
    private String birthDay;
    private int points;

    //REQUIRES: points >= 0
    //EFFECTS：constructs Member with firstName, lastName, email
    //birthday and points
    public Member(String firstName, String lastName,
                  String email, String birthDay, int points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDay = birthDay;
        this.points = points;
    }

    //EFFECTS: Returns the first name of a member
    public String getFirstName() {

        return firstName;
    }

    //EFFECTS: Sets the first name of a member
    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    //EFFECTS: Returns the last name of a member
    public String getLastName() {

        return lastName;
    }

    //EFFECTS: Sets the last name of a member
    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    //EFFECTS: Returns the email of a member
    public String getEmail() {

        return email;
    }

    //EFFECTS: Sets the email name of a member
    public void setEmail(String email) {

        this.email = email;
    }

    //EFFECTS: Returns the birthday of a member
    public String getBirthDay() {

        return birthDay;
    }

    //EFFECTS: Sets the birthday of a member
    public void setBirthDay(String birthDay) {

        this.birthDay = birthDay;
    }

    //EFFECTS: Returns the birthday of a member
    public int getPoints() {

        return points;
    }

    //EFFECTS: Sets the points of a member
    public void setPoints(int points) {

        this.points = points;
    }

    @Override
    // EFFECTS: returns string representation of this member
    public String toString() {
        return "Member{" + "firstName=" + firstName + ", lastName=" + lastName + ","
                + "email=" + email + ", birthDay=" + birthDay + ", points=" + points + '}';
    }

    //@Override
    // EFFECTS：returns members as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("first name", firstName);
        json.put("last name", lastName);
        json.put("email", email);
        json.put("birthday", birthDay);
        json.put("points", points);
        return json;
    }

    //@Override
    //public JSONArray toJsonArray() {
        //return null;
    //}

}


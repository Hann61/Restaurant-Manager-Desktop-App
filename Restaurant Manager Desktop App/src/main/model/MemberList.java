package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;


//MemberList is a class consists of Arraylist of Member. Therefore, the manager can add a member to the list if its
// email were not existed before. The manager can also remove a member by search member's lastname and email; The
// manager are able to change the points of member as well based on the lastname and email.

public class MemberList implements Iterable<Member> {

    private ArrayList<Member> members;



    // EFFECTS: constructs an initial Member list
    public MemberList() {
        members = new ArrayList<Member>();
    }

    //EFFECTS: get the member through last name and email, return null if not exist
    public Member getMember(String lastName, String email) {
        for (Member member : members) {
            if (member.getLastName().equals(lastName)
                    && member.getEmail().equals(email)) {
                return member;
            }
        }
        return null;

    }


    // REQUIRES : member cannot be null
    // MODIFIES : this
    // EFFECTS : add a member to the Member list if the email not existed before
    public void addMember(String firstName, String lastName, String email,
                          String birthday, int points) {
        for (Member member : members) {
            if (member.getEmail().equals(email)) {
                return;
            }
        }
        Member member = new Member(firstName, lastName, email, birthday, points);
        members.add(member);
        Event event = new Event("Add New Member Last Name : " + lastName + "\n"
                    + "Email : " + email);
        EventLog.getInstance().logEvent(event);

    }




    // REQUIRES : member already be in the list
    // MODIFIES : this
    // EFFECTS : remove a member from the Member list according to lastname
    // and email
    public void removeMember(String lastName, String email) {
        for (Member member : members) {
            if (member.getLastName().equals(lastName)
                    && member.getEmail().equals(email)) {
                members.remove(member);
                Event event = new Event("Remove Member Last Name : " + lastName + "\n"
                        + "Email : " + email);
                EventLog.getInstance().logEvent(event);
                return;
            }
        }

    }


    // REQUIRES : points > 0
    // MODIFIES : this
    // EFFECTS: update the points according to lastName and email
    public void changePoints(String lastName, String email, int points) {
        for (Member member : members) {
            if (member.getLastName().equals(lastName)
                    && member.getEmail().equals(email)) {
                member.setPoints(points);
                return;
            }
        }
    }

    @Override
    // EFFECTS: returns string representation of this memberList
    public String toString() {
        String str = "";
        for (Member member :  members) {
            str += member.toString() + "\n";
        }
        return str;
    }

    // EFFECTS: Returns the number of items in the list
    public int size() {
        return members.size();
    }

    //@Override
    //EFFECTS: return this as JSON object
    // public JSONObject toJson() {
        //return null;
    //}

    // EFFECTS: returns member in this memberList as a JSON array
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Member member: members) {
            jsonArray.put(member.toJson());
        }
        return jsonArray;
    }

    @Override
    public Iterator<Member> iterator() {
        return members.iterator();
    }

    public void printLogs() {
        Iterator<Event> eventIterator = EventLog.getInstance().iterator();
        while (eventIterator.hasNext()) {
            System.out.println(eventIterator.next().toString());
        }
//        for (Event e : EventLog.getInstance()) {
//            System.out.println(e);
//        }

    }
}


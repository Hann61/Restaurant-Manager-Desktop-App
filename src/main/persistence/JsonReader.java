package persistence;


import model.MemberList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader that reads memberList from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS:constructs reader to read from the source file
    public JsonReader(String source)  {
        this.source = source;
    }


    // EFFECTS: reads memberList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MemberList read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        MemberList memberList = new MemberList();
        parseMemberList(memberList, jsonArray);
        return memberList;
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES:memberList
    // EFFECTS: parses member from JSON Array and adds them to memberList
    private void parseMemberList(MemberList memberList, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextMember = (JSONObject) json;
            addMember(memberList, nextMember);
        }
    }

    // MODIFIES:memberList
    // EFFECTS:parses member from JSON object and adds it to memberList
    private void addMember(MemberList memberList, JSONObject nextMember) {
        String firstName = nextMember.getString("first name");
        String lastName = nextMember.getString("last name");
        String email = nextMember.getString("email");
        String birthday = nextMember.getString("birthday");
        int points = nextMember.getInt("points");
        memberList.addMember(firstName,lastName,email,birthday,points);
    }
}

package persistence;

import model.Member;
import model.MemberList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MemberList memberList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyMemberList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMemberList.json");
        try {
            MemberList memberList = reader.read();
            assertEquals(memberList.toString(), "");
            assertEquals(0, memberList.size());
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMemberList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMemberList.json");
        try {
            MemberList memberList = reader.read();
            assertEquals(2, memberList.size());
            assertEquals("Member{firstName=Lei, lastName=Li,email=lilei@gmail.com, " +
                            "birthDay=2002-09-08, points=50}\n" +
                                 "Member{firstName=MeiMei, lastName=Han,email=hanmeimei@gmail.com, " +
                            "birthDay=2003-10-05, points=200}\n", memberList.toString());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

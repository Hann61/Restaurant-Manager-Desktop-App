package persistence;



import model.MemberList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MemberList memberList = new MemberList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMemberList() {
        try {
            MemberList memberList = new MemberList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMemberList.json");
            writer.open();
            writer.write(memberList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMemberList.json");
            memberList = reader.read();
            assertEquals(memberList.toString(),"");
            assertEquals(0, memberList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Lei", "Li","lilei@gmail.com",
                    "2002-09-08", 50);
            memberList.addMember("MeiMei", "Han","hanmeimei@gmail.com",
                    "2003-10-05",200);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMemberList.json");
            writer.open();
            writer.write(memberList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMemberList.json");
            memberList = reader.read();
            assertEquals(2, memberList.size());
            assertEquals(memberList.toString(), "Member{firstName=Lei, lastName=Li,email=lilei@gmail.com, " +
                    "birthDay=2002-09-08, points=50}\n" +
                    "Member{firstName=MeiMei, lastName=Han,email=hanmeimei@gmail.com, " +
                    "birthDay=2003-10-05, points=200}\n");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

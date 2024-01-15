package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberTest {
    private Member testMember;

    @BeforeEach
    public void runBefore(){testMember = new Member("Victor", "Sun","abcd@gmail",
            "1998-06-01", 50);}

    @Test
    public void testConstructor(){
        assertEquals("Victor", testMember.getFirstName());
        assertEquals("Sun", testMember.getLastName());
        assertEquals("abcd@gmail", testMember.getEmail());
        assertEquals("1998-06-01", testMember.getBirthDay());
        assertEquals(50, testMember.getPoints());
        String expected = "Member{firstName=Victor, lastName=Sun,email=abcd@gmail, birthDay=1998-06-01, points=50}";
        assertEquals(expected, testMember.toString());
    }

    @Test
    public void testSetter(){
        testMember.setFirstName("V");
        testMember.setLastName("L");
        testMember.setEmail("E");
        testMember.setBirthDay("1999-06-01");
        testMember.setPoints(100);

        assertEquals("V", testMember.getFirstName());
        assertEquals("L", testMember.getLastName());
        assertEquals("E", testMember.getEmail());
        assertEquals("1999-06-01", testMember.getBirthDay());
        assertEquals(100, testMember.getPoints());
    }

}

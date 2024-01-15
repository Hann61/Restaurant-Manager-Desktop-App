package persistence;

import model.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMember(String firstName, String lastName, String email,
                               String birthDay, int points, Member member) {
        assertEquals(firstName, member.getFirstName());
        assertEquals(lastName, member.getLastName());
        assertEquals(email, member.getEmail());
        assertEquals(birthDay, member.getBirthDay());
        assertEquals(points, member.getPoints());
    }

}

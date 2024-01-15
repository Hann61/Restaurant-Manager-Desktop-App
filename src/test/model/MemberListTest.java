package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

class MemberListTest {
    private MemberList testMemberList;

    @BeforeEach
    public void runBefore() {
        testMemberList = new MemberList();

    }

    @Test
    public void testConstructor() {
        assertEquals(0, testMemberList.size());
    }

    @Test
    public void testAddMember(){
        testMemberList.addMember("Victor", "Sun", "abcd@gmail",
                "1998-06-01", 50);
        testMemberList.addMember("Lei", "Li", "efgh@gamil",
                "1997-08-02", 100);
        testMemberList.addMember("Meimei", "Han", "hijk@gmail",
                "2000-03-05", 300);
        assertEquals(3, testMemberList.size());

        testMemberList.addMember("Victor", "Sun", "abcd@gmail",
                "1998-06-01", 50);
        assertEquals(3, testMemberList.size());

        testMemberList.addMember("Victor", "Sun", "BBB@gmail",
                "1998-06-01", 50);
        assertEquals(4, testMemberList.size());

        testMemberList.addMember("Victor", "Xu", "AAA@gmail",
                "1998-06-01", 50);
        assertEquals(5, testMemberList.size());
    }

    @Test
    public void testRemoveMember(){
        testMemberList.addMember("Victor", "Sun", "abcd@gmail",
                "1998-06-01", 50);
        testMemberList.addMember("Lei", "Li", "efgh@gamil",
                "1997-08-02", 100);
        testMemberList.addMember("Meimei", "Han", "hijk@gmail",
                "2000-03-05", 300);
        testMemberList.removeMember("Sun", "abcd@gmail");
        assertEquals(2, testMemberList.size());


        testMemberList.removeMember("AAA", "BBB@gmail");
        assertEquals(2, testMemberList.size());

        testMemberList.removeMember("Xu", "efgh@gamil");
        assertEquals(2, testMemberList.size());

        testMemberList.removeMember("Li", "BBB@gmail");
        assertEquals(2, testMemberList.size());
    }

    @Test
    public void testChangePoints(){
        testMemberList.addMember("Victor", "Sun", "abcd@gmail",
                "1998-06-01", 50);
        testMemberList.changePoints("Sun", "abcd@gmail", 100);
        assertEquals("Member{firstName=Victor, lastName=Sun,email=abcd@gmail, birthDay=1998-06-01, points=100}\n",
                testMemberList.toString());

        testMemberList.changePoints("SHHun", "abcd@gmail", 100);
        assertEquals("Member{firstName=Victor, lastName=Sun,email=abcd@gmail, birthDay=1998-06-01, points=100}\n",
                testMemberList.toString());

        testMemberList.changePoints("Sun", "BBB@gmail", 100);
        assertEquals("Member{firstName=Victor, lastName=Sun,email=abcd@gmail, birthDay=1998-06-01, points=100}\n",
                testMemberList.toString());

        testMemberList.changePoints("SHHun", "BBB@gmail", 100);
        assertEquals("Member{firstName=Victor, lastName=Sun,email=abcd@gmail, birthDay=1998-06-01, points=100}\n",
                testMemberList.toString());
    }

    @Test
    public void testGetMember() {
        testMemberList.addMember("Victor", "Sun", "abcd@gmail",
                "1998-06-01", 50);
        Member member1 = testMemberList.getMember("Sun", "jjjjjj");
        assertEquals(null, member1);

        Member member2 = testMemberList.getMember("Sun", "abcd@gmail");
        assertEquals("Member{firstName=Victor, lastName=Sun,email=abcd@gmail, birthDay=1998-06-01, points=50}",
                member2.toString());

        Member member3 = testMemberList.getMember("Bai", "abcd@gmail");
        assertEquals(null, member3);

        Member member4 = testMemberList.getMember("Bai", "jjjj@gmail");
        assertEquals(null, member4);

    }

}

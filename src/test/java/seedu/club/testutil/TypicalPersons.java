package seedu.club.testutil;

import static seedu.club.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.club.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_GROUP_AMY;
import static seedu.club.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.club.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.club.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.club.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.club.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.club.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.club.model.ClubBook;
import seedu.club.model.Member.Member;
import seedu.club.model.Member.exceptions.DuplicatePersonException;

/**
 * A utility class containing a list of {@code Member} objects
 * to be used in tests.
 */
public class TypicalPersons {

    public static final Member ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("85355255")
            .withMatricNumber("A9210701B")
            .withGroup("logistics")
            .withTags("friends").build();
    public static final Member BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withMatricNumber("A8389539B")
            .withGroup("pr")
            .withTags("owesMoney", "friends").build();
    public static final Member CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withMatricNumber("A6076201A")
            .withGroup("marketing").build();
    public static final Member DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withMatricNumber("A2719059H")
            .withGroup("publicity").build();
    public static final Member ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withMatricNumber("A1932279G")
            .withGroup("marketing").build();
    public static final Member FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withMatricNumber("A9662042H")
            .withGroup("operations").build();
    public static final Member GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withMatricNumber("A2836750A")
            .withGroup("legal").build();

    // Manually added
    public static final Member HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withMatricNumber("a9123096J")
            .withGroup("publicity")
            .build();
    public static final Member IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withMatricNumber("a9239483F")
            .withGroup("logistics")
            .build();

    // Manually added - Member's details found in {@code CommandTestUtil}
    public static final Member AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withMatricNumber(VALID_MATRIC_NUMBER_AMY)
            .withGroup(VALID_GROUP_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Member BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withMatricNumber(VALID_MATRIC_NUMBER_BOB)
            .withGroup(VALID_GROUP_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code ClubBook} with all the typical persons.
     */
    public static ClubBook getTypicalClubBook() {
        ClubBook ab = new ClubBook();
        for (Member member : getTypicalPersons()) {
            try {
                ab.addPerson(member);
            } catch (DuplicatePersonException e) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

    public static List<Member> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

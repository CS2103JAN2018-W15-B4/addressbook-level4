package seedu.club.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.club.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.club.model.group.Group;
import seedu.club.model.Member.Email;
import seedu.club.model.Member.MatricNumber;
import seedu.club.model.Member.Name;
import seedu.club.model.Member.Member;
import seedu.club.model.Member.Phone;
import seedu.club.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code Member}'s details
     */
    public EditPersonDescriptorBuilder(Member member) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(member.getName());
        descriptor.setPhone(member.getPhone());
        descriptor.setEmail(member.getEmail());
        descriptor.setMatricNumber(member.getMatricNumber());
        descriptor.setGroup(member.getGroup());
        descriptor.setTags(member.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code MatricNumber} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMatricNumber(String matricNumber) {
        descriptor.setMatricNumber(new MatricNumber(matricNumber));
        return this;
    }

    /**
     * Sets the {@code Group} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGroup(String group) {
        descriptor.setGroup(new Group(group));
        return this;
    }
    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}

package seedu.club.logic.parser;
//@@author MuhdNurKamal

import static seedu.club.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.club.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.club.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.club.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.club.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.club.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.club.logic.commands.FindCommand;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.member.FieldContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Prefix[] FINDABLE_PREFIXES = {PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE,
        PREFIX_MATRIC_NUMBER, PREFIX_GROUP, PREFIX_TAG};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        for (Prefix prefix : FINDABLE_PREFIXES) {
            int prefixLength = prefix.toString().length();
            if (trimmedArgs.length() >= prefixLength && trimmedArgs.substring(0, prefixLength)
                    .equals(prefix.toString())) {
                String[] findArgs = trimmedArgs.substring(prefixLength, trimmedArgs.length())
                        .trim().split("\\s+");
                return new FindCommand(new FieldContainsKeywordsPredicate(
                        Arrays.asList(findArgs), prefix));
            }
        }

        return new FindCommand(new FieldContainsKeywordsPredicate(
                Arrays.asList(trimmedArgs.split("\\s+")), null));
    }
}

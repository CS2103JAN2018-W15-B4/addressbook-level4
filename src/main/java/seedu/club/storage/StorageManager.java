package seedu.club.storage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.club.commons.core.ComponentManager;
import seedu.club.commons.core.LogsCenter;
import seedu.club.commons.events.model.ClubBookChangedEvent;
import seedu.club.commons.events.model.ProfilePhotoChangedEvent;
import seedu.club.commons.events.storage.DataSavingExceptionEvent;
import seedu.club.commons.exceptions.DataConversionException;
import seedu.club.commons.exceptions.PhotoException;
import seedu.club.model.ReadOnlyClubBook;
import seedu.club.model.UserPrefs;

/**
 * Manages storage of ClubBook data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClubBookStorage clubBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private ProfilePhotoStorage profilePhotoStorage;


    public StorageManager(ClubBookStorage clubBookStorage, UserPrefsStorage userPrefsStorage,
                          ProfilePhotoStorage profilePhotoStorage) {
        super();
        this.clubBookStorage = clubBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.profilePhotoStorage = profilePhotoStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public String getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ClubBook methods ==============================

    @Override
    public String getClubBookFilePath() {
        return clubBookStorage.getClubBookFilePath();
    }

    @Override
    public Optional<ReadOnlyClubBook> readClubBook() throws DataConversionException, IOException {
        return readClubBook(clubBookStorage.getClubBookFilePath());
    }

    @Override
    public Optional<ReadOnlyClubBook> readClubBook(String filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clubBookStorage.readClubBook(filePath);
    }

    @Override
    public void saveClubBook(ReadOnlyClubBook clubBook) throws IOException {
        saveClubBook(clubBook, clubBookStorage.getClubBookFilePath());
    }

    @Override
    public void saveClubBook(ReadOnlyClubBook clubBook, String filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clubBookStorage.saveClubBook(clubBook, filePath);
    }


    @Override
    @Subscribe
    public void handleClubBookChangedEvent(ClubBookChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveClubBook(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    //@@author amrut-prabhu
    @Override
    public void readOriginalPhotoFile(String originalPath, String newPhotoName) throws PhotoException {
        logger.fine("Attempting to read photo from file: " + originalPath);
        profilePhotoStorage.readOriginalPhotoFile(originalPath, newPhotoName);
    }

    @Override
    public void createPhotoFileCopy(BufferedImage image, File newPath) throws IOException {
        logger.fine("Attempting to write photo to file: " + newPath);
        profilePhotoStorage.createPhotoFileCopy(image, newPath);
    }

    @Override
    @Subscribe
    public void handleProfilePictureChangedEvent(ProfilePhotoChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Profile photo changed, copying file"));
        try {
            readOriginalPhotoFile(event.originalPhotoPath, event.newFileName);
        } catch (PhotoException pe) {
            raise(new DataSavingExceptionEvent(pe));
        }
    }

}

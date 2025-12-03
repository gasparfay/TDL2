package dao;

import java.util.List;
import model.*;

public interface ProfileDAO {
    boolean loadProfile(Profile profile);
    List<Profile> findAll();
    boolean associateWithAccount(Profile profile, Account account);
    List<Profile> findByAccount(Account account);
}

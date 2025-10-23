package model;

import java.util.Comparator;

public class AccountComparatorEmail implements Comparator<Account>{

    @Override
    public int compare(Account a1, Account a2) {
        return a1.getEmail().compareTo(a2.getEmail());
    }
}
 
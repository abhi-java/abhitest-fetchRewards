package com.abhi.mail.mailcounter.model;

import java.io.Serializable;
import java.util.List;

public class EmailWrapper implements Serializable {

    private List<String> emails;

    /**
     * Getter
     * @return
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * Setter
     * @param emails
     */
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }


}

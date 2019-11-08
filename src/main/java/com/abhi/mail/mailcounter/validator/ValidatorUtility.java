package com.abhi.mail.mailcounter.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// final, because it's not supposed to be subclassed
@Component
public final class ValidatorUtility {
    private static final Logger logger = LoggerFactory.getLogger(ValidatorUtility.class);

    private ValidatorUtility() {
    }

    /**
     * Utility method to return the number of unique email address
     * @param listOfUser
     * @return
     */
    public static int validEmailAddressCount(List<String> listOfUser){
        Set<String> emailSet = new HashSet<String>();
        if(listOfUser == null){
            return emailSet.size();
        }
        for(String email: listOfUser){
            email = convertToValidEmail(email);
            if(!emailSet.contains(email)){
              emailSet.add(email);
            }
        }
        return emailSet.size();
    }

    /**
     * Utility method to convert email address to valid one, returns null , if email id is invalid
     * @param emailAddress
     * @return
     */
    public static String convertToValidEmail(String emailAddress){
        if(emailAddress == null || emailAddress.length() == 0){
            return null;
        }
        // As we don't much about email id restrictions , so iam going with pattern of only '@' in email id.
        // in case it has more then '@', i will consider last occurence of it as a part which divides domain from user name
        // and all previous occurence of '@' will be considered as part of userName
        // we will consider email id as valid if contains atleast '@',
        // Same validation is used by EmailValidator class of apache Commons'lang
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(regex);
        if(!matcher.matches()){
           return null;
        }
        String userName = getUserAndDomainName(emailAddress)[0];
        String domainName = getUserAndDomainName(emailAddress)[1];
        //removing all dots from User name
        userName = userName.replace(".","");
        //extracting user name from 0 till first occurence of "+";
        if(userName.contains("+")){
            userName = userName.substring(0,userName.indexOf("+"));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(userName);
        stringBuilder.append("@");
        //converting domain name to lower case, it will help us further when we do comparison of email address
        //as generally user name is case sensitive while domain name is case insensitive. So while generating
        // valid email address we will keep domain name in lower case. This will help us in checking the uniqueness
        //based upon user name only which is case sensitive.
        stringBuilder.append(domainName.toLowerCase());
        return stringBuilder.toString();
    }

    /**
     * Utility method to return user name and domain name
     * @param emailAddress
     * @return
     */
    public static String[] getUserAndDomainName(String emailAddress){
        String domainName = emailAddress.substring(emailAddress.lastIndexOf("@")+1,emailAddress.length());
        String userName = emailAddress.substring(0,emailAddress.lastIndexOf("@"));
        String[] result = new String[]{userName,domainName};
        return result;
    }
}

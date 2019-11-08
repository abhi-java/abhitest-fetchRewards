package com.abhi.mail.mailcounter.validator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


public class ValidatorUtilityTest {
    /**
     * Test case for getting correct User name
     */
    @Test
    public void testGetUserNameAndDomain(){
        String userName = "abcd";
        String domain = "gmail.com";
        String emailAddress = "abcd@gmail.com";
        String[] userAndDomain = ValidatorUtility.getUserAndDomainName(emailAddress);
        Assert.assertEquals(userName,userAndDomain[0]);
        Assert.assertEquals(domain,userAndDomain[1]);
    }

    /**
     * Test case for having +++ in user name
     */
    @Test
    public void testConvertToValidEmailWithAdditionSignInUserName(){
        String originalEmail = "abcd++++dfer+233@gmail.com";
        String expectedEmail = "abcd@gmail.com";
        Assert.assertEquals(expectedEmail,ValidatorUtility
                .convertToValidEmail(originalEmail));
    }

    /**
     * Test case for having . in user name
     */
    @Test
    public void testConvertToValidEmailWithDotsInUserName(){
        String originalEmail = "abcd......@gmail.com";
        String expectedEmail = "abcd@gmail.com";
        Assert.assertEquals(expectedEmail,ValidatorUtility
                .convertToValidEmail(originalEmail));
    }

    /**
     * Test case for empty list of email ids
     */
    @Test
    public void testValidEmailAddressCountWithEmptyList(){
        List<String> emailIds = Arrays.asList();
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, ValidatorUtility
                .validEmailAddressCount(emailIds));

    }
    /**
     * Test case for null list of email ids
     */
    @Test
    public void testValidEmailAddressCountWithNullInput(){
        List<String> emailIds = null;
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, ValidatorUtility
                .validEmailAddressCount(emailIds));
    }

    /**
     * Test case for valid list of email ids
     */
    @Test
    public void testValidEmailAddressCountWithValidInputs(){
        List<String> emailIds = Arrays.asList
                ("test.email@gmail.com", "test.email+spam@gmail.com","testemail@gmail.com");
        int expectedResult = 1;
        Assert.assertEquals(expectedResult, ValidatorUtility
                .validEmailAddressCount(emailIds));
    }
}

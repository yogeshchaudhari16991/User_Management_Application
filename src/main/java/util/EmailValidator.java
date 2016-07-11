package util;

//////////////////////////////////////////////////////////////////////////////
// EmailValidator.java - Utility file - Validates Email                     //
// Ver 1.0                                                                  //
// Application: User Management Application                                 //
// Language:    Java, ver 8, IntelliJ IDEA 2016.1.3                         //
// Platform:    Dell Inspiron 14 5000Series, Core-i5, Windows 10            //
// Author:      Yogesh Chaudhari, Intern, Syracuse University               //
//              (315) 4809210, yogeshchaudhari16991@gmail.com               //
//////////////////////////////////////////////////////////////////////////////
/*
 * File Operations:
 * -------------------
 *
 * Defines Regex pattern for email string matching - Used by User object
 * implements validate() method to check if email string is valid or not
 *
 */
/*
 * Maintenance:
 * ------------
 * Required Files:
 *      java.util.regex
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   Java Regex : https://docs.oracle.com/javase/tutorial/essential/regex/
 * ----------
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// class to validate email address
public class EmailValidator {

    // private Pattern and Matcher regex objects
    private Pattern pattern;
    private Matcher matcher;

    // email_pattern matching string
    // w => [A-Za-z_0-9]
    private static final String EMAIL_PATTERN =
            "^[\\w]+(\\.[\\w]+)*@"
                    + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // Public constructor
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    // method to validate email string
    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}

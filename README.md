//////////////////////////////////////////////////////////////////////////////
// README.md - READ me file for User Management Application                 //
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
 * This file acts as Read Me file for User Management Application
 *
 * This file gives information about following :
 * 1.  Author of application
 * 2.  File operations
 * 3.  Build process for application
 * 4.  All reference used for development
 * 5.  Maintenance history
 * 6.  Problem statement
 * 7.  Assumptions
 * 8.  Language, Libraries and Frameworks Used
 * 9.  Instructions to Run Tests - UserEngineTest.java
 * 10. Instructions to Run Application - App.java
 *
 */
/*
 * Maintenance:
 * ------------
 *
 * Build Process:
 *      DevEnv : mvn install
 *      DepEnv : mvn clean deploy
 *
 * Reference:   Spark Documentation : http://sparkjava.com/documentation.html
 * ----------   Spark Tutorials : https://sparktutorials.github.io/
 *              Google Gson User-Guide : https://sites.google.com/site/gson/gson-user-guide
 *              Building Simple REST API with Java Spark : https://dzone.com/articles/building-simple-restful-api
 *              Java MongoDB Driver Documentation : https://docs.mongodb.com/ecosystem/drivers/java/
 *              Building Simple REST API with Java Spark : https://dzone.com/articles/building-simple-restful-api
 *
 *
 * Maintenance History:
 * --------------------
 * ver 1.0 : 10 Jul 2016
 * - first release
 * ver 1.0.1 : 11 Jul 2016
 * - Interfaces and Factories added
 *
 * Next Release:
 * -------------
 *      Add few more test-cases
 *
 *
 * Problem Statement:
 * ------------------
 *      Create RESTful webservice for User Management Application using
 *          - Spark java
 *          - MongoDB
 *
 * Assumptions Made:
 * -----------------
 *      1. User has the following properties
 *           {
 *             "id":"1630215c-2608-44b9-aad4-9d56d8aafd4c",
 *             "firstName":"Dorris",
 *             "lastName":"Keeling",
 *             "email":"Darby_Leffler68@gmail.com",
 *             "address":{
 *                "street":"193 Talon Valley",
 *                "city":"South Tate furt",
 *                "zip":"47069",
 *                "state":"IA",
 *                "country":"US"
 *             },
 *             "dateCreated":"2016-03-15T07:02:40.896Z",
 *             "company":{
 *                "name":"Denesik Group",
 *                "website":"http://jodie.org"
 *             },
 *             "profilePic":"http://lorempixel.com/640/480/people"
 *          }
 *
 *      2. Data Store - Mongo DB
 *      3. User's ID uniquely identifies user
 *
 * Language Used: Java 8
 * --------------
 *
 * Libraries And Frameworks Used:   1. Spark Java - ver 2.3
 * ------------------------------   2. Java MongoDB driver - ver 2.11.3
 *                                  3. Google Gson - ver 2.7
 *                                  4. JUnit - ver 4.12
 *
 *
 * Instructions to Run UserEngineTest.java:     1. Run MongoDB
 * ----------------------------------------     2. Run UserEngineTest.java
 *
 *
 * Instructions to Run App.java:    1. Run MongoDB
 * -----------------------------    2. Run App.java
 *
 *
 */
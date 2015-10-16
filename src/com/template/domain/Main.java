package com.template.domain;

import com.template.domain.database.Database;
import com.template.domain.database.table.ExampleUserTable;
import org.hibernate.Session;

public class Main {

    public static void main(String[] args){

        creating("exampleUsername", "examplePassword", "example@email.com");

        updating("newExample@email.com");

        String email = getting();

    }


    public static String getting(){
        Session session = Database.getDatabaseInstance().getSessionFactory().openSession(); // Open a session with the database

        session.beginTransaction(); // Start a new transaction

        ExampleUserTable existingUser = session.get(ExampleUserTable.class, "Example"); // Get the object of your desire by its ID

        String email = existingUser.getEmail(); // Get the value(s) you want from that object (maybe the object it self)

        session.close(); // Close the transaction

        return email; // Return the object
    }

    public static void updating(String email){
        Session session = Database.getDatabaseInstance().getSessionFactory().openSession(); // Open a session with the database

        session.beginTransaction(); // Start a new transaction

        ExampleUserTable existingUser = session.get(ExampleUserTable.class, "Example"); // Create the object of your desire

        existingUser.setEmail(email); // update the field in the object

        session.update(email); // update the object to in the transaction

        session.getTransaction().commit(); // Commit the transaction to the database

        session.close(); // Close the used session for this transaction (not needed, but recommended)
    }

    public static void creating(String username, String password, String email){
        Session session = Database.getDatabaseInstance().getSessionFactory().openSession(); // Open a session with the database

        session.beginTransaction(); // Start a new transaction

        ExampleUserTable newUser = new ExampleUserTable(); // Create the object of your desire

        newUser.setUsername(username); // Set all the fields that are not nullable
        newUser.setPassword(password); // Set all the fields that are not nullable
        newUser.setEmail(email); // Set also those field you want to have filled in...
        // ect...

        session.save(newUser); // Save the object to the transaction

        session.getTransaction().commit(); // Commit the transaction to the database

        session.close(); // Close the used session for this transaction (not needed, but recommended)
    }



}

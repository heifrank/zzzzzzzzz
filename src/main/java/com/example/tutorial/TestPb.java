package com.example.tutorial;

import com.example.tutorial.AddressBookProtos.*;

/**
 * Created by heifrank on 16/6/2.
 */
public class TestPb {

    public static void main(String[] args){
        AddressBook.Builder builder = AddressBook.newBuilder();
        Person john = Person.newBuilder().setId(123456).setName("John Doe").setEmail("heifrank27@gmai.com")
                                    .addPhone(Person.PhoneNumber.newBuilder().setNumber("4627979").setType(Person.PhoneType.HOME))
                                    .addPhone(Person.PhoneNumber.newBuilder().setNumber("wenfwen").setType(Person.PhoneType.MOBILE))
                                    .build();
        String ss = john.toString();
        System.out.println(ss);
    }
}

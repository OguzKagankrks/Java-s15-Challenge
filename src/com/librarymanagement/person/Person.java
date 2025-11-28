package com.librarymanagement.person;

public abstract class Person {

    private String name;
    private String password;

     public Person(String name,String password){
         this.name = name;
         this.password = password;

     }

    public abstract String whoYouAre();

     public String getName(){
         return name;
     }

     public String getPassword(){
         return password;
     }

     public void setName(String name){
         this.name = name;
     }

     public void setPassword(String password){
         this.password = password;
     }




}


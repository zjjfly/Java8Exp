package com.cn.zjj;

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

class Person {
    String firstName;
    String lastName;
    Person() {}
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
     static String valueOf(Person p) {
		return p.firstName+"・"+p.lastName;
	}  
     @Override
    public String toString() {
 		return this.firstName+"・"+this.lastName;
    }
}
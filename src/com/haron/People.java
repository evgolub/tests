package com.haron;

import java.io.Serializable;
import java.util.Comparator;

public class People implements Serializable {
    String name;
    int age;
    String sex;
    String city;
    String social;
    String car;

    People(String n, int a, String s, String c, String so, String ca){
        name = n;
        age = a;
        sex = s;
        city = c;
        social = so;
        car = ca;
    }
    public String getName(){ return name; }
    public int getAge(){ return age; }
    public String getSex(){ return sex; }
    public String getCity(){ return city; }
    public String getSocial(){ return social; }
    public String getCar(){ return car; }

    public String toString() {
        return name +" "+ age +" "+ sex +" "+ city +" "+ social +" "+ car;
    }

    public static final Comparator<People> COMPARE_BY_AGE = new Comparator<People>() {//Сортировка по возрасту
        public int compare(People lhs, People rhs) {
            return lhs.getAge() - rhs.getAge();
        }
    };
    public static final Comparator<People> COMPARE_BY_NAME = new Comparator<People>() {//Соратировка по Имени
        public int compare(People lhs, People rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    };
}

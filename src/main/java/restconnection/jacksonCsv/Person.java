package restconnection.jacksonCsv;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"name","surname","age","numbers"})
public class Person {
    private String name;
    private String surname;
    private String age;
    private List<Integer> numbers;

    public Person(String name, String surname, String age,List<Integer> numbers) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age='" + age + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}

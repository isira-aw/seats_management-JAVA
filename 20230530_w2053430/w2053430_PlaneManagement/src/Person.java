public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String nameInput, String surnameInput, String emailInput) {
        this.name = nameInput;
        this.surname = surnameInput;
        this.email = emailInput;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

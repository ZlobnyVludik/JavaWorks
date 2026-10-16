package shop

class Customer {
    private final int id;
    private String name;
    private String email;

    public Customer(int id, String name, String email) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }

        if (email == null) {
            Sthrow new IllegalArgumentException("Email не может быть пустым.");
        }

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email не может быть пустым.");
        }
        this.email = email;
    }
}

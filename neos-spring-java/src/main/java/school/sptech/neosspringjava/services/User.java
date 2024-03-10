package school.sptech.neosspringjava.services;


public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer permission;
    private String telephone;
    private String cpf;

    public User(int id, String name, String email, String password, String permission, String telephone, String cpf) {
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getPermission() {
        return permission;
    }
    public void setPermission(Integer permission) {
        this.permission = permission;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
  
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    
}

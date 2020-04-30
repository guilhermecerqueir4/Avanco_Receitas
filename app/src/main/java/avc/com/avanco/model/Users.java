package avc.com.avanco.model;

public class Users
{
    private String nomeUsuario, phone, senha, image, address;


    public Users()
    {

    }

    public Users(String nomeUsuario, String phone, String senha, String image, String address) {
        this.nomeUsuario = nomeUsuario;
        this.phone = phone;
        this.senha = senha;
        this.image = image;
        this.address = address;
    }


    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

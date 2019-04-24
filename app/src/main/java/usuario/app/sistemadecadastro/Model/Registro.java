package usuario.app.sistemadecadastro.Model;

//POJO
public class Registro{
    private int ID;
    private String nome, endereco, telefone;

    public Registro(){
    }

    public Registro(String nome, String endereco, String telefone)
    {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    //GETTER E SETTER
    public int getId(){return ID;}
    public String getNome(){
        return nome;
    }
    public String getEndereco(){
        return endereco;
    }
    public String getTelefone(){
        return telefone;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

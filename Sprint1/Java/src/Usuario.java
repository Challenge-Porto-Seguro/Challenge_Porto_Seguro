import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Endereco endereco;
    private List<Automovel> automoveis = new ArrayList<>();
    private List<Orcamento> orcamentos = new ArrayList<>();

    public Usuario(String nome, String cpf, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addAutomovel(Automovel automovel){
        this.automoveis.add(automovel);
    }

    public void removeAutomovel(Automovel automovel){
        this.automoveis.remove(automovel);
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void addOrcamento(Orcamento orcamento){
        orcamentos.add(orcamento);
    }

    public void removeOrcamento(Orcamento orcamento){
        orcamentos.remove(orcamento);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    private boolean verificaSeCpfEValido(String cpf){
        return true;
    }

    private void mudarStatusOrcamento(Orcamento orcamento){
        orcamento.setStatus(StatusOrcamento.INATIVO);
    }
}

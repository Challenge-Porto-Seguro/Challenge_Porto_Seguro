public class Autenticacao {

    private String email;
    private String senha;

    public Autenticacao(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public boolean autenticar(Usuario usuario) {
        return this.email.equals(usuario.getEmail()) && this.senha.equals(usuario.getSenha());
    }
}

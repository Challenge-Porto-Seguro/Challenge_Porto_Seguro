import java.util.Date;

public class Diagnostico {

    private int id;
    private String descricao;
    private Date data;
    private VerificaDiagnostico verificador;

    public Diagnostico(String descricao) {
        id += 1;
        this.descricao = descricao;
        this.data = new Date();
        this.verificador = VerificaDiagnostico.DIAGNOSTICO_NAO_RESOLVIDO;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData(){
        return data;
    }

    public VerificaDiagnostico getVerificador() {
        return verificador;
    }

    public void setVerificador(VerificaDiagnostico verificador) {
        this.verificador = verificador;
    }
}

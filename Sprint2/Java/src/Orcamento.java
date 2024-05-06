import java.util.ArrayList;
import java.util.List;

public class Orcamento {

    private double valorTotal;
    private List<Pedido> pedidos = new ArrayList<>();
    private StatusOrcamento statusOrcamento;

    public Orcamento() {
        this.statusOrcamento = StatusOrcamento.ATIVO;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public void setValorTotal() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.getValor();
        }

        this.valorTotal = total;
    }

    public StatusOrcamento getStatus() {
        return statusOrcamento;
    }

    public void setStatus(StatusOrcamento statusOrcamento) {
        this.statusOrcamento = statusOrcamento;
    }
}

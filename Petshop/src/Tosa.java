public class Tosa extends Servico{

    private String descricao;
    private Double preco;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String descricao() {
        return descricao;
    }

    @Override
    public Double preco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Tosa{" +
                "descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}';
    }
}

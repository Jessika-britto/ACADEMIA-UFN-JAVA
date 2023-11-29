package br.com.cassiogamarra.orcamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="ORCAMENTOS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orcamento {

	@Id
    @Column(name="ID_ORCAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="NOME_CLIENTE", nullable = false)
    private String nomeCliente;

    @Column(name="NUM_CONVIDADOS", nullable = false)
    private int numConvidados;
    
    @Column(name="VL_COVIDADOS", nullable = false)
    private double vlConvidados;
    
    @Column(name="IND_SOBREMESA", nullable = false)
    private Boolean indSobremesa;

    @Column(name="TX_SOBREMESA", nullable = false)
    private double txSobremesa;

    @Column(name="QTD_GARCONS", nullable = false)
    private int qtdGarcons;

    @Column(name="TX_GARCONS", nullable = false)
    private double txGarcons;
 
    @Column(name="VL_TOTAL", nullable = false)
    private double vlTotal;
}

package br.com.cassiogamarra.orcamentos.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.cassiogamarra.orcamentos.entities.Garcons;
import br.com.cassiogamarra.orcamentos.entities.Orcamento;
import br.com.cassiogamarra.orcamentos.repositories.OrcamentoRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ManagedBean(name="orcamentoBean")
@RequestScoped
@Builder
@Data  
@NoArgsConstructor
@AllArgsConstructor
public class OrcamentoBean {
	private static final double PRECO_PRATO_QUENTE = 22.90;
	private static final double CACHE_GARCOM = 250.00;
	private static final int NUM_CONVIDADOS_X_GARCONS = 15;
  
	OrcamentoRepository orcamentoRepository = new OrcamentoRepository();
	
    private int id;
    private String nomeCliente;
    private int numConvidados;
    private double vlConvidados;
    private Boolean indSobremesa;
    private double txSobremesa;
    private int qtdGarcons;
    private double txGarcons;
    private double vlTotal;
    
    @PostConstruct
    public void init() {
    	orcamentoRepository.setup();
    }
    
    public String salvar(OrcamentoBean orcamentoBean) {
    	Orcamento orcamento = new Orcamento();
    	orcamento.setNomeCliente(orcamentoBean.getNomeCliente());
    	orcamento.setNumConvidados(orcamentoBean.getNumConvidados());
    	orcamento.setIndSobremesa(orcamentoBean.getIndSobremesa());
    	
    	double vlTotalConvidados = orcamentoBean.getNumConvidados() * PRECO_PRATO_QUENTE;
    	orcamento.setVlConvidados(vlTotalConvidados);
    	
    	double txTotalSobremesa = orcamento.getIndSobremesa() ? vlTotalConvidados * 0.10 : 0;
    	orcamento.setTxSobremesa(txTotalSobremesa);
    	 
    	if (orcamento.getNumConvidados() > 15) {
    		Garcons garcons = calcularTaxaGarcons(numConvidados);
    		orcamento.setQtdGarcons(garcons.getNumGarcons());
    		orcamento.setTxGarcons(garcons.getTxGargons());
    	}
    	
    	double vlTotal = orcamento.getVlConvidados() + orcamento.getTxSobremesa() + orcamento.getTxGarcons();
    	orcamento.setVlTotal(vlTotal);
    	
    	return orcamentoRepository.salvar(orcamento);
    }
    
    public Orcamento buscar(String nomeCliente) {
    	return orcamentoRepository.buscarPorCliente(nomeCliente);
    }
    
    private Garcons calcularTaxaGarcons(int numConvidados) {
    	Garcons garcons = new Garcons();
    	if (numConvidados < NUM_CONVIDADOS_X_GARCONS) {
    		return garcons;
    	}  
    	
    	int qtdGarcons = numConvidados / NUM_CONVIDADOS_X_GARCONS;
    	double txGarcons = qtdGarcons * CACHE_GARCOM;
    	garcons.setNumGarcons(qtdGarcons);
    	garcons.setTxGargons(txGarcons);
    	return garcons;
    }
}

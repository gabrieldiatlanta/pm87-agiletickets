package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		preco = sessao.getPreco();
		
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos
			if(sessao.getPorcentagemReservados() <= 0.05) { 
				preco = sessao.calculaPreco(preco, 0.10);
			} 
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			if(sessao.getPorcentagemReservados() <= 0.50) { 
				preco = sessao.calculaPreco(preco, 0.20);
			} 
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = sessao.calculaPreco(preco, 0.10);
			}
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(sessao.getPorcentagemReservados() <= 0.50) { 
				preco = sessao.calculaPreco(preco, 0.20);
			} 

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = sessao.calculaPreco(preco, 0.10);;
			}
		}  

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}



}
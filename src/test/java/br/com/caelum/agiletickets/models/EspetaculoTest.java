package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	@Test
	
	public void deveCriarUmaUnicaSessaoQuandoInicioIgualAFim(){
		//arrange
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2017,2,9);
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime(21,0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		//act
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		//assert
		Assert.assertNotNull("A lista de sessoes nao deve ser nula", sessoes);
		Assert.assertEquals(1, sessoes.size());
		
		Sessao unica = sessoes.get(0);
		Assert.assertEquals(espetaculo,unica.getEspetaculo());
		Assert.assertEquals("09/02/17", unica.getDia());
		Assert.assertEquals("21:00", unica.getHora());
		
		
	}
	
	@Test
	public void deveCriarSessoesQuandoOFimForMaiorQueOInicio(){
		//arrange
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2017,2,9);
		LocalDate fim = new LocalDate(2017,2,10);
		LocalTime horario = new LocalTime(21,0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		//act
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		//assert
		Assert.assertEquals(2, sessoes.size());
		
		Sessao unica = sessoes.get(0);
		Assert.assertEquals(espetaculo,unica.getEspetaculo());
		Assert.assertEquals("09/02/17", unica.getDia());
		Assert.assertEquals("21:00", unica.getHora());
		
		unica = sessoes.get(1);
		Assert.assertEquals(espetaculo,unica.getEspetaculo());
		Assert.assertEquals("10/02/17", unica.getDia());
		Assert.assertEquals("21:00", unica.getHora());
	}
	
	@Test
	public void deveCriarSessaoQuandoOPeriodoForSemanal(){
		//arrange
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2017,2,9);
		LocalDate fim = new LocalDate(2017,2,16);
		LocalTime horario = new LocalTime(21,0);
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		//act
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		//assert
		Assert.assertEquals(2, sessoes.size());
		
		Sessao unica = sessoes.get(0);
		Assert.assertEquals(espetaculo,unica.getEspetaculo());
		Assert.assertEquals("09/02/17", unica.getDia());
		Assert.assertEquals("21:00", unica.getHora());
		
		unica = sessoes.get(1);
		Assert.assertEquals(espetaculo,unica.getEspetaculo());
		Assert.assertEquals("16/02/17", unica.getDia());
		Assert.assertEquals("21:00", unica.getHora());
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}

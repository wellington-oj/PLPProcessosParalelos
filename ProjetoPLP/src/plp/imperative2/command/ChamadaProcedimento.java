package plp.imperative2.command;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.command.Comando;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.imperative1.memory.ListaValor;
import plp.imperative2.declaration.ListaDeclaracaoParametro;
import plp.imperative2.memory.AmbienteCompilacaoImperativa2;
import plp.imperative2.memory.AmbienteExecucaoImperativa2;
import plp.imperative2.memory.Procedimento;

public class ChamadaProcedimento implements Comando {

	private Id nomeProcedimento;

	private ListaExpressao parametrosReais;

	public ChamadaProcedimento(Id nomeProcedimento,
			ListaExpressao parametrosReais) {
		this.nomeProcedimento = nomeProcedimento;
		this.parametrosReais = parametrosReais;
	}

	public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa amb)
			throws IdentificadorNaoDeclaradoException,
			IdentificadorJaDeclaradoException, EntradaVaziaException {
		AmbienteExecucaoImperativa2 ambiente = (AmbienteExecucaoImperativa2) amb;
		Procedimento procedimento = ambiente.getProcedimento(nomeProcedimento);
		ambiente.incrementa();
		ListaDeclaracaoParametro parametrosFormais = procedimento
				.getParametrosFormais();
		AmbienteExecucaoImperativa2 aux = bindParameters(ambiente,
				parametrosFormais);
		aux = (AmbienteExecucaoImperativa2) procedimento.getComando().executar(
				aux);
		aux.restaura();
		return aux;
		/*
		 * o incrementa e o restaura neste ponto servem para criar as variveis
		 * que serao utilizadas pela execucao do procedimento
		 */
	}

	/**
	 * insere no contexto o resultado da associacao entre cada parametro formal
	 * e seu correspondente parametro atual
	 */
	private AmbienteExecucaoImperativa2 bindParameters(
			AmbienteExecucaoImperativa2 ambiente,
			ListaDeclaracaoParametro parametrosFormais)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		ListaValor listaValor = parametrosReais.avaliar(ambiente);
		while (listaValor.length() > 0) {
			ambiente.map(parametrosFormais.getHead().getId(), listaValor.getHead());
			parametrosFormais = (ListaDeclaracaoParametro) parametrosFormais.getTail();
			listaValor = (ListaValor) listaValor.getTail();
		}
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos desta chamada de procedimento, onde os
	 * tipos dos parametros formais devem ser iguais aos tipos dos parametros
	 * reais na ordem em que se apresentam.
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificadores e
	 *            tipos.
	 * @return <code>true</code> se a chamada de procedimeno está bem tipada;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa amb)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException {
		AmbienteCompilacaoImperativa2 ambiente = (AmbienteCompilacaoImperativa2) amb;
		boolean resposta;
		ambiente.incrementa();
		ListaDeclaracaoParametro parametrosFormais = ambiente
				.getParametrosProcedimento(nomeProcedimento);
		List<Tipo> tiposParametrosReais = parametrosReais.getTipos(ambiente);
		// tem o mesmo numero de parametros formais e reais?
		if (tiposParametrosReais.size() == parametrosFormais.length()) {
			// a funcao tem algum parametro?
			if (tiposParametrosReais.isEmpty()
					|| parametrosFormais.getHead() == null) {
				resposta = true;
			} else {
				resposta = true;
				// tem parametros formais de tipos diferentes
				// de parametros reais na ordem em que se apresentam?
				for (Tipo tipoAtual : tiposParametrosReais) {
					if (parametrosFormais == null)
						break;
					if (!tipoAtual.equals(parametrosFormais.getHead().getTipo())) {
						resposta = false;
						break;
					}
					parametrosFormais = (ListaDeclaracaoParametro) parametrosFormais.getTail();
				}
			}
		} else {
			resposta = false;
		}
		ambiente.restaura();
		return resposta;
	}

}

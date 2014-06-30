package plp.imperative2.declaration;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.util.Lista;
import plp.imperative2.memory.AmbienteCompilacaoImperativa2;
import plp.imperative2.memory.AmbienteExecucaoImperativa2;

public class ListaDeclaracaoParametro extends Lista<DeclaracaoParametro> {

	public ListaDeclaracaoParametro() {
	}

	public ListaDeclaracaoParametro(DeclaracaoParametro declaracao) {
		super(declaracao, null);
	}

	public ListaDeclaracaoParametro(DeclaracaoParametro declaracao, ListaDeclaracaoParametro listaDeclaracao) {
		super(declaracao, listaDeclaracao);
	}

	public AmbienteExecucaoImperativa2 elabora(
			AmbienteExecucaoImperativa2 ambiente) {
		AmbienteExecucaoImperativa2 resposta;
		if (getHead() != null) {
			if (getTail() != null) {
				resposta = ((ListaDeclaracaoParametro)getTail()).elabora(getHead().elabora(ambiente));
			} else {
				resposta = getHead().elabora(ambiente);
			}
		} else {
			resposta = ambiente;
		}
		return resposta;
	}

	public boolean checaTipo(AmbienteCompilacaoImperativa2 ambiente)
			throws VariavelNaoDeclaradaException {
		boolean resposta;
		if (getHead() != null) {
			if (getTail() != null) {
				resposta = getHead().checaTipo(ambiente)
						&& ((ListaDeclaracaoParametro)getTail()).checaTipo(ambiente);
			} else {
				resposta = getHead().checaTipo(ambiente);
			}
		} else {
			resposta = true;
		}
		return resposta;
	}

	/**
	 * Cria um mapeamento do identificador para o tipo do parametro desta
	 * declaração no AmbienteCompilacaoImperativa2
	 * 
	 * @param ambiente
	 *            o ambiente que contem o mapeamento entre identificador e seu
	 *            tipo.
	 * @return o ambiente modificado pela declaração do parametro.
	 */
	public AmbienteCompilacaoImperativa2 declaraParametro(
			AmbienteCompilacaoImperativa2 ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		AmbienteCompilacaoImperativa2 resposta;
		if (getHead() != null) {
			if (getTail() != null) {
				resposta = ((ListaDeclaracaoParametro)getTail()).declaraParametro(
						getHead().declaraParametro(ambiente));
			} else {
				resposta = getHead().declaraParametro(ambiente);
			}
		} else {
			resposta = ambiente;
		}
		return resposta;
	}

}

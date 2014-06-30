package plp.imperative2.memory;

import java.util.HashMap;
import java.util.Stack;

import plp.expressions2.expression.Id;
import plp.imperative1.memory.ContextoCompilacaoImperativa;
import plp.imperative1.memory.ListaValor;
import plp.imperative2.declaration.ListaDeclaracaoParametro;

public class ContextoCompilacaoImperativa2 extends ContextoCompilacaoImperativa
		implements AmbienteCompilacaoImperativa2 {

	/**
	 * A pilha de blocos de contexto.
	 */
	private Stack<HashMap<Id, ListaDeclaracaoParametro>> pilhaParametrosProcedimento;

	/**
	 * Construtor da classe.
	 */
	public ContextoCompilacaoImperativa2(ListaValor entrada) {
		super(entrada);
		pilhaParametrosProcedimento = new Stack<HashMap<Id, ListaDeclaracaoParametro>>();
	}

	public void incrementa() {
		super.incrementa();
		pilhaParametrosProcedimento
				.push(new HashMap<Id, ListaDeclaracaoParametro>());
	}

	public void restaura() {
		super.restaura();
		pilhaParametrosProcedimento.pop();
	}

	public void mapParametrosProcedimento(Id idArg,
			ListaDeclaracaoParametro parametrosId)
			throws ProcedimentoJaDeclaradoException {
		HashMap<Id, ListaDeclaracaoParametro> aux = pilhaParametrosProcedimento
				.peek();
		if (aux.put(idArg, parametrosId) != null) {
			throw new ProcedimentoJaDeclaradoException(idArg);
		}
	}

	public ListaDeclaracaoParametro getParametrosProcedimento(Id idArg)
			throws ProcedimentoNaoDeclaradoException {
		ListaDeclaracaoParametro result = null;
		Stack<HashMap<Id, ListaDeclaracaoParametro>> auxStack = new Stack<HashMap<Id, ListaDeclaracaoParametro>>();
		while (result == null && !pilhaParametrosProcedimento.empty()) {
			HashMap<Id, ListaDeclaracaoParametro> aux = pilhaParametrosProcedimento
					.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
		}
		while (!auxStack.empty()) {
			pilhaParametrosProcedimento.push(auxStack.pop());
		}
		if (result == null) {
			throw new ProcedimentoNaoDeclaradoException(idArg);
		}

		return result;

	}

}

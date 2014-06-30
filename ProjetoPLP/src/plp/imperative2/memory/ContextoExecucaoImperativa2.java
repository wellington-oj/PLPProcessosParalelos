package plp.imperative2.memory;

import java.util.HashMap;
import java.util.Stack;

import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.imperative1.memory.ContextoExecucaoImperativa;
import plp.imperative1.memory.ListaValor;

public class ContextoExecucaoImperativa2 extends ContextoExecucaoImperativa
		implements AmbienteExecucaoImperativa2 {

	/**
	 * A pilha de blocos de contexto.
	 */
	private Stack<HashMap<Id, Procedimento>> pilhaProcedimento;

	/**
	 * A pilha de blocos de contexto.
	 */
	private ListaValor entrada;

	/**
	 * A pilha de blocos de contexto.
	 */
	private ListaValor saida;

	/**
	 * Construtor da classe.
	 */
	public ContextoExecucaoImperativa2(ListaValor entrada) {
		super(entrada);
		pilhaProcedimento = new Stack<HashMap<Id, Procedimento>>();
	}

	public void incrementa() {
		super.incrementa();
		pilhaProcedimento.push(new HashMap<Id, Procedimento>());
	}

	public void restaura() {
		super.restaura();
		pilhaProcedimento.pop();
	}

	/**
	 * Mapeia o id no procedimento dado.
	 * 
	 * @exception ProcedimentoJaDeclaradoException
	 *                se já existir um mapeamento do identificador nesta tabela.
	 */
	public void mapProcedimento(Id idArg, Procedimento procedimentoId)
			throws ProcedimentoJaDeclaradoException {

		HashMap<Id, Procedimento> aux = pilhaProcedimento.peek();
		if (aux.put(idArg, procedimentoId) != null) {
			throw new ProcedimentoJaDeclaradoException(idArg);
		}

	}

	/**
	 * Retorna o procedimento mapeado ao id dado.
	 * 
	 * @exception ProcedimentoNaoDeclaradoException
	 *                se não existir nenhum procedimento mapeado ao id dado
	 *                nesta tabela.
	 */
	public Procedimento getProcedimento(Id idArg)
			throws ProcedimentoNaoDeclaradoException {
		Procedimento result = null;
		Stack<HashMap<Id, Procedimento>> auxStack = new Stack<HashMap<Id, Procedimento>>();
		while (result == null && !pilhaProcedimento.empty()) {
			HashMap<Id, Procedimento> aux = pilhaProcedimento.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
		}
		while (!auxStack.empty()) {
			pilhaProcedimento.push(auxStack.pop());
		}
		if (result == null) {
			throw new ProcedimentoNaoDeclaradoException(idArg);
		}

		return result;
	}
	
	public Object clone(){
		ContextoExecucaoImperativa2 contexto = new ContextoExecucaoImperativa2(getEntrada());
		contexto.pilhaProcedimento = (Stack<HashMap<Id, Procedimento>>) this.pilhaProcedimento.clone(); 
		contexto.setPilha((Stack<HashMap<Id, Valor>>) getPilha().clone());
		contexto.setSaida(getSaida());
		return contexto;
	}
}

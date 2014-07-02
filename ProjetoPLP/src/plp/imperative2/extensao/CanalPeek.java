package plp.imperative2.extensao;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.memory.AmbienteExecucaoImperativa;

public class CanalPeek implements Expressao{

	IdCanal id;

	public IdCanal getId() {
		return id;
	}

	public void setId(IdCanal id) {
		this.id = id;
	}

	public CanalPeek(IdCanal id) {
		this.id = id;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		ControleCanal args = ((AmbienteExecucaoImperativa) amb).getCanal(id);
		args.lock.lock();
		try{
			return amb.get(id);
		}
		finally{
			args.lock.unlock();
		}
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean result = true;
		amb.get(id); // se estiver no ambiente, entao esta ok.
		return result;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return amb.get(id);
	}
}

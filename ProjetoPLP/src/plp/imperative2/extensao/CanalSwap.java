package plp.imperative2.extensao;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.memory.AmbienteExecucaoImperativa;

public class CanalSwap implements Expressao{

	IdCanal id;
	Expressao exp;

	public CanalSwap(IdCanal id, Expressao exp) {
		this.id = id;
		this.exp = exp;
	}


	@Override
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		ControleCanal args = ((AmbienteExecucaoImperativa) amb).getCanal(id);
		args.lock.lock();
		try{
			Valor retorno = amb.get(id);
			((AmbienteExecucaoImperativa) amb).changeValor(id, exp.avaliar(amb));
			return retorno;
		}
		finally{
			args.lock.unlock();
		}
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		amb.get(id);
		return true;
	}	
	@Override
	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return exp.getTipo(amb);
	}

}

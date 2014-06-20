package plp.imperative2.extensao;

import plp.expressions1.expression.ValorInteiro;
import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class CanalGet implements Expressao{

	Id id;
	
	public CanalGet(Id id) {
		this.id = id;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		synchronized (id) {
			Valor args = amb.get(id);
			amb.map(id, (Valor) new ValorInteiro(0));
			return args;			
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

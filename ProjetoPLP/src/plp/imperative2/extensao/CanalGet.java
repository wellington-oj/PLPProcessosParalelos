package plp.imperative2.extensao;

import java.util.concurrent.TimeUnit;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative2.util.Constantes;

public class CanalGet implements Expressao{

	IdCanal id;
	
	public IdCanal getId() {
		return id;
	}

	public void setId(IdCanal id) {
		this.id = id;
	}

	public CanalGet(IdCanal id) {
		this.id = id;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		ControleCanal args = ((AmbienteExecucaoImperativa) amb).getCanal(id);
		args.lock.lock();
		try{
			while(args.getVazio()){
				try {
					args.isEmpty.await(100,TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Valor retorno = amb.get(id);
			return retorno;
		}
		finally{
			args.setVazio(true);
			args.isEmpty.signalAll();
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

package plp.imperative2.extensao;

import java.util.concurrent.TimeUnit;

import plp.expressions2.expression.Expressao;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.command.Comando;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public class CanalSend implements Comando{

	IdCanal id;
	Expressao exp;
	
	public CanalSend(IdCanal id, Expressao exp) {
		this.id = id;
		this.exp = exp;
	}
	
	@Override
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa amb)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		
		ControleCanal args = ((AmbienteExecucaoImperativa) amb).getCanal(id);
		args.lock.lock();
		try{
			while(!args.getVazio()){
				try {
					args.isEmpty.await(100,TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			amb.changeValor(id, exp.avaliar(amb));
			return amb;
		}
		finally{
			args.setVazio(false);
			args.isEmpty.signalAll();
			args.lock.unlock();
		}
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return exp.checaTipo(ambiente) &&
				id.getTipo(ambiente).equals(exp.getTipo(ambiente));
	}

}

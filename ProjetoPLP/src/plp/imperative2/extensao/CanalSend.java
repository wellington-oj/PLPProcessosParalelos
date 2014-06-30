package plp.imperative2.extensao;

import java.util.concurrent.TimeUnit;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.command.Comando;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.imperative2.util.Constantes;

public class CanalSend implements Comando{

	IdCanal id;
	Expressao exp;
	
	public CanalSend(IdCanal id, Expressao exp) {
		this.id = id;
		this.exp = exp;
	}

	@Override
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		
		id.lock.lock();
		try{
			Valor args = ambiente.get(id);
			while(!args.equals(Constantes.stringNull)){
				try {
					id.isEmpty.await(100,TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				args = ambiente.get(id);
			}
			ambiente.changeValor(id, exp.avaliar(ambiente));
			return ambiente;
		}
		finally{
			id.isEmpty.signalAll();
			id.lock.unlock();
		}
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		ambiente.incrementaCanal();
		ambiente.mapCanal(id, exp.getTipo(ambiente));
		return true;
	}

}

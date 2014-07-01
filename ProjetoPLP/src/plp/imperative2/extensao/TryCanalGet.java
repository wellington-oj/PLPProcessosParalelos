package plp.imperative2.extensao;

import java.util.concurrent.TimeUnit;

import plp.expressions1.util.Tipo;
import plp.expressions1.util.TipoPrimitivo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorString;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative2.util.Constantes;

public class TryCanalGet implements Expressao{

	IdCanal id;
	
	public IdCanal getId() {
		return id;
	}

	public void setId(IdCanal id) {
		this.id = id;
	}

	public TryCanalGet(IdCanal id) {
		this.id = id;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		ControleCanal args = ((AmbienteExecucaoImperativa) amb).getCanal(id);
		if(args.lock.tryLock()){
			try{
				Valor retorno = amb.get(id);
				while(args.equals(Constantes.stringNull)){
					try {
						args.isEmpty.await(100,TimeUnit.MILLISECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					retorno = amb.get(id);
				}
				return retorno;
			}
			finally{
				((AmbienteExecucaoImperativa) amb).changeValor(id, Constantes.stringNull);
				args.isEmpty.signalAll();
				args.lock.unlock();
			}
		}
		else 
			return Constantes.stringNull;
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
//		Tipo args = ((AmbienteCompilacaoImperativa) amb).getCanal(id);
//		((AmbienteCompilacaoImperativa) amb).restauraCanal();
//		return args;
		return amb.get(id);
	}



}

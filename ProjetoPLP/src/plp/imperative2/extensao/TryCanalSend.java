package plp.imperative2.extensao;

import java.util.concurrent.TimeUnit;

import plp.expressions1.util.Tipo;
import plp.expressions1.util.TipoPrimitivo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative1.command.Comando;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.imperative2.util.Constantes;

public class TryCanalSend implements Expressao{

	IdCanal id;
	Expressao exp;
	
	public TryCanalSend(IdCanal id, Expressao exp) {
		this.id = id;
		this.exp = exp;
	}
	
	
	//Tentar colocar algo no canal, caso consiga retorna TRUE, caso contrario FALSE
	@Override
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		ControleCanal args = ((AmbienteExecucaoImperativa) amb).getCanal(id);
		if(args.getVazio()){
			args.lock.lock();
			try{
				((AmbienteExecucaoImperativa) amb).changeValor(id, exp.avaliar(amb));
				return Constantes.booleanoTrue;
			}
			finally{
				args.isEmpty.signalAll();
				args.lock.unlock();
			}
		}
		else 
			return Constantes.booleanoFalse;
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
		return TipoPrimitivo.BOOLEANO;
	}

}

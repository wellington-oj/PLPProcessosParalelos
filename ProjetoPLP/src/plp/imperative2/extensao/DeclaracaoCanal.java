package plp.imperative2.extensao;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.declaration.Declaracao;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public class DeclaracaoCanal extends Declaracao<Id, Expressao>{

	private IdCanal id;
	
	public DeclaracaoCanal(Id id,Expressao exp) {
		super(id,exp);
		this.setId((IdCanal) id);
	}

	@Override
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		ambiente.incrementaCanal();
		ambiente.mapCanal(id, new ControleCanal());
		ambiente.map(parametro1 ,parametro2.avaliar(ambiente));
		return ambiente;
//		ambiente.map(getId(),Constantes.stringNull);
//		return ambiente;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		boolean result = parametro2.checaTipo(ambiente);
		if (result) {
			ambiente.map(parametro1, parametro2.getTipo(ambiente));
		}
		return result;
//		ambiente.incrementaCanal();
//		ambiente.mapCanal(id, TipoPrimitivo.INTEIRO);
//		ambiente.map(parametro1, Constantes.stringNull.getTipo(ambiente));
//		return true;
	}

	public IdCanal getId() {
		return id;
	}

	public void setId(IdCanal id) {
		this.id = id;
	}
}

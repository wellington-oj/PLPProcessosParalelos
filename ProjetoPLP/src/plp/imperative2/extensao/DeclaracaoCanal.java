package plp.imperative2.extensao;

import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.declaration.Declaracao;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.imperative2.util.Constantes;

public class DeclaracaoCanal extends Declaracao<Id, Valor>{

	private IdCanal id;
	
	public DeclaracaoCanal(Id id) {
		super(id, Constantes.stringNull);
		this.setId((IdCanal) id);
	}

	@Override
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		ambiente.map(getId(),Constantes.stringNull);
		return ambiente;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		ambiente.map(parametro1, Constantes.stringNull.getTipo(ambiente));
		return true;
	}

	public IdCanal getId() {
		return id;
	}

	public void setId(IdCanal id) {
		this.id = id;
	}
}

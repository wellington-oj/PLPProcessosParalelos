package plp.imperative1.memory;

import java.util.HashMap;
import java.util.Stack;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.ContextoCompilacao;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative2.declaration.ListaDeclaracaoParametro;
import plp.imperative2.extensao.IdCanal;

public class ContextoCompilacaoImperativa extends ContextoCompilacao 
	implements AmbienteCompilacaoImperativa {

	private Stack<HashMap<Id, Tipo>> pilhaCanal;
	
    /**
     * A pilha de blocos de contexto.
     */    
    private ListaValor entrada;

    /**
     * Construtor da classe.
     */
    public ContextoCompilacaoImperativa(ListaValor entrada){
        super();
        this.entrada = entrada;    
        this.pilhaCanal = new Stack<>();
    }

    public Tipo getTipoEntrada() throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
    		EntradaVaziaException {
        if(entrada == null || entrada.getHead() == null) {
            throw new EntradaVaziaException();
        }
        Tipo aux = entrada.getHead().getTipo(this);
        entrada = (ListaValor)entrada.getTail();
        return aux;            
    }

	@Override
	public void mapCanal(IdCanal idArg, Tipo tipoId)
			throws VariavelJaDeclaradaException {
		try {
			HashMap<Id,Tipo> aux =  pilhaCanal.peek();
	    	if (aux.put(idArg, tipoId) != null) {
	    		throw new IdentificadorJaDeclaradoException();
	    	}
		} catch (IdentificadorJaDeclaradoException e) {
			throw new VariavelJaDeclaradaException (idArg);
		}
		
	}

	@Override
	public Tipo getCanal(IdCanal idArg) throws VariavelNaoDeclaradaException {
		try {
			Tipo result = null;
			Stack<HashMap<Id,Tipo>> auxStack = new Stack<HashMap<Id,Tipo>>();
			while (result == null && !pilhaCanal.empty()) {
				HashMap<Id,Tipo> aux = pilhaCanal.pop();
				auxStack.push(aux);
				result = (Tipo) aux.get(idArg);
			}
			while (!auxStack.empty()) {
				pilhaCanal.push(auxStack.pop());
			}
			if (result == null) {
				throw new IdentificadorNaoDeclaradoException();
			} 
			
			return result;
		} catch (IdentificadorNaoDeclaradoException e) {
			throw new VariavelNaoDeclaradaException(idArg);
		}
	}

	@Override
	public void incrementaCanal() {
		pilhaCanal.push(new HashMap<Id,Tipo>());
	}

	@Override
	public void restauraCanal() {
		pilhaCanal.pop();
	}
    
}


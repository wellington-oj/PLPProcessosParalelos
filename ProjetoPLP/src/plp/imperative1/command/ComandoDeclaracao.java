package plp.imperative1.command;

import plp.imperative1.declaration.Declaracao;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;

public class ComandoDeclaracao implements Comando {
    
    private Declaracao declaracao;
    
    private Comando comando;
    
    public ComandoDeclaracao(Declaracao declaracao, Comando comando){
        this.declaracao = declaracao;
        this.comando = comando;
    }
    
    /**
     * Declara a(s) vari�vel(is) e executa o comando.
     *
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     *
     * @return o ambiente modificado pela execu��o da declara��o e do comando.
     *
     */
    public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente) 
        throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException {
        ambiente.incrementa();
        ambiente = comando.executar(declaracao.elabora(ambiente));
        ambiente.restaura();
        if(ambiente.pilhaCanalTemAlgo()){
//        	ambiente.restauraCanal();
        }
        return ambiente;
    }

    /**
     * Verifica se o tipo do comando esta correto, levando em conta que
     * o tipo de uma variavel � o tipo do valor da sua primeira atribuicao.
     */
    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente) 
	        throws IdentificadorJaDeclaradoException, IdentificadorNaoDeclaradoException, EntradaVaziaException {
		boolean resposta;
		ambiente.incrementa();
		if(declaracao.checaTipo(ambiente)) {
            //ambiente = declaracao.declara(ambiente);
            resposta = comando.checaTipo(ambiente);
        }
        else {
            resposta = false;
        }
        ambiente.restaura();
        return resposta;
    }
 
}

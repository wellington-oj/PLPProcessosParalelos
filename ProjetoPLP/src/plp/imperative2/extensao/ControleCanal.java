package plp.imperative2.extensao;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControleCanal{

	ReentrantLock lock;
	Condition isEmpty;
	Condition isFull;
	boolean vazio = false;
	
	public boolean getVazio() {
		return vazio;
	}

	public void setVazio(boolean vazio) {
		this.vazio = vazio;
	}
	
	public ControleCanal() {
		lock = new ReentrantLock();
		isEmpty = lock.newCondition();
		isFull = lock.newCondition();
	}

}

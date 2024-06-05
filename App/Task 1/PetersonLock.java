//Practical assignment 2
//Student Number: u19304308
//Student Name: Kieran Woodrow
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

class PetersonLock implements Lock {

	private static final Logger LOGGER = Logger.getLogger("global");

	boolean[] flag = new boolean[2];
	private volatile int victim; //VOLATILE KEYWORD ALLOWS DIFFERENT THREADS TO MODIFY THE VARIABLE

	public PetersonLock() {
	}

	public void lock() {
		
		int i = ThreadID.get(); //GET THE THREAD ID FOR THE FIRST THREAD
		int j = 1 - i;
		flag[i] = true; //SET FIRST ONE TO TRUE TO INDICATE THAT IT IS INTERESTED
		victim = i; //ALSO MAKE IT THE VICTIM, SO IT WILL ALWAYS GIVE WAY 
		LOGGER.fine("[Lock]"+" "+"Thread"+" "+ThreadID.get()+" "+"is victim");
		
		while( flag[j] && victim == i ) //IF THREAD TWO  IS INTERESTED AND THREAD ONE IS THE VICTIM, THREAD TWO WAITS
		{
			

		}
		LOGGER.fine("[Lock]"+" "+"Thread"+" "+ThreadID.get()+" "+"entering CS"+" "+"(Thread"+" "+victim+" "+"is victim)");
	}

	public void unlock() {

		int i = ThreadID.get(); //GET THE THREAD ID FOR THE FIRST THREAD
		flag[i] = false;	
		LOGGER.fine("[Lock]"+" "+"Thread"+" "+ThreadID.get()+" "+"leaving CS");
	}

	// Any class implementing Lock must provide these methods
	public Condition newCondition() {
		throw new java.lang.UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}

	public boolean tryLock() {
		throw new java.lang.UnsupportedOperationException();
	}

	public void lockInterruptibly() throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}
}

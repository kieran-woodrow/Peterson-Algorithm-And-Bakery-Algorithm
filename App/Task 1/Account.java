//Practical assignment 2
//Student Number: u19304308
//Student Name: Kieran Woodrow

import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

class Account {
	private static final Logger LOGGER = Logger.getLogger("global");

	float balance;
	Lock lock;
	
	Account(float amount, Lock mylock) {
		balance = amount;
		lock = mylock;
	}

	boolean withdraw(float amount, String threadName) {
	
		boolean isSuccess = false; // INDICATE IF WITHDRAWL FUNCTION WAS SUCCESSFULL

		lock.lock();

			try{ //THIS IS THE CRITICAL SECTION BECAUSE IT IS USING THE SHARED VARIABLE BALANCE

				if( balance > amount && balance != 0)
				{
					balance = balance - amount;
					isSuccess = true;
					LOGGER.info(threadName+" "+"R"+String.format("%.02f", amount)+" "+"withdrawn from account,"+" "+"R"+String.format("%.02f", balance)+" "+"remaining");
				}
				
				else
				{
					LOGGER.info(threadName+" "+"Not enough money,"+" "+"R"+String.format("%.02f", balance)+" "+"remaining");
				}
			}
			finally{

				lock.unlock();
			}
			
		return isSuccess;
	}

	void deposit(float amount, String threadName) {

		lock.lock();

		try{ //THIS IS THE CRITICAL SECTION BECAUSE IT IS USING THE SHARED VARIABLE BALANCE

			balance = balance + amount;
			LOGGER.info(threadName+" "+"R"+String.format("%.02f", amount)+" "+"deposited into account,"+" "+"R"+String.format("%.02f", balance)+" "+"remaining");
		}
		finally{

			lock.unlock();
		}
	}

	float getBalance()
	{
		return balance;
	}

}

//Practical assignment 2
//Student Number: u19304308
//Student Name: Kieran Woodrow
class TThread extends Thread {

	Account account;
	int sleep = TThreadOptions.sleepTime; //STORE SLEEP TIME FROM TTHREAD OPTIONS VARIABLE TO USE IN SLEEP FUNCTION
	int runs = TThreadOptions.numRuns;  //STORE NUMBER OF RUNS FROM TTHREAD OPTIONS VARIABLE 
	float withdrawl = TThreadOptions.withdrawAmount; //STORE WITHDRAWL AMOUNT FROM TTHREAD OPTIONS VARIABLE 
	float deposit = TThreadOptions.depositAmount; //STORE DEPOSIT AMOUNT FROM TTHREAD OPTIONS VARIABLE 
	String name = getName(); //GET NAME OF THREAD

	TThread(Account acc) {
		account = acc;
	}

	public void run() {

		for ( int i = 0; i < runs ; i++ ) 
		{
			account.withdraw(withdrawl, name) ;
			try 
			{
				sleep(sleep); //SLEEP FOR A WHILE BEFORE YOU TRY NEXT WITHDRAWL
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			} //HAVE TO HAVE TRY AND CATCH FOR SLEEP FUNCTION
			
		}

		account.deposit(deposit, name);
	}
}
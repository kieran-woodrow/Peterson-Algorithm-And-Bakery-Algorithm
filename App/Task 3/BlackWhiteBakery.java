//Practical assignment 3
//Student Number: u19304308
//Student Name: Kieran Woodrow
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

class BlackWhiteBakery implements Lock {

	private static final Logger LOGGER = Logger.getLogger("global");

	int maxThreads;
	String colour = "Black";
	boolean[] flag;
	int[] number;
	String[] mycolor;

	public BlackWhiteBakery(int maxT) {

		maxThreads = maxT;
		flag = new boolean[maxT];
		number = new int[maxT];
		mycolor = new String[maxT];

		for(int i=0; i<maxT; i++)
		{
			flag[i] = false;
			number[i] = 0;
		}

	}

	public void lock() {

		int i = ThreadID.get(); //GET THE THREAD ID FOR THE FIRST THREAD
		flag[i] = true;
		mycolor[i] = colour;
		number[i] = sameColours(number[i]) + 1;

		LOGGER.fine("[LOCK]:"+" "+"(id:"+ThreadID.get()+",number:"+number[i]+",color:"+mycolor[i]+") => ticket assigned");
        flag[i] = false;

		for( int k=0; k < maxThreads; k++)
		{
			while( flag[k] )
			{
				//wait
			}

			if( mycolor[k] == mycolor[i] )
			{
				if( i != k)
				{
					if ( number[k] < number[i] )
						LOGGER.finest("[LOCK]: "+"assess priority => (id:"+k+",number:"+number[k]+",color:"+mycolor[k]+") > (id:"+i+",number:"+number[i]+",color:"+mycolor[i]+")");
				
					else if( number[i] < number[k] )
						LOGGER.finest("[LOCK]: "+"assess priority => (id:"+i+",number:"+number[i]+",color:"+mycolor[i]+") > (id:"+k+",number:"+number[k]+",color:"+mycolor[k]+")");

					else //if equal
					{
						if( i < k )
							LOGGER.finest("[LOCK]: "+"assess priority => (id:"+i+",number:"+number[i]+",color:"+mycolor[i]+") > (id:"+k+",number:"+number[k]+",color:"+mycolor[k]+")");
						
						else
							LOGGER.finest("[LOCK]: "+"assess priority => (id:"+k+",number:"+number[k]+",color:"+mycolor[k]+") > (id:"+i+",number:"+number[i]+",color:"+mycolor[i]+")");
					}

					while( (number[k] != 0)  && (number[k] < number[i]) && (mycolor[k] == mycolor[i]) )
					{

					}

				}

			}

			else
			{
				if( i != k )
				{
					if( mycolor[k] != colour )
						LOGGER.finest("[LOCK]: "+"assess priority => (id:"+k+",number:"+number[k]+",color:"+mycolor[k]+") > (id:"+i+",number:"+number[i]+",color:"+mycolor[i]+")");
				
					else
						LOGGER.finest("[LOCK]: "+"assess priority => (id:"+i+",number:"+number[i]+",color:"+mycolor[i]+") > (id:"+k+",number:"+number[k]+",color:"+mycolor[k]+")");

					while( (number[k] != 0) && (mycolor[i] == colour ) && (mycolor[k] != mycolor[i]) )
					{
						//wait
					}
				}
			}
		}

		LOGGER.finer("[LOCK]:"+" "+"(id:"+ThreadID.get()+",number:"+number[i]+",color:"+mycolor[i]+") => entering CS");
	}

	public void unlock() {

		int i = ThreadID.get(); //GET THE THREAD ID FOR THE FIRST THREAD
		
		if( mycolor[i] == "Black" )
			colour = "White";

		else
			colour = "Black";

		LOGGER.finer("[LOCK]:"+" "+"(id:"+ThreadID.get()+",number:"+number[i]+",color:"+mycolor[i]+") => left CS, shared color:"+colour);

		number[i] = 0;
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

	public int sameColours(int c){

		int count = 0;

		for(int i=0; i < mycolor.length; i++)
		{
			if( mycolor[i] == mycolor[c] && i!=c)
				count = 1 + count;
		}
		return count;
	}
}



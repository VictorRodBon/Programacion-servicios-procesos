package museo;

import java.util.concurrent.Semaphore;

public class HiloConductor extends Thread{
	BeanMuseo bm=null;
	Semaphore esperabus=null;
	Semaphore esperaconductor=null;
	
	

	public HiloConductor(BeanMuseo bm, Semaphore esperabus, Semaphore esperaconductor) {
		this.bm = bm;
		this.esperabus = esperabus;
		this.esperaconductor = esperaconductor;
	}



	public void run() {
	
		if (bm.esperabus<bm.maxvisitantes)
			try {
				esperaconductor.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		for (int i=0;i<bm.maxvisitantes;i++)
			esperabus.release();
	}
	
}

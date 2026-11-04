package museo;

import java.util.concurrent.Semaphore;

public class HiloVisitante extends Thread{

	Semaphore puertas;
	Semaphore aforo;
	BeanMuseo bm;
	Semaphore mutex;
	Semaphore esperabus;
	Semaphore esperaconductor;




	public HiloVisitante(Semaphore puertas, Semaphore aforo, BeanMuseo bm, Semaphore mutex, Semaphore esperabus,
			Semaphore esperaconductor) {
		this.puertas = puertas;
		this.aforo = aforo;
		this.bm = bm;
		this.mutex = mutex;
		this.esperabus = esperabus;
		this.esperaconductor = esperaconductor;
	}




	public void run() {

		try {	
			aforo.acquire();
			System.out.println("Voy a entrar al museo");
			puertas.acquire();
	//////////ACCESO DE 1 en 1////////////////////
			puertas.release();
			
			int tiempo=(int)((Math.random()*3)+1);
			System.out.println("Estoy visitando el museo tardo "+tiempo+" segundos");
			
			mutex.acquire();
			bm.tiempo+=tiempo;
			mutex.release();
			
			Thread.sleep(tiempo*1000);
			System.out.println("Voy a salir del museo");
			
			puertas.acquire();
	//////////SALIDA DE 1 en 1////////////////////
			puertas.release();
			
			System.out.println("Me voy ya a casa");
			aforo.release();
			
			
			mutex.acquire();
			  bm.esperabus++;
			mutex.release();
			
			if(bm.esperabus==bm.maxvisitantes)
			  esperaconductor.release();
			 
			esperabus.acquire();

		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

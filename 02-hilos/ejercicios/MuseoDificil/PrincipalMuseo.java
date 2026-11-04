package museo;
/* ENUNCIADO
 * Se desea gestionar las visitas a un museo de manera concurrente.
 * El museo tendrá una única puerta de entrada salida por donde
 *  los visitantes entrarán  visitarán el museo mediante un tiempo aleatorio entre 1 y 4 segundos
 *  y a continuación saldrán por la misma puerta.
 * El museo tendrá un aforo máximo de 3 visitantes.
 * Se desea mostrar el tiempo medio de visita por visitante.
 * Una vez haya concluido la visita los visitantes deberán esperar en el 
 * autobús a que todos los visitantes terminen la visita
 * El conductor del autobús arrancará el autobus y se marcharán terminando así
 * la ejecución de todos los hilos.
 * Sincroniza para representar el escenario anterior.
 */
import java.util.concurrent.Semaphore;

/*
 * Se desea simular una visita a un museo. Los 10 hilos visitantes entrarán y saldrán por la puerta de 1 en 1.
 * Los visitantes visitarán el museo un tiempo entre 1 y 4 aleatorios. Saldrán por la misma puerta de 1 en 1
 * El museo tendrá un aforo máximo de 3 visitantes. Si alguien quiere entrar al museo se deberá bloquear.
 * Una vez terminada la visita el visitante se subirá al autobús y esperará a que los 10 visitantes se
 * hayan subido al autobús.
 * Mientras que no se hayan subido todos el conductor deberá esperar.
 * Una vez subidos el conductor arrancará,los llevará a casa y liberá a los visitantes para que se puedan bajar
 * 
 * 
 * 
 */


public class PrincipalMuseo {

	public static void main(String[] args) {
		
		final int AFOROMAXIMO=3;
		
		BeanMuseo bm=new BeanMuseo();
		Semaphore puertas = new Semaphore(1);
		Semaphore aforo = new Semaphore(AFOROMAXIMO);
		Semaphore mutex = new Semaphore(1);
		Semaphore esperabus = new Semaphore(0);
		Semaphore esperaconductor = new Semaphore(0);
		
		HiloVisitante v1= new HiloVisitante(puertas,aforo,bm,mutex,esperabus,esperaconductor);
		HiloVisitante v2= new HiloVisitante(puertas,aforo,bm,mutex,esperabus,esperaconductor);
		HiloVisitante v3= new HiloVisitante(puertas,aforo,bm,mutex,esperabus,esperaconductor);
		HiloVisitante v4= new HiloVisitante(puertas,aforo,bm,mutex,esperabus,esperaconductor);
		HiloVisitante v5= new HiloVisitante(puertas,aforo,bm,mutex,esperabus,esperaconductor);
		HiloConductor hc = new HiloConductor(bm,esperabus,esperaconductor);
		
		v1.start();
		v2.start();
		v3.start();
		v4.start();
		v5.start();
		hc.start();

		try {
			v1.join();
			v2.join();
			v3.join();
			v4.join();
			v5.join();
			hc.join();
			
			System.out.println("El tiempo medio de visita ha sido.."+bm.tiempo/bm.maxvisitantes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
	}

}

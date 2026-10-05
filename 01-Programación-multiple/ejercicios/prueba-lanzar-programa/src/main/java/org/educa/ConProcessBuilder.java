package org.educa;

public class ConProcessBuilder {
    public static void main(String[] args) throws Exception {
        String ruta = "/usr/bin/galculator";
        LanzarProceso lp = new LanzarProceso(ruta);
        lp.ejecutar();
    }
}




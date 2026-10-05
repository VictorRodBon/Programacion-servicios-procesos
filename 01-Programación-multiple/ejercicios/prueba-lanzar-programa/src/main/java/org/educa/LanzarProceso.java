package org.educa;

public class LanzarProceso {
    private String ruta;

    // Constructor para inicializar la ruta
    public LanzarProceso(String ruta) {
        this.ruta = ruta;
    }

    // Método para ejecutar el proceso
    public void ejecutar() {
        try {
            ProcessBuilder pb = new ProcessBuilder(this.ruta);
            pb.start();
        } catch (Exception e) {
            System.out.println("Error al lanzar el proceso: " + e.getMessage());
        }
    }
}

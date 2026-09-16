# Definiciones:
- **Programa:** Es una secuencia de instrucciones para realizar unas determinadas tareas.
- **Ejecutable:** Es un programa en binario que puede interpretar directamente el ordenador.
- **Proceso:** Es un programa (o parte de él) que se encuentra en ejecución. El SO decide qué proceso entrea y sale de la CPU.
- **Servicio:** Es un programa en ejecución en segundo plano, que no interactúa con el usuario. Normalmente realizan tareas para el SO.
- **Servidor:** Algo que sirve.
- **Algoritmo:** forma de solucinar un problema.

# Procesos:
## Elementos de un proceso:
- Código ejecutable
- Datos
- Pila de programa
- Contador de programa
- Puntero de pila y otros registros
- Intormación necesaria para ejecutar el programa

## Bloque de Control de Proceso (BCP):
Cuando un proceso sale de la CPU temporalmente, al volver a estar debe retormarse en el mismo estado en que se encontraba. La información necesaria para ello se guarda en el **Bloque de Control de Proceso**, que se compone de:
- Identificador de proceso
- Estado del proceso
- Contador de programa
- Registros de la CPU
- Información de planificación de CPU (prioridad entre otros)
- Información de gestión de memoria
- Información contable (cantidad de tiempo de CPU, tiempo real consumido...)
- Información de estado de E/S (dispositivos, archivos abiertos...)

## Estados de un proceso:
- Ejecución: El proceso está haciendo uso de la CPU
- Bloqueado: El procedso está esperando a que ocurra un evento (ej. que termine un evento de entrada salida)
- Preparado: El proceso está listo para ejecutarse cuando se le de paso

- Ejecución -> Bloqueo -> Bloqueado | Está en espera de un evento externo
- Bloqueado -> Evento -> Preparado | El evento que esperaba se produce
- Preparado -> Despachar -> Ejecución | El sistema le otorga un tiempo de CPU
- Ejecución -> Fin de tiempo -> Preparado | Se le acaba el tiempo asignado de CPU

# Planificación de procesos:

## Niveles de planificación de procesos:
- **Largo plazo:** Se realiza el control de admisión de procesos a ejecutar.
- **Medio plazo:** Se selecciona qué procesos se añaden o retiran de la memoria principal.
- **Corto plazo:** Se selecciona el siguiente proceso a ejecutar.

## Tipos de planificación de procesos:
- **No apropiativa/No expulsora:** El proceso sigue usando la CPU.
- **Apropiativa/Expulsora:** El SO puede expulsar a los procsos de la CPU.

## Medidas de la planificación:
- **Uso de la CPU:** Porcentaje de tiempo que la CPU estña en uso. Se debe maximizar
- **Rendimiento:** Nº de trabajos terminados por unidad de tiempo. Se debe maximizar
- **Tiempo de retorno:** El tiempo transcurrido entre la llegada de un proceso y su finalización. Se debe minimizar.
- **Tiempo de servicio:** Tiempo dedicado a tareas productivas.
- **Tiempo de espera:** Tiempo que está esperando.

## Algunos ejemplos de algoritmos de planificación
- **FCFS:** El primero en llegar primero en salir. No expulsor
    - (+) Facil de implementar
    - (-) Tiempo de espera promedio bastante largo.
- **SJF:** El más corto es el primero en salir. No expulsor
    - (+) Minimiza el tiempo de espera medio
    - (-) Riesgo de inanición de los procesos largos
    - (-) En la práctica se basa en estimaciones de la duración de los procesos
- **Por prioridad:** Entra el de mayor prioridad. Expulsor/No expulsor
    - (-) Los procesos con prioridad más baja tienen riesgo de inanición. Solución; aumento de prioridad por envejecimiento.
- **RR:** Como *FCFS* pero cada proceso dispone de un tiempo máximo (*Q*). Apropiativo
    - Si *Q* es muy grande, los procesos terminan de usar al CPU antes del límite de tiempo.
    - Si *Q* es muy pequeño, al estar cambiando constantemente de rpoceso, el rendimiento disminuye mucho.
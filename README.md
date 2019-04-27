# POC-Thread :umbrella:


## Runnable vs Thread
Runnable es sólo una interfaz que necesita para instanciar un hilo para contenerlo. Mientras que el Thread es una clase contiene ya la capacidad de generar un hilo. Si usted extiende Thread no se puede extender de ninguna otra clase mas(Java no admite la herencia múltiple), en cambio se puede tener múltiples interfaces en una clase, por lo tanto se puede tener Runnable y extender de otra clase.

## Ciclo de vida de un Thread

| Nombre | Definicion |
| ----- | ---- |
| Nuevo (New) | El thread ha sido creado pero no inicializado, es decir, no se ha ejecutado todavía el método start(). Se producirá un mensaje de error (IllegalThreadStateException) si se intenta ejecutar cualquier método de la clase Thread distinto de start(). |
| Ejecutable (Runnable) | El thread puede estar ejecutándose, siempre y cuando se le haya asignado un determinado tiempo de CPU. En la práctica puede no estar siendo ejecutado en un instante determinado en beneficio de otro thread. |
| Bloqueado (Blocked o Not Runnable) | El thread podría estar ejecutándose, pero hay alguna actividad interna suya que lo impide, como por ejemplo una espera producida por una operación de escritura o lectura de datos por teclado (E/S). Si un thread está en este estado, no se le asigna tiempo de CPU. |
| Muerto (Dead) | La forma habitual de que un thread muera es finalizando el método run(). También puede llamarse al método stop() de la clase Thread, aunque dicho método es considerado “peligroso” y no se debe utilizar. |

### sleep()
Hace que el subproceso que se está ejecutando en ese momento esté en suspensión (cese temporalmente la ejecución) durante la cantidad especificada de milisegundos más la cantidad especificada de nanosegundos, sujeto a la precisión y la precisión de los cronómetros y programadores del sistema. El hilo no pierde la propiedad de ningún monitor.

### yield()
Una sugerencia para el programador de que el subproceso actual está dispuesto a dar su uso actual de un procesador. El programador es libre de ignorar esta sugerencia.
El rendimiento es un intento heurístico para mejorar la progresión relativa entre subprocesos que de lo contrario utilizarían una CPU. Su uso debe combinarse con un perfil detallado y una evaluación comparativa para garantizar que realmente tenga el efecto deseado.

### wait()
Hace que el subproceso actual espere hasta que otro subproceso invoque el método notify () o el método notifyAll () para este objeto, o que otro subproceso interrumpa el subproceso actual, o que haya transcurrido una cierta cantidad de tiempo real.

### join()
El método join () de una instancia de Thread se usa para unir el inicio de la ejecución de un thread al final de la ejecución de otro thread, de manera que un thread no comienza a ejecutarse hasta que otro thread finaliza. Si se llama a join () en una instancia de Thread, el hilo actualmente en ejecución se bloqueará hasta que la instancia de Thread haya terminado de ejecutarse.

# SpeedFast - Semana 5

Proyecto de **Desarrollo Orientado a Objetos II** basado en el caso SpeedFast.

## Objetivo

Continuar el sistema concurrente desarrollado en la Semana 4 incorporando sincronizacion para proteger una zona de carga compartida. Tres repartidores se ejecutan en paralelo y retiran pedidos de forma segura, evitando condiciones de carrera y entregas duplicadas.

## Elementos principales

- `Pedido`: clase base de los pedidos, ahora con estado.
- `EstadoPedido`: enum con `PENDIENTE`, `EN_REPARTO` y `ENTREGADO`.
- `ZonaDeCarga`: recurso compartido protegido mediante metodos `synchronized`.
- `Repartidor`: implementa `Runnable` y consume pedidos desde la misma zona de carga.
- `Main`: carga 6 pedidos, ejecuta 3 repartidores mediante `ExecutorService`, espera su finalizacion y valida que todos queden entregados.

Se conservan `PedidoComida`, `PedidoEncomienda` y `PedidoExpress` del proyecto de semanas anteriores para mantener la continuidad del caso.

## Ejecucion

1. Abrir la carpeta `SpeedFast` en IntelliJ IDEA.
2. Usar JDK 17.
3. Ejecutar `src/main/java/cl/speedfast/Main.java`.
4. Verificar que cada pedido aparezca una sola vez como retirado y termine en estado `ENTREGADO`.
5. Al final debe mostrarse: `Todos los pedidos han sido entregados correctamente`.

## Entrega

La pauta solicita subir el proyecto a GitHub dentro de una carpeta llamada `semana 5` y entregar tambien un archivo `.zip` o `.rar` funcional.

## Autor

Osvaldo Gonzalez

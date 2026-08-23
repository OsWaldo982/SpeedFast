# SpeedFast — Semana 2

Actividad de Desarrollo Orientado a Objetos II sobre clases abstractas,
herencia, sobrescritura y polimorfismo.

## Estructura

- `Pedido`: clase abstracta con los atributos comunes `idPedido`,
  `direccionEntrega` y `distanciaKm`.
- `PedidoComida`: calcula 15 minutos base más 2 minutos por kilómetro.
- `PedidoEncomienda`: calcula 20 minutos base más 1,5 minutos por kilómetro
  y redondea el resultado al entero más cercano.
- `PedidoExpress`: calcula 10 minutos base y agrega 5 minutos cuando la
  distancia supera los 5 kilómetros.
- `Main`: instancia los tres tipos de pedido y demuestra polimorfismo mediante
  un arreglo de tipo `Pedido[]`.


# SpeedFast

Proyecto desarrollado en Java para la actividad de la Semana 1 de la asignatura **Desarrollo Orientado a Objetos II**.

El objetivo del proyecto es aplicar conceptos de **herencia, polimorfismo, sobrecarga y sobreescritura de métodos** mediante un sistema simple de asignación de repartidores para una empresa de delivery.

## Descripción

SpeedFast trabaja con tres tipos de pedidos:

* Pedido de comida
* Pedido de encomienda
* Pedido express

Todos los pedidos heredan de la clase base `Pedido`.

La clase `Pedido` contiene el método:

```java
asignarRepartidor()
```

Este método es sobrescrito en cada una de las clases hijas para mostrar un comportamiento diferente según el tipo de pedido.

También se utiliza una versión sobrecargada:

```java
asignarRepartidor(String nombreRepartidor)
```

Esta permite asignar un repartidor indicando su nombre y mostrar las validaciones correspondientes a cada servicio.

## Clases principales

### Pedido

Clase padre del proyecto. Contiene los atributos generales de un pedido y los métodos para asignar repartidores.

### PedidoComida

Representa los pedidos de comida. Para este tipo de servicio se considera que el repartidor debe contar con mochila térmica.

### PedidoEncomienda

Representa el envío de documentos o paquetes. Se considera la validación del peso y del embalaje antes de realizar la entrega.

### PedidoExpress

Representa compras realizadas en supermercados o farmacias. Se busca asignar un repartidor cercano y con disponibilidad inmediata.

### Main

En la clase `Main` se crean objetos de los diferentes tipos de pedido y se ejecutan los métodos de asignación para comprobar el funcionamiento del polimorfismo.

## Autor

Osvaldo González

# Montecarlo Distribuido

Conociendo las fórmulas para el cálculo del área del cuadrado y el círculo, y usando un generador de datos aleatorios uniforme, es posible calcular el valor de Pi.

Basta con imaginar un cuadrado de lado 2 unidades de medida y un círculo inscrito, que tendría entonces un radio de 1 unidad de medida. Dado esto, el área de ese cuadrado es entonces de 2x2 = 4, y el del círculo de pi*12 = pi

Para la solución de este problema hemos realizado dos versiones. En este repositorio se encontrará la segunda versión la cuál es mediante programación distribuida, está realizada con los patrones de diseño **Master-Worker**, **CallBack** y **Observer**.  Junto a esto se usa el MiddleWare ICE para la solución deistribuida.

La primera versión se encontrará [aquí](https://github.com/StivenArboleda/Monolitic-PIMontecarlo "aquí")

### Versiones usadas:
- Slice2Java versión: 3.7.7v 
- gradle versioón: 6.6
- Java versión: 11.0.15

### Despliegue del proyecto
- gradle : master:build
- gradle : worker:build
- java -jar master/build/libs/master.jar
- java -jar worker/build/libs/worker.jar

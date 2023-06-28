# TPE segunda parte

## Descripción

Segunda estapa del tpe.

## Autores

- Luguercio, Axel

## Fecha

2023/06/23

## Contenido

1. [Backtracking](###Backtracking)
2. [Greedy](###Greedy)
3. [Resultados](###Comparativa)
4. [Conclusiones](###Conclusion)
---
## A continuacion se responderan las siguientes preguntas

* ¿Cuál fue la estrategia Backtracking llevada adelante para resolver el problema? ¿Cuál es el
costo computacional de dicha estrategia?
* ¿Cuál fue la estrategia Greedy llevada adelante para resolver el problema? ¿Cuál es el costo
computacional de dicha estrategia?
* Mostrar una tabla comparativa, para las distintas entradas posibles, de los resultados obtenidos
por ambas técnicas. Por resultados no sólo nos referimos a la calidad de la solución sino también a la métrica que determina el costo de obtener dicha solución.

---

### Backtracking

La estrategia Backtracking llevada adelante para resolver el problema consiste en explorar exhaustivamente todas las posibles combinaciones de estaciones, verificando si se ha visitado todas las estaciones y actualizando la mejor solución encontrada en cada caso. El algoritmo realiza una búsqueda recursiva y utiliza podas para evitar explorar soluciones que ya son peores que la mejor solución encontrada hasta el momento. El costo computacional de esta estrategia es exponencial, ya que el número de combinaciones posibles crece de manera exponencial con la cantidad de estaciones.

---

### Greedy

La estrategia Greedy llevada adelante para resolver el problema se basa en seleccionar en cada paso la conexión más económica (arcos con valores menores) disponible. En este caso, se implementa una variante del algoritmo de Dijkstra utilizando una cola de prioridad para seleccionar los arcos de menor costo. El costo computacional de esta estrategia depende de la implementación específica y la estructura del grafo, pero en general es más eficiente que el enfoque Backtracking.

---

### Comparativa

Tabla comparativa de los resultados obtenidos por ambas técnicas para distintas entradas posibles

| Entrada      | Backtracking | Greedy  | Cantidad de Ciclos Backtracking | Cantidad de Ciclos Greedy |
| ------------ | ------------ | ------- |---------------------------------|---------------------------|
| dataset1.txt | 30 kms       | 30 kms  | 11                              | 10                        |
| dataset2.txt | 165 kms      | 150 kms | 57                              | 103                       |
| dataset1.txt | 680 kms      | 510 kms | 8178                            | 2345                      |

---

### Conclusion

De la comparativa realizada, podemos sacar las siguientes conclusiones:

* Distancia recorrida: En general, ambos algoritmos lograron encontrar soluciones con distancias similares. En la mayoría de los casos, la diferencia en la distancia recorrida entre los algoritmos es aceptable, aunque en algunos casos, como en dataset2.txt, la diferencia es un poco más notable.

* Eficiencia del algoritmo: El algoritmo Greedy muestra una mejor eficiencia en términos de cantidad de ciclos realizados en comparación con el algoritmo de Backtracking. En todos los casos, el algoritmo Greedy realizó menos ciclos para encontrar la solución óptima.

* Escalabilidad: Aunque el algoritmo Greedy fue más eficiente en términos de cantidad de ciclos, es importante considerar su escalabilidad en problemas más grandes. El algoritmo de Backtracking es exhaustivo y busca todas las soluciones posibles, por lo que puede volverse más lento y menos práctico a medida que aumenta el tamaño del grafo y la complejidad del problema. El algoritmo Greedy puede ser más adecuado en esos casos, ya que tiene una complejidad menor.

En general, podemos concluir que el algoritmo Greedy es más eficiente en términos de cantidad de ciclos, pero es importante tener en cuenta la escalabilidad y evaluar el rendimiento en problemas más grandes antes de tomar una decisión final sobre qué algoritmo utilizar.
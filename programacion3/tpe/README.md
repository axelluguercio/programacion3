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

La estrategia Greedy llevada adelante para resolver el problema se basa en seleccionar en cada paso la conexión más económica disponible, sin considerar las decisiones futuras. En este caso, se implementa una variante del algoritmo de Dijkstra utilizando una cola de prioridad para seleccionar los arcos de menor costo. El costo computacional de esta estrategia depende de la implementación específica y la estructura del grafo, pero en general es más eficiente que el enfoque Backtracking. En este caso, el costo computacional del algoritmo Greedy es del orden de O(E log V), donde E es la cantidad de arcos y V es la cantidad de vértices en el grafo.

---

### Comparativa

Tabla comparativa de los resultados obtenidos por ambas técnicas para distintas entradas posibles

| Entrada      | Backtracking | Greedy  | Tiempo de ejecución (ns) Backtracking | Tiempo de ejecución (ns) Greedy |
| ------------ | ------------ | ------- | ------ | -------- |
| dataset1.txt | 30 kms       | 30 kms  | 475416 | 1906000  |
| dataset2.txt | 165 kms      | 150 kms | 412333 | 2384583  |
| dataset1.txt | 680 kms      | 510 kms | 4805208 | 3169250 |

---

### Conclusion

De la comparativa realizada, podemos sacar las siguientes conclusiones:

* En términos de la calidad de la solución (distancia total recorrida), en algunos casos el algoritmo Greedy obtuvo una solución de igual calidad que el algoritmo Backtracking, como en el caso del dataset1.txt donde ambos algoritmos obtuvieron una distancia total de 30 kms. Sin embargo, en otros casos, el algoritmo Greedy obtuvo soluciones de calidad ligeramente superior, como en el dataset2.txt donde el algoritmo Greedy obtuvo una distancia total de 150 kms frente a los 165 kms del algoritmo Backtracking.

* En cuanto al tiempo de ejecución, se observa que el algoritmo Greedy en general requiere mayor tiempo que el algoritmo Backtracking para encontrar una solución. En los casos de los datasets analizados, el algoritmo Greedy tuvo tiempos de ejecución más altos que el algoritmo Backtracking.

* Se puede concluir que el algoritmo Greedy ofrece una buena entre calidad de la solución y tiempo de ejecución, siempre y cuando el objetivo sea encontra la solucion optima.
import java.util.*;

public class OptimizadorCadenas {

    /**
     * Método optimizado para encontrar el primer carácter no repetido en una cadena.
     * Complejidad: O(n) tiempo, O(k) espacio (k = caracteres distintos).
     *
     * @param texto Cadena de entrada.
     * @return El primer carácter con frecuencia 1, o null si no existe.
     */
    public static Character primerCaracterNoRepetido(String texto) {
        // PASO 1: Mapa para contar frecuencias.
        // Usamos LinkedHashMap para preservar el orden de inserción.
        // Esto nos permite, en el paso 2, iterar en el orden en que aparecen los caracteres en el texto.
        Map<Character, Integer> frecuencias = new LinkedHashMap<>();

        // PRIMER RECORRIDO (O(n)):
        // Recorremos la cadena de principio a fin para contar cuántas veces aparece cada letra.
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            // getOrDefault: si la clave existe, devuelve su valor; si no, devuelve 0.
            // Luego sumamos 1 y lo guardamos en el mapa.
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // SEGUNDO RECORRIDO (O(n) en el peor caso, pero normalmente solo recorre el mapa de claves):
        // Iteramos sobre las entradas del mapa. Como es LinkedHashMap, el orden es el de primera aparición.
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            // Si la frecuencia es exactamente 1, es el primer carácter no repetido.
            if (entrada.getValue() == 1) {
                return entrada.getKey(); // Devolvemos el carácter.
            }
        }

        // Si todos los caracteres se repiten, retornamos null.
        return null;
    }

    // ALTERNATIVA MÁS MEMORIA EFICIENTE (usando HashMap común y segundo recorrido sobre el texto):
    public static Character primerCaracterNoRepetidoV2(String texto) {
        // Mapa sin orden (HashMap). Ocupa menos memoria que LinkedHashMap.
        Map<Character, Integer> frecuencias = new HashMap<>();

        // Primer recorrido (O(n)): conteo.
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // Segundo recorrido (O(n)): recorremos el texto ORIGINAL en orden.
        // Al recorrer el texto directamente, aseguramos el orden de primera aparición.
        for (char c : texto.toCharArray()) {
            if (frecuencias.get(c) == 1) {
                return c;
            }
        }
        return null;
    }
}

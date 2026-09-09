public class TeoriaColisiones {
    public static void main(String[] args) {
        // Sabemos por teoría que "FB".hashCode() == "Ea".hashCode() == 2236.
        String s1 = "FB";
        String s2 = "Ea";

        Set<String> conjunto = new HashSet<>();

        // Inserción de s1:
        // - Se calcula hash = 2236 -> bucket "X".
        // - Se guarda "FB" en bucket "X".
        conjunto.add(s1);

        // Inserción de s2:
        // - Se calcula hash = 2236 -> bucket "X" (el mismo).
        // - Java verifica si existe algún elemento en bucket "X" que sea .equals(s2).
        // - Recorre la lista: encuentra "FB". Pregunta "FB".equals("Ea") -> false.
        // - Como son diferentes, agrega "Ea" como un NUEVO ELEMENTO en el mismo bucket.
        conjunto.add(s2);

        // RESULTADO: El conjunto contiene 2 elementos.
        // La colisión de hash NO reduce el tamaño, siempre y cuando equals() los distinga.
        System.out.println("Cantidad de elementos: " + conjunto.size()); // 2

        // Verificación de existencia:
        System.out.println("Contiene 'FB'? " + conjunto.contains("FB")); // true
        System.out.println("Contiene 'Ea'? " + conjunto.contains("Ea")); // true
    }
}

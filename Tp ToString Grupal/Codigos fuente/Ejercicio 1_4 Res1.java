import java.util.*;

class TarjetaAcceso {
    // CORRECCIÓN 1: Campos declarados 'final' para evitar la mutación.
    private final String codigo;
    private final int nivelAcceso;

    public TarjetaAcceso(String codigo, int nivelAcceso) {
        this.codigo = codigo;
        this.nivelAcceso = nivelAcceso;
    }

    // CORRECCIÓN 2: Se ELIMINA el método 'setNivelAcceso'. No se puede modificar el objeto.
    // En su lugar, se provee un método que DEVUELVE UNA NUEVA INSTANCIA con el nivel cambiado.
    public TarjetaAcceso conNuevoNivel(int nuevoNivel) {
        // Esto crea un objeto nuevo, el anterior sigue siendo válido en el HashSet.
        return new TarjetaAcceso(this.codigo, nuevoNivel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TarjetaAcceso)) return false;
        TarjetaAcceso that = (TarjetaAcceso) o;
        // CORRECCIÓN 3: Ambos campos son inmutables, el hash nunca cambia.
        return nivelAcceso == that.nivelAcceso && Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        // CORRECCIÓN 4: Como los campos no cambian, este hash es ESTABLE para toda la vida del objeto.
        return Objects.hash(codigo, nivelAcceso);
    }
}

public class ControlAccesoSeguro {
    public static void main(String[] args) {
        Set<TarjetaAcceso> tarjetasValidas = new HashSet<>();

        // Se crea e inserta la tarjeta.
        TarjetaAcceso t1 = new TarjetaAcceso("ACC-101", 2);
        tarjetasValidas.add(t1);

        // CORRECCIÓN 5: Para "cambiar" el nivel, se reemplaza la referencia en el Set.
        // 1. Se remueve la vieja (funciona porque el hash original sigue siendo el mismo).
        // 2. Se agrega la nueva instancia.
        TarjetaAcceso t1Actualizado = t1.conNuevoNivel(5);
        tarjetasValidas.remove(t1);          // Elimina la instancia antigua (hash estable).
        tarjetasValidas.add(t1Actualizado);  // Agrega la nueva (con su nuevo hash).

        // Ahora contains() funciona correctamente.
        System.out.println("¿Tarjeta válida?: " + tarjetasValidas.contains(t1Actualizado)); // true
        System.out.println("Cantidad: " + tarjetasValidas.size()); // 1
    }
}

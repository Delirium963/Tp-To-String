import java.util.*;

class TarjetaAcceso {
    // CORRECCIÓN: 'codigo' es la CLAVE ÚNICA de la entidad (inmutable).
    private final String codigo;
    // 'nivelAcceso' puede mutar libremente, pero NO PARTICIPA en el hash/equals.
    private int nivelAcceso;

    public TarjetaAcceso(String codigo, int nivelAcceso) {
        this.codigo = codigo;
        this.nivelAcceso = nivelAcceso;
    }

    // El setter es seguro ahora, porque modificar esto NO cambia el hash.
    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TarjetaAcceso)) return false;
        TarjetaAcceso that = (TarjetaAcceso) o;
        // CORRECCIÓN: equals solo compara el 'codigo' (que es único e inmutable).
        // Dos tarjetas son la misma si tienen el mismo código.
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        // CORRECCIÓN: El hash depende ÚNICAMENTE del código.
        // Aunque el nivel cambie, el hash permanece IDÉNTICO.
        return Objects.hash(codigo);
    }
}

public class ControlAccesoPorCodigo {
    public static void main(String[] args) {
        Set<TarjetaAcceso> tarjetasValidas = new HashSet<>();

        TarjetaAcceso t1 = new TarjetaAcceso("ACC-101", 2);
        tarjetasValidas.add(t1);

        // Ahora es SEGURO mutar el nivel, porque el hash NO cambia.
        t1.setNivelAcceso(5);

        // contains() funciona perfectamente porque el bucket de búsqueda es el mismo.
        System.out.println("¿Tarjeta válida?: " + tarjetasValidas.contains(t1)); // true (CORREGIDO)
        System.out.println("Cantidad: " + tarjetasValidas.size()); // 1
    }
}

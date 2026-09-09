import java.util.*;

class TarjetaAcceso {
    private String codigo;
    private int nivelAcceso;

    public TarjetaAcceso(String codigo, int nivelAcceso) {
        this.codigo = codigo;
        this.nivelAcceso = nivelAcceso;
    }

    //PROBLEMA FUNDAMENTAL: Este setter permite mutar el objeto DESPUÉS de insertarlo en el HashSet.
    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaAcceso tarjeta = (TarjetaAcceso) o;
        // El 'equals' depende del código Y del nivel de acceso.
        return nivelAcceso == tarjeta.nivelAcceso && Objects.equals(codigo, tarjeta.codigo);
    }

    @Override
    public int hashCode() {
        // EL GRAVE PROBLEMA: El hash depende del 'nivelAcceso', que es MUTABLE.
        // Al cambiar el nivel, el hash cambia, pero el objeto ya está guardado en otro bucket.
        return Objects.hash(codigo, nivelAcceso);
    }
}

public class ControlAcceso {
    public static void main(String[] args) {
        Set<TarjetaAcceso> tarjetasValidas = new HashSet<>();

        // 1. Se crea el objeto con nivelAcceso = 2.
        TarjetaAcceso t1 = new TarjetaAcceso("ACC-101", 2);

        // 2. Se inserta en el HashSet.
        //    - Se calcula el hash con nivel=2 -> se guarda en el BUCKET "A".
        tarjetasValidas.add(t1);

        // 3. MUTACIÓN PELIGROSA: El nivel cambia a 5, pero el objeto sigue físicamente en el BUCKET "A".
        //    - Si volvemos a calcular el hash ahora, daría el hash de nivel=5 -> apuntaría al BUCKET "B".
        t1.setNivelAcceso(5);

        // 4. Búsqueda fallida:
        //    - contains() calcula el hash actual (nivel=5) y busca en el BUCKET "B".
        //    - Como el objeto está en el BUCKET "A", NO LO ENCUENTRA.
        System.out.println("¿Tarjeta válida en sistema?: " + tarjetasValidas.contains(t1)); // false (ERROR)

        // 5. El size sigue siendo 1 porque el objeto NUNCA se eliminó, pero es INVISIBLE para el HashSet.
        System.out.println("Cantidad de tarjetas registradas: " + tarjetasValidas.size()); // 1
    }
}

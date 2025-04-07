package model.pessoa;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa{

    private List<ArrayList> turmasMinistradas = new ArrayList<>();
    private LocalDate data;

    public Professor(String nome, String telefone, String email, List<ArrayList> turmasMinistradas) throws Exception {
        super(nome, telefone, email);
        this.turmasMinistradas = turmasMinistradas;
        this.data = LocalDate.now();

    }

    public List<ArrayList> getTurmasMinistradas() {
        return turmasMinistradas;
    }

    public void setTurmasMinistradas(List<ArrayList> turmasMinistradas) {
        this.turmasMinistradas = turmasMinistradas;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

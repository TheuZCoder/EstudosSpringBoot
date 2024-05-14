package escolaprojeto.escola.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class AlunoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String matricula;
    private String senha;

    
    @ManyToMany()
    @JoinTable(name = "aluno_model_disciplinas",
    joinColumns = @JoinColumn(name = "alunos_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "disciplinas_id", referencedColumnName = "id"))
    private Set<DisciplinaModel> disciplinas = new HashSet<>();

   

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<DisciplinaModel> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<DisciplinaModel> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void addDisciplina(DisciplinaModel disciplina) {
        disciplinas.add(disciplina);
        disciplina.getAlunos().add(this);
    }

    public void removeDisciplina(DisciplinaModel disciplina) {
        disciplinas.remove(disciplina);
        disciplina.getAlunos().remove(this);
    }


  
}

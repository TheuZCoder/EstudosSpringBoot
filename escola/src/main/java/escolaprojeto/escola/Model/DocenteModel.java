package escolaprojeto.escola.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class DocenteModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private String senha;

    @JsonIgnore
    @ManyToMany
    private Set<DisciplinaModel> disciplinas = new HashSet<>();


    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
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
    public Set<DisciplinaModel> getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(Set<DisciplinaModel> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void addDisciplina(DisciplinaModel disciplina) {
        disciplinas.add(disciplina);
        disciplina.getDocente().add(this);
    }

    public void removeDisciplina(DisciplinaModel disciplina) {
        disciplinas.remove(disciplina);
        disciplina.getDocente().remove(this);
    }

    
}

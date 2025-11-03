package ac2_project.example.ac2_ca;

import ac2_project.example.ac2_ca.entity.*;


public class Main {
    public static void main(String[] args) {
        Instituicao inst = new Instituicao(1, "PUC", "Av. Central, 100", "12.345.678/0001-90");
        Professor prof = new Professor();
        Curso curso = new Curso(1, "Java Básico", "Aprenda Java do zero", prof, inst);
        Aluno aluno1 = new Aluno(1, "RA456", curso, inst);
        Aluno aluno2 = new Aluno(2, "RA789", curso, inst);

        curso.adicionarAluno(aluno1);
        curso.adicionarAluno(aluno2);
        inst.adicionarCurso(curso);
        inst.adicionarAluno(aluno1);
        inst.adicionarAluno(aluno2);

        System.out.println(inst);
        System.out.println(curso);
        System.out.println(curso.getAlunos());
}

}
package ac2_project.example.ac2_ca;

import ac2_project.example.ac2_ca.entity.*;


public class Main {
    public static void main(String[] args) {
        Instituicao inst = new Instituicao( "PUC", "Av. Central, 100", "12.345.678/0001-90");
        Curso curso = new Curso(null, 0 );
        Aluno aluno1 = new Aluno( "RA456", curso);
        Aluno aluno2 = new Aluno( "RA789", curso);

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
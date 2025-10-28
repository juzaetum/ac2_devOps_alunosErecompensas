package ac2_project.example.ac2_ca.entity;

public class Professor {
        private int id;
        private String ra;
        private Instituicao instituicao;

        public Professor(int id, String ra, Instituicao instituicao) {
            this.id = id;
            this.ra = ra;
            this.instituicao = instituicao;
        }

        public int getId() {
            return id;
        }

        public String getRa() {
            return ra;
        }

        public Instituicao getInstituicao() {
            return instituicao;
        }

        @Override
        public String toString() {
            return "Professor{" +
                    "id=" + id +
                    ", ra='" + ra + '\'' +
                    ", instituicao=" + instituicao.getNome() +
                    '}';
        }
    }


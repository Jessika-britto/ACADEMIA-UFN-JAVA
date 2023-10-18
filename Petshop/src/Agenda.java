public class Agenda {

    private String animal;
    private String servico;
    private String data;
    private String horario;

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String agendar(String data, String horario) {
        if (this.data.equals(data) && this.horario != horario){
            return this.servico;
        }
        return "Agendamento indisponivel, tente outro horario.";
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "animal='" + animal + '\'' +
                ", servico='" + servico + '\'' +
                ", data='" + data + '\'' +
                ", horario='" + horario + '\'' +
                '}';
    }
}

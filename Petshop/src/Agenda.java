import java.util.List;

public class Agenda {

    private String animal;
    private Servico servico;
    private String data;
    private String horario;

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
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

    public String agendar(List<Agenda> agendaList, Agenda agenda) {
        for (Agenda ag : agendaList) {
            if (ag.getData().equals(agenda.data) && ag.getHorario().equals(agenda.horario)) {
                return "Agendamento indisponível, tente outro horário.";
            }
        }
        agendaList.add(agenda);
        return "Agendamento Concluído";
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

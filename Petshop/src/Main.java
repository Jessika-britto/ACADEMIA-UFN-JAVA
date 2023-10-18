import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Produto shampoo = new Produto();
        shampoo.setNome("shampoo");
        shampoo.setCategoria("Cães");
        shampoo.setPreco(17.99);
        shampoo.setQuantidadeEstoque(29);
        shampoo.vender(15);

        Produto racao = new Produto();
        racao.setNome("Ração");
        racao.setCategoria("Cães");
        racao.setPreco(59.99);
        racao.setQuantidadeEstoque(50);
        racao.vender(32);

        Produto camaPet = new Produto();
        camaPet.setNome("Cama pet");
        camaPet.setCategoria("Gatos");
        camaPet.setPreco(89.99);
        camaPet.setQuantidadeEstoque(24);
        camaPet.vender(0);

        List<Produto> produtoList = new ArrayList<>();
        produtoList.add(shampoo);
        produtoList.add(racao);
        produtoList.add(camaPet);

        System.out.println("Lista de produtos: ");

        for (Produto item : produtoList) {
            System.out.println(" - " + item);
        }

        Agenda agenda1 = new Agenda();
        agenda1.setAnimal("Cachorro");
        agenda1.setServico("Tosa");
        agenda1.setData("20/10/2023");
        agenda1.setHorario("09:00");

        Agenda agenda2 = new Agenda();
        agenda2.setAnimal("Gato");
        agenda2.setServico("Consulta Veterinaria");
        agenda2.setData("20/10/2023");
        agenda2.setHorario("10:00");

        Agenda agenda3 = new Agenda();
        agenda3.setAnimal("Ramster");
        agenda3.setServico("Consulta Veterinaria");
        agenda3.setData("22/10/2023");
        agenda3.setHorario("09:00");

        List<Agenda> agendaList = new ArrayList<>();
        agendaList.add(agenda1);
        agendaList.add(agenda2);
        agendaList.add(agenda3);
        System.out.println( "Lista de Agendamento: ");
        for (Agenda agenda : agendaList){
            System.out.println(" - " + agenda);
        }

        Animal animal1 = new Animal();
        animal1.setNome("Papagaio");
        animal1.setEspecie("Aves");
        animal1.setRaca("Cara-roxa");
        animal1.setDataNascimento("13/04/2020");
        animal1.setPoprietario("Maria Helena Brandão");

        Animal animal2 = new Animal();
        animal2.setNome("cachorro");
        animal2.setEspecie("Lobo");
        animal2.setRaca("Malamute do Alasca");
        animal2.setDataNascimento("08/10/2019");
        animal2.setPoprietario("Bianca Nogueira");

        Animal animal3 = new Animal();
        animal3.setNome("Gato");
        animal3.setEspecie("Silvestre");
        animal3.setRaca("Felis Silvestris");
        animal3.setDataNascimento("10/02/2018");
        animal3.setPoprietario("Millena Rodovalho");

        List <Animal> animalList = new ArrayList<>();
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        System.out.println("Lista de animais: ");
        for (Animal animal :animalList){
            System.out.println(" - "+animal);
        }
    }
}
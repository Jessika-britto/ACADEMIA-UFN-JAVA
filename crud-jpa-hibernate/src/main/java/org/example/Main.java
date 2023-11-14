package org.example;

import org.example.entity.Departamento;
import org.example.entity.Role;
import org.example.entity.Usuario;
import org.example.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class Main {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Inserir dados
            transaction.begin();

            Role role = new Role();
            role.setNome("ADMINISTRADOR");

            Departamento departamento = new Departamento();
            departamento.setNome("TI");

            Usuario usuario = new Usuario();
            usuario.setNome("Maria");
            usuario.setSobrenome("Souza Aragão");
            usuario.setEmail("Souzamaria@gmail.com");
            usuario.setSexo("Feminino");
            usuario.getRoles().add(role);
            role.getUsuarios().add(usuario);
            usuario.setDepartamento(departamento);

            em.persist(usuario);
            em.persist(role);
            em.persist(departamento);

            Role role2 = new Role();
            role2.setNome("CLIENTE");

            Departamento departamento2 = new Departamento();
            departamento2.setNome("RH");

            Usuario usuario2 = new Usuario();
            usuario2.setNome("João");
            usuario2.setSobrenome("Lopes Silva");
            usuario2.setEmail("lopesjo@gmail.com");
            usuario2.setSexo("Masculino");
            usuario2.getRoles().add(role2);
            role2.getUsuarios().add(usuario2);
            usuario2.setDepartamento(departamento2);

            em.persist(usuario2);
            em.persist(role2);
            em.persist(departamento2);

            transaction.commit();

            // Consultar e exibir usuários
            Usuario consultarUsuario1 = em.find(Usuario.class, 1L);
            Usuario consultarUsuario2 = em.find(Usuario.class, 2L);
            System.out.println("Usuário 1: " + (consultarUsuario1 != null ? consultarUsuario1.toString() : "N/A"));
            System.out.println("Usuário 2: " + (consultarUsuario2 != null ? consultarUsuario2.toString() : "N/A"));

            // Atualizar dados
            transaction.begin();

            Usuario usuarioAtualizado = em.find(Usuario.class, 1L);
            if (usuarioAtualizado != null) {
                usuarioAtualizado.setNome("Helena");
                usuarioAtualizado.setEmail("helena@gmail.com");
            }

            transaction.commit();

            // Consultar e exibir usuário atualizado
            Usuario consultarUsuarioAtualizado = em.find(Usuario.class, 1L);
            System.out.println("Usuário Atualizado: " + (consultarUsuarioAtualizado != null ? consultarUsuarioAtualizado.toString() : "N/A"));

            // Excluir dados
            transaction.begin();

            Usuario usuarioParaExcluir = em.find(Usuario.class, 2L);
            if (usuarioParaExcluir != null) {
                em.remove(usuarioParaExcluir);
            }

            transaction.commit();

            // Tentar consultar usuário excluído
            Usuario consultarUsuarioExcluido = em.find(Usuario.class, 2L);
            System.out.println("Usuário Excluído: " + (consultarUsuarioExcluido != null ? consultarUsuarioExcluido.toString() : "N/A"));
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            JpaUtil.close();
        }
    }
}

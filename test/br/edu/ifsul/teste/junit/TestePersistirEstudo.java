/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.teste.junit;

import br.edu.ifsul.modelo.Estudo;
import br.edu.ifsul.modelo.Execucao;
import br.edu.ifsul.modelo.Exercicio;
import br.edu.ifsul.modelo.Tecnica;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dalpizzol
 */
public class TestePersistirEstudo {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirEstudo() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("IFSUL-TA-6M1-PU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste() {
        boolean exception = false;

        try {
            
            Estudo estudo = new Estudo();
            estudo.setData(Calendar.getInstance());
            
            Exercicio exercicio = em.find(Exercicio.class, 1);
            
            Execucao execucao1 = new Execucao();
            execucao1.setExercicio(exercicio);
            execucao1.setBpm(120);
            execucao1.setInicio(Calendar.getInstance());
            execucao1.setFim(Calendar.getInstance());
            execucao1.setFeedback("Tocado com som limpo. Execução boa mas com alguns erros de digitação.");
            execucao1.setEstudo(estudo);
            
            Execucao execucao2 = new Execucao();
            execucao2.setExercicio(exercicio);
            execucao2.setBpm(140);
            execucao2.setInicio(Calendar.getInstance());
            execucao2.setFim(Calendar.getInstance());
            execucao2.setFeedback("Tocado com distorção. Execução ainda um pouco suja.");
            execucao2.setEstudo(estudo);
            
            estudo.getExecucoes().add(execucao1);
            estudo.getExecucoes().add(execucao2);
            
            em.getTransaction().begin();
            em.persist(estudo);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        }

        Assert.assertEquals(false, exception);
    }    
    
}

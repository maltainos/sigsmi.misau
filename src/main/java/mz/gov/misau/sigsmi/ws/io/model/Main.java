//package mz.gov.misau.sigsmi.ws.io.model;
//
//import java.time.LocalDateTime;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//public class Main {
//
//	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
//		EntityManager em = emf.createEntityManager();
//		GravidaEntity gravidez = new GravidaEntity();
//		gravidez.setGravidaId("1234mfjsfksk");
//		gravidez.setDataEngravida(LocalDateTime.now());
//		gravidez.setGravidezStatus(GravidezStatus.SAUDAVEL);
//		System.out.println("\n\n\n\n"+gravidez+"\n\n\n");
//		em.getTransaction().begin();
//		em.persist(gravidez);
//		em.getTransaction().commit();
//
//		System.out.println(gravidez);
//		em.close();
//	}
//}

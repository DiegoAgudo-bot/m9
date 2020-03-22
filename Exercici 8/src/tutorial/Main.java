package tutorial;

import javax.persistence.*;
import java.util.*;

public class Main {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
    static EntityManager em = emf.createEntityManager();
    
	public List<Vehicle> retornarVehicles(){
		TypedQuery<Vehicle> query = em.createQuery("SELECT p FROM Vehicle p", Vehicle.class);
		List<Vehicle> results = query.getResultList();
		
        return results;
	}
	
	public List<Propietaris> retornarPropietaris(){
		TypedQuery<Propietaris> query = em.createQuery("SELECT p FROM Propietaris p", Propietaris.class);
        List<Propietaris> results = query.getResultList();
		
        return results;
	}

	public void crearVehicle(long index, long id, int kilometros, String marca, String model, boolean aire) {
		em.getTransaction().begin();
		if(index == 0) {
			Vehicle p = new Vehicle(id, kilometros, model, marca, aire);
			em.persist(p);
		} else {
			Vehicle p = em.find(Vehicle.class, index);
			
			p.setModel(model);
			p.setAireAcondicionat(aire);
			p.setMarca(marca);
			p.setKilometros(kilometros);
			p.setPropietariId(id);
			em.persist(p);
		}
        em.getTransaction().commit();
	}
	
	public void creaPropietari(long index, int edad, String nom, boolean haPagat) {
		em.getTransaction().begin();
		
		if(index == 0) {
			Propietaris p = new Propietaris(edad, nom, haPagat);
			em.persist(p);
		} else {
			Propietaris p = em.find(Propietaris.class, index);
			
			p.setEdad(edad);
			p.setHaPagat(haPagat);
			p.setNom(nom);
			em.persist(p);
		}
		
        em.getTransaction().commit();
	}
	
	public void esborrarRegistre(long id, String tipus) {
		em.getTransaction().begin();
		
		if(tipus.equals("p")) {
			Propietaris p = em.find(Propietaris.class, id);
			em.remove(p);
		} else {
			Vehicle v = em.find(Vehicle.class, id);
			em.remove(v);
		}
		
		em.getTransaction().commit();
		
	}
}
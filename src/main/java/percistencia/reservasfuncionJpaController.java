/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package percistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.reservasfuncion;
import percistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Juan Jose
 */
public class reservasfuncionJpaController implements Serializable {

    public reservasfuncionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(reservasfuncion reservasfuncion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reservasfuncion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(reservasfuncion reservasfuncion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservasfuncion = em.merge(reservasfuncion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reservasfuncion.getId();
                if (findreservasfuncion(id) == null) {
                    throw new NonexistentEntityException("The reservasfuncion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservasfuncion reservasfuncion;
            try {
                reservasfuncion = em.getReference(reservasfuncion.class, id);
                reservasfuncion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservasfuncion with id " + id + " no longer exists.", enfe);
            }
            em.remove(reservasfuncion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<reservasfuncion> findreservasfuncionEntities() {
        return findreservasfuncionEntities(true, -1, -1);
    }

    public List<reservasfuncion> findreservasfuncionEntities(int maxResults, int firstResult) {
        return findreservasfuncionEntities(false, maxResults, firstResult);
    }

    private List<reservasfuncion> findreservasfuncionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(reservasfuncion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public reservasfuncion findreservasfuncion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(reservasfuncion.class, id);
        } finally {
            em.close();
        }
    }

    public int getreservasfuncionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<reservasfuncion> rt = cq.from(reservasfuncion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

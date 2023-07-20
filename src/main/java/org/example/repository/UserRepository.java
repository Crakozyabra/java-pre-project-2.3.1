package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User save(User user) {
        if (Objects.isNull(user.getId())) {
            em.persist(user);
            return user;
        }
        return Objects.isNull(findById(user.getId())) ? null : em.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> getAll() {
        return em.createQuery("FROM User", User.class).getResultList();
    }
}

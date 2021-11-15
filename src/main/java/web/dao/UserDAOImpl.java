package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        String jpql = "SELECT user FROM User user";
        return em.createQuery(jpql, User.class)
                .getResultList();
    }

    @Override
    public void deleteUser(int userId) {
        em.remove(em.find(User.class, userId));
    }

    @Override
    public User updateUser(int id, User user) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setName(user.getName());
        return em.merge(user);
    }

    @Override
    public User getUser(int id) {
        String jpql = "SELECT user FROM User user WHERE user.id = :id";
        return em.createQuery(jpql, User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

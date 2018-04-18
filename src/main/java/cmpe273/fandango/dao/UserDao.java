package cmpe273.fandango.dao;

import cmpe273.fandango.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Save the user in the database.
   */
  public void create(User user) {
    entityManager.persist(user);
  }

  /**
   * Delete the user from the database.
   */
  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
  }

  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List getAll() {
    return entityManager.createQuery("from User").getResultList();
  }

  /**
   * Return the user having the passed email.
   */
  public User getByEmail(String email) {
    return (User) entityManager.createQuery(
        "from User where email = :email")
        .setParameter("email", email)
        .getSingleResult();
  }

  /**
   * Return the user having the passed id.
   */
  public User getById(long id) {
    return entityManager.find(User.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(User user) {
    entityManager.merge(user);
  }
}
import com.sdajava.hibernate.entity.BooksEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.awt.print.Book;
import java.util.List;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(String[] args) {
        listOfBooks();
        addBook();
        deleteBook();

        ourSessionFactory.close();
    }

    private static void listOfBooks() {
        Transaction tx = null;

        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<BooksEntity> books =
                    session.createQuery("FROM " + BooksEntity.class.getName()).list();

            for (BooksEntity book : books) {
                System.out.println(book);
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private static void addBook() {
        Transaction tx = null;

        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();

            BooksEntity booksEntity = new BooksEntity();
            booksEntity.setAuthor("Test2");
            booksEntity.setIsbn("666");

            session.save(booksEntity);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private static void deleteBook() {
        Transaction tx = null;

        try (Session session = ourSessionFactory.openSession()) {
            tx = session.beginTransaction();

            BooksEntity bookToDelete = (BooksEntity) session.load(BooksEntity.class, 2);
            session.delete(bookToDelete);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}

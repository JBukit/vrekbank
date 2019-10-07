package pinMachine.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pinMachine.HibernateUtil;
import pinMachine.model.PaymentData;

public class PaymentDao {

    public void savePayment(PaymentData paymentdata) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction
            transaction = session.beginTransaction();

            // save the pinMachine object

            session.save(paymentdata);

            // commit transaction

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();

            }

            e.printStackTrace();

        }

    }
}

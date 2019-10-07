package pinMachine.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pinMachine.HibernateUtil;
import pinMachine.model.PaymentData;
import pinMachine.model.Transfer;

public class TransferDao {


    public void saveTransfer(Transfer transfer) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction
            transaction = session.beginTransaction();

            // save the pinMachine object

            session.save(transfer);

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

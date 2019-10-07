package pinMachine.model.dao;


import org.hibernate.Session;

import org.hibernate.Transaction;
import pinMachine.controller.ClientPinMachine;
import pinMachine.HibernateUtil;


public class PinMachineDao {

    public void saveClientPinMachine(ClientPinMachine clientPinMachine) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction
            transaction = session.beginTransaction();

            // save the pinMachine object

            session.save(clientPinMachine);

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
//Dit ombouwen voor lijst van betalingen / journaal?
//    public List < Student > getStudents() {
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//            return session.createQuery("from Student", Student.class).list();
//
//        }
//
//    }


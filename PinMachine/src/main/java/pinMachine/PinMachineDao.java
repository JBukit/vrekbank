package pinMachine;


import org.hibernate.Session;

import org.hibernate.Transaction;


public class PinMachineDao //extends CrudRepository<ClientPinMachine, Integer>
{

    public void saveClientPinMachine(ClientPinMachine clientPinMachine) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction
            transaction = session.beginTransaction();

            // save the student object

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


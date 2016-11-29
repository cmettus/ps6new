package base;

	import java.util.ArrayList;
	import java.util.Date;
	import java.util.Iterator;
	import java.util.List;
	import java.util.UUID;

	import org.hibernate.HibernateException;
	import org.hibernate.Query;
	import org.hibernate.Session;
	import org.hibernate.Transaction;

import domain.PersonDomainModel;
import domain.StudentDomainModel;
	import util.HibernateUtil;

	public class PersonDAL {


		/**
		 * addPerson - Method adds a Person to the database
		 * @param stu
		 * @return
		 */
		public static PersonDomainModel addPerson(PersonDomainModel stu) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			int employeeID = 0;
			try {
				tx = session.beginTransaction();
				session.save(stu);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			return stu;
		}
		
		
		public static ArrayList<PersonDomainModel> getPersons() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			StudentDomainModel stuGet = null;		
			ArrayList<PersonDomainModel> stus = new ArrayList<PersonDomainModel>();
			
			try {
				tx = session.beginTransaction();	
				
				List students = session.createQuery("FROM StudentDomainModel").list();
				for (Iterator iterator = students.iterator(); iterator.hasNext();) {
					PersonDomainModel stu = (PersonDomainModel) iterator.next();
					stus.add(stu);

				}
				
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			return stus;
		}		
		
		
		public static PersonDomainModel getPerson(UUID stuID) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			PersonDomainModel stuGet = null;		
			
			try {
				tx = session.beginTransaction();	
										
				Query query = session.createQuery("from StudentDomainModel where studentId = :id ");
				query.setParameter("id", stuID.toString());
				
				List<?> list = query.list();
				stuGet = (PersonDomainModel)list.get(0);
				
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			return stuGet;
		}		
		
		public static void deletePerson(UUID stuID) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			PersonDomainModel stuGet = null;		
			
			try {
				tx = session.beginTransaction();	
										
				PersonDomainModel stu = (PersonDomainModel) session.get(PersonDomainModel.class, stuID);
				session.delete(stu);
			
				
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}

		}	
		

		public static PersonDomainModel updatePerson(PersonDomainModel stu) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			PersonDomainModel stuGet = null;		
			
			try {
				tx = session.beginTransaction();	
										
				session.update(stu);
		
				
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}

			return stu;
		}		
		
		
		
		
		
		
	}



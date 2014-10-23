package com.mywebsite.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mywebsite.models.ReminderBean;

public class ReminderController {
    // connection object for db connection
    private static Connection conn = null;
    private static Statement stmt;
    private static String driver = "org.sqlite.JDBC";
    private static String dbName = "";
    private static String user = "", password = "";
    private static int counter = 0;
    ReminderBean rBean = new ReminderBean();

    // connect to the db
    public static boolean connect() {
	boolean isConnected = false;

	try {
	    // this will load the SQLITE driver, each DB has its own driver
	    Class.forName(driver);
	    // setup the connection with the DB.
	    conn = (Connection) DriverManager
		    .getConnection("jdbc:sqlite:test2.db");

	    conn.setAutoCommit(true);
	    stmt = conn.createStatement();

	    stmt.executeUpdate("drop table if exists AGENDA;");
	    intializeDatabase();

	    isConnected = true;
	} catch (Exception e) {
	    System.out.println("can not connect to database");
	    e.printStackTrace();
	}
	return isConnected;

    }

    private static void intializeDatabase() throws SQLException {
	String sql = "CREATE TABLE AGENDA " + "(ID INTEGER PRIMARY  KEY,"
		+ " NAME            VARCHAR(50)    NOT NULL, "
		+ " STADT           TEXT    NOT NULL, "
		+ " AGE           	TEXT     NOT NULL, "
		+ " ADDRESS         TEXT, " + " FAX       		 INT, "
		+ " HANDY       	 INT, " + " TELEFON         INT, "
		+ " EMAIL       	 TEXT, " + " NOTIZ       	 TEXT)";

	stmt.execute(sql);
	stmt.close();
    }

    public static void save(ReminderBean rBean) {

	String sql = "INSERT INTO AGENDA (NAME,STADT,AGE,ADDRESS,FAX, HANDY, TELEFON, EMAIL, NOTIZ) "
		+ "VALUES (' "
		+ rBean.getTfName()
		+ "', ' "
		+ rBean.getDdStadt()
		+ "',' "
		+ convertDateToString(rBean.getDatePicker())
		+ "',' "
		+ rBean.getTfAdresse()
		+ "' , ' "
		+ rBean.getTfFaxNb()
		+ "','"
		+ rBean.getTfHandy()
		+ "','"
		+ rBean.getTfPhone()
		+ "',' "
		+ rBean.getTfEmail() + "',' " + rBean.getTfNotiz() + "' );";
	try {

	    stmt.executeUpdate(sql);

	} catch (SQLException e) {
	    System.err.println("Something went wrong while saving");
	    e.printStackTrace();
	}

	try {
	    stmt.close();
	} catch (SQLException e) {

	    e.printStackTrace();
	}
    }

    public static List<ReminderBean> load(List<ReminderBean> rBeanList) {
	ResultSet rs;
	// reset list
	rBeanList.clear();
	try {
	    rs = stmt.executeQuery("SELECT * FROM AGENDA;");

	    // ID,NAME,STADT,AGE,ADDRESS,FAX, HANDY, TELEFON, EMAIL, NOTIZ
	    while (rs.next()) {
		ReminderBean rBean = new ReminderBean();
		int id = rs.getInt("ID");
		String name = rs.getString("Name");
		String stadt = rs.getString("STADT");
		String datePicker = rs.getString("AGE");
		int fax = rs.getInt("FAX");
		String adresse = rs.getString("address");
		int handy = rs.getInt("HANDY");
		int telefon = rs.getInt("TELEFON");
		String email = rs.getString("EMAIL");
		String notiz = rs.getString("NOTIZ");

		rBean.setId(id);
		rBean.setTfName(name);
		rBean.setDatePicker(convertStrinfToDate(datePicker));
		rBean.setDdStadt(stadt);
		rBean.setTfFaxNb(String.valueOf(fax));
		rBean.setTfAdresse(adresse);
		rBean.setTfHandy(String.valueOf(handy));
		rBean.setTfPhone(String.valueOf(telefon));
		rBean.setTfEmail(email);
		rBean.setTfNotiz(notiz);
		rBeanList.add(rBean);
	    }
	} catch (SQLException e) {
	    System.err.println("something went wrong!");
	    e.printStackTrace();
	}
	return rBeanList;

    }

    public static void delete(ReminderBean rBean) {
	try {
	    stmt = conn.createStatement();
	    String sql = "DELETE from AGENDA where ID=" + rBean.getId() + ";";
	    stmt.executeUpdate(sql);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public static ResultSet select(String query) {
	ResultSet result = null;

	try {
	    Statement s = (Statement) conn.createStatement();
	    result = s.executeQuery(query);
	} catch (Exception e) {

	}

	return result;

    }

    // display the result of selected query

    public static void showSelecte(ResultSet result) {
	if (result != null)
	    try {

		ResultSetMetaData rsmd = (ResultSetMetaData) result
			.getMetaData();

		int noColumns = rsmd.getColumnCount();

		for (int i = 0; i < noColumns; i++) {

		    System.out.println(rsmd.getColumnName(i));
		}
		System.out.println();

		while (result.next()) {
		    for (int i = 0; i < noColumns; i++) {
			System.out.println(result.getString(i + 1));
			System.out.println();
		    }

		}
	    } catch (Exception e) {

	    }

    }

    public void disconnect() {
	try {
	    conn.close();
	} catch (Exception e) {

	}
    }

    public static String convertDateToString(Date date) {

	if (date != null) {

	    SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat(
		    "dd.MM.yyyy");
	    String date_to_string = dateformatyyyyMMdd.format(date);
	    return date_to_string;
	}
	return null;
    }

    public static Date convertStrinfToDate(String stringDate) {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	Date convertedDate = null;
	try {
	    convertedDate = dateFormat.parse(stringDate);
	} catch (ParseException e) {

	}
	return convertedDate;

    }

    /**
     * Hibernate Code
     */

    public static void speichern(ReminderBean rBean) {

	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	session.save(rBean);
	session.getTransaction().commit();
	session.close();
    }

    public static List<ReminderBean> laden(List<ReminderBean> rBeanList) {
	rBeanList.clear();
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	List<ReminderBean> list = session.createQuery("from ReminderBean").list();
	if (list.size()!=0) {
	    for (Object n : list) {
		ReminderBean rBean = (ReminderBean) n;
		    System.out.println(rBean.getTfName());
		    rBeanList.add(rBean);
		} 
	}
	session.getTransaction().commit();
	session.close();
	

	return rBeanList;
    }
    
    public static void loeschen(ReminderBean rBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(rBean);
		session.getTransaction().commit();
		session.close();
	}

}

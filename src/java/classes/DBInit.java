package classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;


public class DBInit {

    Connection con = null;
    String s = "jdbc:mysql://localhost:3306/project?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";

    public DBInit() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver"); 
            System.out.println("Driver loaded");

//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "egorvadik_101?");
            con = DriverManager.getConnection(s, "root", "egorvadik_101?");

            System.out.println("Connected");

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    public int storeUser(User u) {

        int updated = 0;

        try {

//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "egorvadik_101?");
            con = DriverManager.getConnection(s, "root", "egorvadik_101?");
            System.out.println("Connected");

            PreparedStatement pstmt = con.prepareStatement("insert into user(name, username, email, password) values(?,?,?,?)");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getUsername());
            pstmt.setString(3, u.getEmail());
            pstmt.setString(4, u.getPassword());

            updated = pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return updated;
    }

    public User loginUser(User u) {

        try {
            con = DriverManager.getConnection(s, "root", "egorvadik_101?");

            Statement stmt = con.createStatement();

            ResultSet r = stmt.executeQuery("select * from user");

            if (u.getUsername() != null && u.getPassword() != null) {
                while (r.next()) {
                    if (u.getUsername().equals(r.getString("username")) && u.getPassword().equals(r.getString("password"))) {
                        u.setId(r.getInt("id"));
                        u.setEmail(r.getString("email"));
                        u.setLoggedIn(true);
                        return u;
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return u;
    }

    public int addQuestion(String q, String a, String c) {
        int updated = 0;

        if (q.equals("") || a.equals("") || c.equals("")) {
            return updated;
        }

        try {

            con = DriverManager.getConnection(s, "root", "egorvadik_101?");
            System.out.println("Connected");

            PreparedStatement pstmt = con.prepareStatement("insert into question(q, a, c_name) values(?,?,?)");
            pstmt.setString(1, q);
            pstmt.setString(2, a);
            pstmt.setString(3, c);

            updated = pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return updated;
    }

    public ArrayList<Question> getQuestions(String c_name) {

        try {
            ArrayList<Question> q = new ArrayList<>();

            con = DriverManager.getConnection(s, "root", "egorvadik_101?");

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM question where c_name=? ORDER BY RAND() LIMIT 5");
            pstmt.setString(1, c_name);

            ResultSet r = pstmt.executeQuery();

            while (r.next()) {

                Question qu = new Question();
                System.out.println(r.getString("q"));
                qu.setQ_id(r.getInt("q_id"));
                qu.setQuestion(r.getString("q"));
                qu.setAnswer(r.getString("a"));
                qu.setC_name(r.getString("c_name"));

                q.add(qu);

            }
            return q;

        } catch (SQLException ex) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;

    }

    public boolean checkAnswer(String q, String a) {
        try {

            con = DriverManager.getConnection(s, "root", "egorvadik_101?");

            PreparedStatement pstmt = con.prepareStatement("SELECT a FROM question where q=?");
            pstmt.setString(1, q);

            ResultSet r = pstmt.executeQuery();

            if (r.next()) {
                String ans = r.getString("a");
                return ans.equals(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int addHistory(String u_id, int grade, String course_name) {
        int updated = 0;

        try {

            con = DriverManager.getConnection(s, "root", "egorvadik_101?");
            System.out.println("Connected");
            
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            
            PreparedStatement pstmt = con.prepareStatement("insert into history(u_id, course_name, date, grade) values(?,?,?,?)");
            pstmt.setInt(1, Integer.parseInt(u_id));
            pstmt.setString(2, course_name);
            pstmt.setTimestamp(3, timestamp);
            pstmt.setInt(4, grade);

            updated = pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return updated;
        
    }
    
    public ArrayList<History> getHistory(int u_id) {
        try {
            ArrayList<History> historys = new ArrayList<>();

            con = DriverManager.getConnection(s, "root", "egorvadik_101?");

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM history where u_id=?");
            pstmt.setInt(1, u_id);

            ResultSet r = pstmt.executeQuery();

            while (r.next()) {

                History h = new History();
                h.setU_id(u_id);
                h.setGrade(r.getInt("grade"));
                h.setC_name(r.getString("course_name"));
                h.setDate(r.getTimestamp("date"));
                historys.add(h);

            }
            return historys;

        } catch (SQLException ex) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

}

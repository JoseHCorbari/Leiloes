
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class conectaDAO {
    
    public static Connection connectDB() throws ClassNotFoundException{
       Connection conn = null;
       
        
        try {    
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?&user=root&password=tntdeus147&serverTimezone=America/Sao_Paulo");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}

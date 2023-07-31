import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        try {
            conn = new conectaDAO().connectDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            int status;
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }  
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}


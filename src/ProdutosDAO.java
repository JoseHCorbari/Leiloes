
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public int cadastrarProduto(ProdutosDTO produto) {

        try {
            conn = new conectaDAO().connectDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        int status;
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com Sucesso");
            return status;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Produto não foi cadastrado");
            System.out.println("Produto Não Inserido, Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }

    }

    public int venderProduto(ProdutosDTO venda) {

        try {
            conn = new conectaDAO().connectDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        int status;
        try {
            prep = conn.prepareStatement("UPDATE produtos SET status = ? where id = ? ");
            prep.setString(1, venda.getStatus());
            prep.setInt(2, venda.getId());
            status = prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto Eitado com Sucesso");
            return status;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Produto não foi Editado");
            System.out.println("Produto Não Editado, Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        try {
            conn = new conectaDAO().connectDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from produtos";

        try {
            prep = conn.prepareStatement(sql);

            resultset = prep.executeQuery();
            listagem = new ArrayList<>();

            while (resultset.next()) {
                ProdutosDTO prodDTO = new ProdutosDTO();
                prodDTO.setId(resultset.getInt("id"));
                prodDTO.setNome(resultset.getString("nome"));
                prodDTO.setValor(resultset.getInt("valor"));
                prodDTO.setStatus(resultset.getString("status"));
                listagem.add(prodDTO);
            }
            return listagem;
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir: " + ex.getMessage());
            return null;
        }
    }

}

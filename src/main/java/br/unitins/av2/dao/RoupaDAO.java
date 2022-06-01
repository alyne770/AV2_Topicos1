package br.unitins.av2.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.av2.model.Marca;
import br.unitins.av2.model.Roupa;

public class RoupaDAO implements DAO<Roupa> {
	
	@Override
	public boolean insert(Roupa obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO roupa  (");
		sql.append("  peca, ");
		sql.append("  tamanho, ");
		sql.append("  cor, ");
		sql.append("  data_recebimento, ");
		sql.append("  preco, ");
		sql.append("  estoque, ");
		sql.append("  id_marca ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?,  ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getPeca());
			stat.setString(2, obj.getTamanho());
			stat.setString(3, obj.getCor());
			stat.setDate(4, Date.valueOf(obj.getData_Recebimento()));
			if (obj.getPreco() == null)
				stat.setDouble(5, (Double)0.0);
			else 
				stat.setDouble(5, obj.getPreco());
			
			if (obj.getEstoque() == null)
				stat.setInt(6, 0);
			else 
				stat.setInt(6, obj.getEstoque());
			if (obj.getMarca() == null || obj.getMarca().getId() == null)
				stat.setObject(7, null);
			else
				stat.setInt(7, obj.getMarca().getId());


			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean update(Roupa obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE roupa SET  ");
		sql.append("  peca = ?, ");
		sql.append("  tamanho = ?, ");
		sql.append("  cor = ?, ");
		sql.append("  data_recebimento = ?, ");
		sql.append("  preco = ?, ");
		sql.append("  estoque = ?, ");
		sql.append("  id_marca = ? ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getPeca());
			stat.setString(2, obj.getTamanho());
			stat.setString(3, obj.getCor());
			stat.setDate(4, Date.valueOf(obj.getData_Recebimento()));
			stat.setDouble(5, obj.getPreco());
			stat.setInt(6, obj.getEstoque());
			
			if (obj.getMarca() == null || obj.getMarca().getId() == null)
				stat.setObject(7, null);
			else
				stat.setInt(7, obj.getMarca().getId());
			
			stat.setInt(8, obj.getId());
		
		
			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;

	}

	@Override
	public boolean delete(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM roupa ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Roupa> getAll() {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Roupa> lista = new ArrayList<Roupa>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  r.id, ");
		sql.append("  r.peca, ");
		sql.append("  r.tamanho, ");
		sql.append("  r.cor, ");
		sql.append("  r.data_recebimento, ");
		sql.append("  r.preco, ");
		sql.append("  r.estoque, ");
		sql.append("  r.id_marca, ");
		sql.append("  m.nome AS nome_marca, ");
		sql.append("  m.datalancamento ");
		sql.append("FROM ");
		sql.append("  roupa r LEFT JOIN marca m ON m.id = r.id_marca ");
		sql.append("ORDER BY ");
		sql.append("  r.peca ");;
		
		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Roupa roupa = new Roupa();
				roupa.setId(rs.getInt("id"));
				roupa.setPeca(rs.getString("peca"));
				roupa.setTamanho(rs.getString("tamanho"));
				roupa.setCor(rs.getString("cor"));
				roupa.setData_Recebimento(rs.getDate("data_recebimento").toLocalDate());
				roupa.setPreco(rs.getDouble("preco"));
				roupa.setEstoque(rs.getInt("estoque"));
				roupa.setMarca(new Marca());
				if (rs.getObject("id_marca") != null) {
					roupa.getMarca().setId(rs.getInt("id_marca"));
					roupa.getMarca().setNome(rs.getString("nome_marca"));
					roupa.getMarca().setData_Lancamento(rs.getDate("datalancamento").toLocalDate());
					
				}

				lista.add(roupa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = null;
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Roupa getById(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		Roupa roupa = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  r.id, ");
		sql.append("  r.peca, ");
		sql.append("  r.tamanho, ");
		sql.append("  r.cor, ");
		sql.append("  r.data_recebimento, ");
		sql.append("  r.preco, ");
		sql.append("  r.estoque, ");
		sql.append("  r.id_marca, ");
		sql.append("  m.nome AS nome_marca, ");
		sql.append("  m.datalancamento ");
		sql.append("FROM ");
		sql.append("  roupa r LEFT JOIN marca m ON m.id = r.id_marca ");
		sql.append("WHERE ");
		sql.append("  r.id = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			while (rs.next()) {
			    roupa = new Roupa();
				roupa.setId(rs.getInt("id"));
				roupa.setPeca(rs.getString("peca"));
				roupa.setTamanho(rs.getString("tamanho"));
				roupa.setCor(rs.getString("cor"));
				roupa.setData_Recebimento(rs.getDate("data_recebimento").toLocalDate());
				roupa.setPreco(rs.getDouble("preco"));
				roupa.setEstoque(rs.getInt("estoque"));
				roupa.setMarca(new Marca());
				if (rs.getObject("id_marca") != null) {
					roupa.getMarca().setId(rs.getInt("id_marca"));
					roupa.getMarca().setNome(rs.getString("nome_marca"));
					roupa.getMarca().setData_Lancamento(rs.getDate("datalancamento").toLocalDate());
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roupa;
	}

	public List<Roupa> getByPeca(String peca) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Roupa> lista = new ArrayList<Roupa>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  r.id, ");
		sql.append("  r.peca, ");
		sql.append("  r.tamanho, ");
		sql.append("  r.cor, ");
		sql.append("  r.data_recebimento, ");
		sql.append("  r.preco, ");
		sql.append("  r.estoque, ");
		sql.append("  r.id_marca, ");
		sql.append("  m.nome AS nome_marca, ");
		sql.append("  m.datalancamento ");
		sql.append("FROM ");
		sql.append("  roupa r LEFT JOIN marca m ON m.id = r.id_marca ");
		sql.append("WHERE ");
		sql.append(" r.peca iLIKE ? ");
		sql.append("ORDER BY ");
		sql.append("  r.peca ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + peca + "%");

			rs = stat.executeQuery();
			while (rs.next()) {
				Roupa roupa = new Roupa();
				roupa.setId(rs.getInt("id"));
				roupa.setPeca(rs.getString("peca"));
				roupa.setTamanho(rs.getString("tamanho"));
				roupa.setCor(rs.getString("cor"));
				roupa.setData_Recebimento(rs.getDate("data_recebimento").toLocalDate());
				roupa.setPreco(rs.getDouble("preco"));
				roupa.setEstoque(rs.getInt("estoque"));
				roupa.setMarca(new Marca());
				if (rs.getObject("id_marca") != null) {
					roupa.getMarca().setId(rs.getInt("id_marca"));
					roupa.getMarca().setNome(rs.getString("nome_marca"));
					roupa.getMarca().setData_Lancamento(rs.getDate("datalancamento").toLocalDate());
				}
				
				lista.add(roupa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = null;
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}

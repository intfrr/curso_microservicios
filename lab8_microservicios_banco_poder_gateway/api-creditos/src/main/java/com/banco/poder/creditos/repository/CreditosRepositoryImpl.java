package com.banco.poder.creditos.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.banco.poder.creditos.SpringJdbcDao;
import com.banco.poder.creditos.modelo.CreditosDto;

@Repository
public class CreditosRepositoryImpl extends SpringJdbcDao implements CreditosRepository {

	private String qryInsert = "INSERT INTO creditos (id,fecha,id_pais,id_canal,id_sucursal,"
			+ "id_cliente,id_producto,monto,plazo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private String qryBuscarById = "SELECT * FROM creditos a WHERE a.id_credito = ?";
	
	private String qryBuscarByIdTodo = "SELECT * FROM creditos a, credito_referencias b WHERE a.id_credito, b.id_credito = ?";

	private String qryUpdate = "UPDATE creditos set id_pais = ?, id_canal = ?, id_sucursal = ?, id_cliente = ?,"
			+ " id_producto = ?, monto = ?, plazo = ? WHERE id_credito = ?";

	private String qryDelete = "DELETE FROM creditos WHERE id_credito = ?";

	@Override
	public CreditosDto buscarById(String id) {
		try {
			return jdbcTemplate.queryForObject(qryBuscarById, new Object[] { id },
					BeanPropertyRowMapper.newInstance(CreditosDto.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public CreditosDto actualizar(String id, CreditosDto creditosDto) {

		jdbcTemplate.update(qryUpdate, creditosDto.getFecha(), creditosDto.getPais(), creditosDto.getCanal(),
				creditosDto.getSucursal().getId(), creditosDto.getCliente(), creditosDto.getProducto(),
				creditosDto.getMonto(), creditosDto.getPlazo(), creditosDto.getIdCredito());

		return buscarById(creditosDto.getIdCredito());
	}

	@Override
	public CreditosDto persistir(CreditosDto creditosDto) {

		creditosDto.setIdCredito(generadorLlaves());

		jdbcTemplate.update(qryInsert, creditosDto.getIdCredito(), creditosDto.getFecha(), creditosDto.getPais(),
				creditosDto.getCanal(), creditosDto.getSucursal().getId(), creditosDto.getCliente(),
				creditosDto.getProducto(), creditosDto.getMonto(), creditosDto.getPlazo());

		return buscarById(creditosDto.getIdCredito());
	}

	@Override
	public void eliminar(String id) {
		jdbcTemplate.update(qryDelete, id);
	}

	@Override
	public CreditosDto buscarByIdTodo(String id) {
		try {
			return jdbcTemplate.queryForObject(qryBuscarByIdTodo, new Object[] { id },
					BeanPropertyRowMapper.newInstance(CreditosDto.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

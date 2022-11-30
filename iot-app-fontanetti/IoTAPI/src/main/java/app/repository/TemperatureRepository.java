package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import app.connection.ConnectionFactory;
import app.models.Temperature;

public class TemperatureRepository {
	
	private Connection conn;
	
	public TemperatureRepository() {
		conn = ConnectionFactory.getConnection();
	}

	public Temperature register(Temperature temperature) {
		
		PreparedStatement statement = null;
		int id = 0;
		
		try {
			String sql = "INSERT INTO tb_temperature (temperature, timestamp) VALUES(?, ?)";
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setDouble(1, temperature.getTemperature());
			statement.setTimestamp(2, Timestamp.valueOf(temperature.getTimestamp()));
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet ids = statement.getGeneratedKeys();
				
				while (ids.next()) {
					id = ids.getInt(1);
					System.out.println("Ok! ids generated: " + id);
				}
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return this.findById(id);
	}

	public Integer update(Temperature temperature) {
		
		PreparedStatement statement = null;
				
		
		
		try {
			String sql = "UPDATE tb_temperature "
					+ "SET temperature = ?, timestamp = ? WHERE id = ?";
			statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, temperature.getTemperature());
			statement.setTimestamp(2, Timestamp.valueOf(temperature.getTimestamp()));
			statement.setInt   (3, temperature.getId());
			
			statement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	public Integer deleteById(Integer id) {
		
		PreparedStatement statement = null;
		
		try {
			
			String sql = "DELETE FROM tb_temperature WHERE (id = ?)";
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			int rowsAffected = statement.executeUpdate();
			
			System.out.println("Rows affected: " + rowsAffected);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return id;
	}


	public List<Temperature> findAll() {
		
		List<Temperature> temperatures = new ArrayList<Temperature>();
		
		PreparedStatement statement = null;
		ResultSet result = null;
	
		try {
			
			statement = conn.prepareStatement("SELECT * FROM tb_temperature");
			result = statement.executeQuery();
			
			while(result.next()) {
				
				Integer id = result.getInt("id");
				Double temperature = result.getDouble("temperature");

				LocalDateTime timestamp = result.getTimestamp("timestamp").toLocalDateTime();
				
				Temperature temp = new Temperature(id, temperature, timestamp);
				
				temperatures.add(temp);
			}
			
			
		} catch (SQLException e) {
		
			throw new RuntimeException(e.getMessage());
		
		} finally {
						
			try {
				statement.close();
				result.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return temperatures;

	
	}

	public Temperature findById(Integer id) {
		
		PreparedStatement statement = null;
		ResultSet result = null;
		Temperature temperature = null;
		
		try {
			
			statement = conn.prepareStatement("SELECT * FROM tb_temperature WHERE id = ?");
			
			statement.setInt(1, id);

			result = statement.executeQuery();
			
			while(result.next()) {
				
				Integer resultId = result.getInt("id");
				Double resultTemp = result.getDouble("temperature");
				LocalDateTime timestamp = result.getTimestamp("timestamp").toLocalDateTime();
				
				temperature = new Temperature(resultId, resultTemp, timestamp);				
			}
			
			
		} catch (SQLException e) {
		
			throw new RuntimeException(e.getMessage());
		
		}
		
		return temperature;
	}
		
	public List<Temperature> getByTimeInterval(String limite) {
		
		List<Temperature> temperatures = new ArrayList<Temperature>();
		
		PreparedStatement statement = null;
		ResultSet result = null;
		
		if (limite == null) {
			limite = "1000";
		}
	
		try {
			
			String sql = "SELECT * FROM tb_temperature t"
					+ " LIMIT "
					+ limite;


			statement = conn.prepareStatement(sql);
			//statement.setString(1, ini);
			//statement.setString(2, end);
			//statement.setString(1, limite);
						
			result = statement.executeQuery();
			
			while(result.next()) {
				
				Integer id = result.getInt("id");
				Double temperature = result.getDouble("temperature");

				LocalDateTime timestamp = result.getTimestamp("timestamp").toLocalDateTime();
				
				Temperature temp = new Temperature(id, temperature, timestamp);
				
				temperatures.add(temp);
			}
			
			
		} catch (SQLException e) {
		
			throw new RuntimeException(e.getMessage());
		
		} finally {
						
			try {
				statement.close();
				result.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return temperatures;

	
	}

}

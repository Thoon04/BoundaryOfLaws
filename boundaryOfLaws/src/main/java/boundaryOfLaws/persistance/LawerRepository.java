package boundaryOfLaws.persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import boundaryOfLaws.models.Lawer;

public class LawerRepository {
		public static Connection con=null;
		static {
			con= MyConnection.getConnection();
		}
		//insert
		public int add(Lawer lawer) {
			int result=0;
			String sql = "INSERT INTO lawer(name,email,password,NRC,phone,gender,current_state,pre_case,case_type,price,photo) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, lawer.getName());
				ps.setString(2, lawer.getEmail());
				ps.setString(3, lawer.getPassword());
				ps.setString(4, lawer.getNRC());
				ps.setString(5, lawer.getPhone());
				ps.setString(6, lawer.getGender());
				ps.setString(7, lawer.getCurrent_state());
				ps.setString(8, lawer.getPre_case());
				ps.setString(9, lawer.getCase_type());
				ps.setString(10, lawer.getPrice());
				ps.setString(11, lawer.getPhoto());

				
				result=ps.executeUpdate();
			}catch(SQLException e){
				result=0;
				System.out.println("Lawer insert error:" + e);
			}
			return result;
		}
		
		//update
		public int edit(Lawer lawer) {
			int result = 0;
			String sql = "UPDATE lawer Set name=?,email=?,password=?,NRC=?,phone=?,gender=?,current_state=?,pre_case=?,case_type=?,price=?,photo=?  where id=?";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, lawer.getName());
				ps.setString(2, lawer.getEmail());
				ps.setString(3, lawer.getPassword());
				ps.setString(4, lawer.getNRC());
				ps.setString(5, lawer.getPhone());
				ps.setString(6, lawer.getGender());
				ps.setString(7, lawer.getCurrent_state());
				ps.setString(8, lawer.getPre_case());
				ps.setString(9, lawer.getCase_type());
				ps.setString(10, lawer.getPrice());
				ps.setString(11, lawer.getPhoto());
				ps.setInt(12, lawer.getId());
				result=ps.executeUpdate();
			}catch(SQLException e){
				result=0;
				System.out.println("Lawer Edit error:" + e);
			}
			return result;
		}
		
		//delete
		public int delete(int id) {
			int result = 0;
			String sql = "DELETE FROM lawer WHERE id=?";
			try {
				PreparedStatement ps= con.prepareStatement(sql);
				ps.setInt(1, id);
				
				result = ps.executeUpdate();
			}catch(SQLException e){
				result = 0;
				System.out.println("Lawer Delete error:" + e);
			}return result;	
		}
		
		//getall
		public List<Lawer>getAll(){
			List<Lawer> lawers = new ArrayList<>();
			String sql = "SELECT * FROM lawer";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next()) {
					 Lawer lawer = new Lawer();
					 lawer.setId(rs.getInt("id"));
					 lawer.setName(rs.getString("name"));
					 lawer.setEmail(rs.getString("email"));
					 lawer.setPassword(rs.getString("password"));
					 lawer.setNRC(rs.getString("NRC"));
					 lawer.setPhone(rs.getString("phone"));
					 lawer.setGender(rs.getString("gender"));
					 lawer.setCurrent_state(rs.getString("current_state"));
					 lawer.setPre_case(rs.getString("pre_case"));
					 lawer.setCase_type(rs.getString("case_type"));
					 lawer.setPrice(rs.getString("price"));
					 lawer.setPhoto(rs.getString("photo"));

					 lawers.add(lawer);

				 }
			}catch(SQLException e) {
				System.out.println("Lawer select error:" + e);
			}
			return lawers;
		}
		
		//getOne
		public Lawer getById(int id){
			Lawer lawer = new Lawer();
			String sql = "SELECT * FROM lawer WHERE id=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next()) {
					 lawer.setId(rs.getInt("id"));
					 lawer.setName(rs.getString("name"));
					 lawer.setEmail(rs.getString("email"));
					 lawer.setPassword(rs.getString("password"));
					 lawer.setNRC(rs.getString("NRC"));
					 lawer.setPhone(rs.getString("phone"));
					 lawer.setGender(rs.getString("gender"));
					 lawer.setCurrent_state(rs.getString("current_state"));
					 lawer.setPre_case(rs.getString("pre_case"));
					 lawer.setCase_type(rs.getString("case_type"));
					 lawer.setPrice(rs.getString("price"));
					 lawer.setPhoto(rs.getString("photo"));
				 }
			}catch(SQLException e) {
				lawer = null;
				System.out.println("Author select one error:" + e);
			}
			return lawer;
		}
	}





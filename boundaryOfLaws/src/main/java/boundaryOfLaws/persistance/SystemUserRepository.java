package boundaryOfLaws.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import boundaryOfLaws.models.SystemUser;



public class SystemUserRepository {
	
		public static Connection con=null;
		static {
			con = MyConnection.getConnection();
		}
		
		public int add(SystemUser user) { 
			int result=0;
			
			String sql="INSERT INTO user(name,password,email,phone) "
					+ "VALUES(?,?,?,?)";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());			
				ps.setString(4, user.getPhone());
				
				result=ps.executeUpdate();
			
				
				
			}catch(SQLException e) {
				result=0;
				System.out.println("insert error: "+e);
			}
			
			return result;
		}
		
		public int edit(SystemUser user) {
			int result=0;
			String sql="UPDATE user SET id=?,name=?,password=?,email=?,phone=?";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, user.getName());				
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());				
				ps.setString(4, user.getPhone());
				result=ps.executeUpdate();
				
			}catch(SQLException e) {
				result=0;
				System.out.println("System user edit error: "+e);
			}
			return result;
		}
		public int delete(Integer id) {
			int result=0;
			String sql="DELETE FROM user WHERE id=?";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1 , id);
				result=ps.executeUpdate();
				
			}catch(SQLException e) {
				result=0;
				System.out.println("System User delete err: "+e);
			}
			return result;
		}
		
		public List<SystemUser>getAll(){
			
			List<SystemUser> users=new ArrayList<>();
			String sql="SELECT * FROM systemuser";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					SystemUser user = new SystemUser();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));				
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					
				}
			}catch(SQLException e) {
				System.out.println("System User select error: "+e);
			}
			return users;
		}
		
		public SystemUser getById(Integer id) {
			SystemUser user=null;
			
			String sql="SELECT * FROM systemuser WHERE id=?";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				user=new SystemUser();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));				
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
			}
			}catch(SQLException e) {
				System.out.println("System user select err: "+e);
			}
			return user;
		}
}

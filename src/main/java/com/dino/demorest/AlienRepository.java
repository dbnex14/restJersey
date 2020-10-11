package com.dino.demorest;

import java.util.ArrayList;
import java.util.List;

// for jdbc
import java.sql.*;


public class AlienRepository {

	Connection con = null;
	
	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb?serverTimezone=UTC"; //mySql url for my restdb database
		String username = "root";
		String password = "Bratunac1";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // load driver
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public List<Alien> getAliens(){
		List<Alien> aliens = new ArrayList<Alien>();
		
		//jdbc
		String sql = "select * from alien";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1)); //column1 = id
				a.setName(rs.getNString(2)); //column2 = name
				a.setPoints(rs.getInt(3)); //column3 = points
				aliens.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
		return aliens;
	}
	
	public Alien getAlien(int id) {
		Alien a = new Alien();
		
		//jdbc
		String sql = "select * from alien where id = " + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				a.setId(rs.getInt(1)); //column1 = id
				a.setName(rs.getNString(2)); //column2 = name
				a.setPoints(rs.getInt(3)); //column3 = points
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
		return a;
	}
	
	public void addAlien(Alien a) {
		//jdbc
		String sql = "insert into alien values(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a.getId());
			st.setString(2, a.getName());
			st.setInt(3, a.getPoints());
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
	}
}

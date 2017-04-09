package db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;

public class ClientManager {
	
	public ArrayList<ClientDB> getAllClients() throws IOException {
		ArrayList<ClientDB> clientList = new ArrayList<ClientDB> ();
		ClientDB client = null;
		
		String query = "SELECT * FROM " + client.TABLE_NAME;
		
		DBConnection db = new DBConnection();
		
		try {
			db.select(query);
			
			ResultSet result = db.getResult();
			
			while(result.next()) {
				client = new ClientDB();
				client.setClientID(result.getInt(client.CLIENT_ID));
				client.setName(result.getString(client.CLIENT_NAME));
				
				clientList.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return clientList;
	}
	
	public boolean addClient(ClientDB client) throws IOException {
		boolean result;
		
		String query = "INSERT INTO" + client.TABLE_NAME + " (" + client.CLIENT_ID + ", " 
		+ client.CLIENT_NAME + ") VALUES ('" + client.getClientID() + "', '" + client.getName() + "')";
		
		DBConnection db = new DBConnection();
		
		try {
			result = db.editRow(query);
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return result;
	}
	
	public boolean deleteClient(ClientDB client) throws IOException {
		boolean result;
		
		String query = "DELETE FROM " + client.TABLE_NAME + " WHERE " 
						+ client.CLIENT_ID + " = '" + client.getClientID() +"'";
		
		DBConnection db = new DBConnection();
		
		try {
			result = db.editRow(query);
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return result;
	}
	
	public boolean updateClient(ClientDB client) throws IOException {
		boolean result;
		
		String query = "UPDATE " + client.TABLE_NAME +
					   "SET " + client.CLIENT_ID + " = '" + client.getClientID()
					   + "' WHERE " + client.CLIENT_NAME + " = '" + client.getName() + "'";
		
		DBConnection db = new DBConnection();
		
		try {
			result = db.editRow(query);
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return result;
	}
	
	public ClientDB getClient(int client_id, String name) throws IOException {
		
		ClientDB client = null;
		ResultSet result;
		
		String query = "SELECET * FROM " + client.TABLE_NAME + " WHERE " +
						client.CLIENT_ID + " = '" + client_id + "' AND " +
						client.CLIENT_NAME + " = '" + name + "'";
		
		DBConnection db = new DBConnection();
		
		try {
			
			db.select(query);
			
			result = db.getResult();
			
			result.next();
			client = new client();
			client.setClientID(result.getInt(client.CLIENT_ID));
			client.setName(result.getString(client.CLIENT_NAME));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db != null) {
				db.close();
			}
		}
		
		return client;
	}
}

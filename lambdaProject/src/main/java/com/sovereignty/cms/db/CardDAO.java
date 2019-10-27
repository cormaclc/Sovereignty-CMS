package com.sovereignty.cms.db;

import java.sql.*;

import com.sovereignty.cms.model.Card;

public class CardDAO {
	java.sql.Connection conn;
	
	public CardDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}
	
// Return null if card does not exist
	public Card getCardByRecipientAndEventType(String recipient, String eventType) throws Exception{
		try {
			Card card = null;
// TODO update table name	
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cards WHERE recipient = ? AND eventType = ?;");
			ps.setString(1,  recipient);
			ps.setString(2,  eventType);

            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                card = generateCard(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return card;
		} catch (Exception e) {
			throw new Exception("Could not get card");
		}
	}
	
	public boolean addCard(Card card) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO cards "
					+ "(uuid, recipient, eventType, orientation) "
					+ "values(?,?,?,?);");
            ps.setString(1,  card.getCardID());
            ps.setString(2,  card.getRecipient());
            ps.setString(3, card.getEvent());
            ps.setString(4, card.getOrientation());
            ps.execute();
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
		
	}
	
    private Card generateCard(ResultSet res) throws Exception {
    	String uuid = res.getString("uuid");
        String recipient  = res.getString("recipient");
        String eventType = res.getString("eventType");
        String orientation = res.getString("orientation");
        
        return new Card (uuid, recipient, eventType, orientation);
    }
}

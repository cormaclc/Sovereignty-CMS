package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Card;



public class CardsDAO {

	java.sql.Connection conn;

    public CardsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
        	System.out.println("FALED TO CONNECT TO DATABASE");
    	}
    }

    public Card getCard(String cardID) throws Exception {
        
        try {
            Card card = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cards WHERE cardID=?;");
            ps.setString(1,  cardID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                card = generateCard(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return card;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
//    public boolean updateCard(Card card) throws Exception {
//        try {
//        	String query = "UPDATE constants SET value=? WHERE name=?;";
//        	PreparedStatement ps = conn.prepareStatement(query);
//            ps.setDouble(1, card.value);
//            ps.setString(2, card.name);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//            
//            return (numAffected == 1);
//        } catch (Exception e) {
//            throw new Exception("Failed to update report: " + e.getMessage());
//        }
//    }
    
    public boolean deleteCard(Card card) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Cards WHERE cardID = ?;");
            ps.setString(1, card.getCardID());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to deletce Card: " + e.getMessage());
        }
    }


    public boolean addCard(Card card) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cards WHERE cardID = ?;");
            ps.setString(1, card.getCardID());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Card c = generateCard(resultSet);
                resultSet.close();
                return false;
            }

            // TODO : add individual Page IDS 
            ps = conn.prepareStatement("INSERT INTO Cards (cardID,eventType,recipient,orientation) values(?,?,?,?);");
            ps.setString(1,  card.getCardID());
            ps.setString(2,  card.getEventType().name());
            ps.setString(3,  card.getRecipient());
            ps.setString(4,  card.getOrientation().name());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public List<Card> getAllCards() throws Exception {
        
        List<Card> allCards = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Cards";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Card c = generateCard(resultSet);
                allCards.add(c);
            }
            resultSet.close();
            statement.close();
            return allCards;

        } catch (Exception e) {
            throw new Exception("Failed in getting cards: " + e.getMessage());
        }
    }
    
    private Card generateCard(ResultSet resultSet) throws Exception {
        String cardID  = resultSet.getString("cardID");
        String recipient  = resultSet.getString("recipient");
        Card.EventType eventType = Enum.valueOf(Card.EventType.class,resultSet.getString("eventType"));
        Card.Orientation orientation = Enum.valueOf(Card.Orientation.class, resultSet.getString("orientation"));
        return new Card (cardID, eventType, recipient, orientation);
    }
	
}

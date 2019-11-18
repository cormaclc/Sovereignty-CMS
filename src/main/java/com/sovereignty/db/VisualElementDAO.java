package com.sovereignty.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sovereignty.model.Card;
import com.sovereignty.model.VisualElement;

public class VisualElementDAO {
	java.sql.Connection conn;

	final static int UNCHANGED = 0;
	final static int CHANGED = 1;
	final static int DELETE = 2;

	public VisualElementDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}
	}
	
	public VisualElement getVisualElementByID(String eltID) throws Exception {
		VisualElement ve = new VisualElement();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Elements WHERE eltID = ?;");
			ps.setString(1, eltID);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				ve = generateVisualElement(resultSet);
			}
			resultSet.close();
			ps.close();
			return ve;
		} catch (Exception e) {
			throw new Exception("Failed getting all cards: " + e.getMessage());
		}
	}

	public List<VisualElement> getVisualElementByPageID(String pageID) throws Exception {
		List<VisualElement> allVisualElements = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Elements WHERE pageID = ?;");
			ps.setString(1, pageID);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				VisualElement ve = generateVisualElement(resultSet);
				allVisualElements.add(ve);
			}
			resultSet.close();
			ps.close();
			return allVisualElements;

		} catch (Exception e) {
			throw new Exception("Failed getting all cards: " + e.getMessage());
		}
	}

	private VisualElement generateVisualElement(ResultSet res) throws SQLException {
		VisualElement ve = new VisualElement();
		if (res.getString("eltType").toLowerCase().equals("text")) {
			ve.setImageURL("");
			ve.setText(res.getString("text"));
			ve.setFont(res.getString("font"));
		} else if (res.getString("eltType").toLowerCase().equals("image")) {
			ve.setImageURL(res.getString("imageURL"));
			ve.setText("");
			ve.setFont("");
		}

		ve.setEltID(res.getString("eltID"));
		ve.setEltType(res.getString("eltType"));
		ve.setxPosition(res.getInt("xPosition"));
		ve.setyPosition(res.getInt("yPosition"));
		ve.setHeight(res.getInt("height"));
		ve.setWidth(res.getInt("width"));
		ve.setPageID(res.getString("pageID"));

		return ve;
	}

	VisualElement update(VisualElement ve) throws Exception {
		try {
			switch (ve.getUpdated()) {
			case CHANGED:
				updateVisualElement(ve);
				break;
			case DELETE:
				deleteVisualElement(ve.getEltID());
				break;
			default:
				break;
			}
			
			// RESET isUpdated if it was successful
			ve.setUpdated(UNCHANGED);

			return ve;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to insert page: " + e.getMessage());
		}
	}

	private VisualElement updateVisualElement(VisualElement ve) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("REPLACE INTO Elements "
					+ "(eltID, eltType, xPosition, yPosition, height, width, text, font, imageURL, pageID) "
					+ "values(?,?,?,?,?,?,?,?,?,?);");
			ps.setString(1, ve.getEltID());
			ps.setString(2, ve.getEltType());
			ps.setInt(3, ve.getxPosition());
			ps.setInt(4, ve.getyPosition());
			ps.setInt(5, ve.getHeight());
			ps.setInt(6, ve.getWidth());
			ps.setString(7, ve.getText());
			ps.setString(8, ve.getFont());
			ps.setString(9, ve.getImageURL());
			ps.setString(10, ve.getPageID());
			
			
			int numAffected = ps.executeUpdate();
			// TODO Better error checking here. Not sure the best way to handle this. 
			if(numAffected <= 0) {
				throw new Exception("No Visual Elements were updated with that ID");
			}
			
			return ve;
		} catch (Exception e) {
			throw new Exception("Failed to insert page: " + e.getMessage());
		}
	}

	private boolean deleteVisualElement(String eltID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Elements WHERE eltID = ?;");
			ps.setString(1, eltID);
			int numAffected = ps.executeUpdate();
			ps.close();
			
			return (numAffected == 1);

		} catch (Exception e) {
			throw new Exception("Failed to delete Element: " + e.getMessage());
		}
	}
	
}

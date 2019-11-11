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

	public VisualElementDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}
	}
	
	public List<VisualElement> getVisualElementByPageID(String pageID) throws Exception{
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

    	}catch (Exception e) {
    		throw new Exception("Failed getting all cards: "+ e.getMessage());
    	}    	
	}

	private VisualElement generateVisualElement(ResultSet res) throws SQLException {
		VisualElement ve = new VisualElement();
		if (res.getString("eltType").equals("text")){
			ve.setText(res.getString("text"));
			ve.setFont(res.getString("font"));
		}else if (res.getString("eltType").equals("image")) {
			ve.setHeight(res.getInt("height"));
			ve.setWidth(res.getInt("width"));
			ve.setImageURL(res.getString("imageURL"));
		}
		ve.setEltType(res.getString("eltType"));
		ve.setxPosition(res.getInt("xPosition"));
		ve.setyPosition(res.getInt("yPosition"));
		ve.setPageID(res.getString("pageID"));
		
		return ve;
	}
}

package com.sovereignty.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sovereignty.model.Page;
import com.sovereignty.model.VisualElement;

public class PageDAO {
	java.sql.Connection conn;

	public PageDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}
	}
	
	public Page getPageByID(String pageID)throws Exception{
		try {
			Page page = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pages WHERE pageID = ?;");
			ps.setString(1, pageID);
			
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                page = generatePage(resultSet);
            }
            resultSet.close();
            ps.close();
    		
            return page;
		} catch (Exception e) {
			throw new Exception("Could not get page");
		}
	}

	private Page generatePage(ResultSet res) throws Exception{
		String pageID = res.getString("pageID");
		int isModifiable = res.getInt("isModifiable") ;
		List<VisualElement> listVE = new VisualElementDAO().getVisualElementByPageID(pageID);
		
		return new Page(pageID, isModifiable, listVE);
	}
}


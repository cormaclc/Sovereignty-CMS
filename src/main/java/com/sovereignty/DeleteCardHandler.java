package com.sovereignty;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sovereignty.db.CardDAO;
import com.sovereignty.db.PageDAO;
import com.sovereignty.http.DeleteCardRequest;
import com.sovereignty.http.DeleteCardResponse;
import com.sovereignty.model.Card;

public class DeleteCardHandler implements RequestHandler<DeleteCardRequest, DeleteCardResponse> {
	CardDAO cardDao = new CardDAO();
	PageDAO pageDao = new PageDAO();

	public String validateDeleteCardRequest(DeleteCardRequest input) {
		if (input.getCardID() == null || input.getCardID().isEmpty()) {
			return "cardID required";
		}
		return null;
	}

	@Override
	public DeleteCardResponse handleRequest(DeleteCardRequest input, Context context) {
		context.getLogger().log("Input: " + input);
		try {
			String validationError = this.validateDeleteCardRequest(input);
			if (validationError != null) {
				return new DeleteCardResponse(400, validationError);
			}

			Card to_delete = cardDao.getCardByID(input.getCardID());
			
			boolean cardDeleted = cardDao.deleteCard(input.getCardID());

			boolean frontDeleted = pageDao.deletePage(to_delete.getFrontPage().getPageID());
			boolean leftDeleted = pageDao.deletePage(to_delete.getLeftPage().getPageID());
			boolean rightDeleted = pageDao.deletePage(to_delete.getRightPage().getPageID());
			
			
			if (!cardDeleted || !frontDeleted || !leftDeleted || !rightDeleted) {
				return new DeleteCardResponse(500, "failed deleting Card " + input.getCardID());
			}
			

			return new DeleteCardResponse(200, "successfully deleted Card " + input.getCardID());
		} catch (Exception e) {
			context.getLogger().log(e.getMessage());
			return new DeleteCardResponse(500, e.getMessage());
		}

	}

}

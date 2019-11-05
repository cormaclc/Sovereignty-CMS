import uuidv1 from 'uuid/v1'

const UNCHANGED = 0;
const UPDATED = 1;
const DELETED = 2;

class Card {
    constructor(eventType, recipient, orientation, frontPage, leftPage, rightPage, backPage) {
        this.cardID = uuidv1();
        this.eventType = eventType;
        this.recipient = recipient;
        this.orientation = orientation;
        this.frontPage = frontPage;
        this.leftPage = leftPage;
        this.rightPage = rightPage;
        this.backPage = backPage
    }
}

class Page {
    constructor(elements, isModifiable) {
        this.uuid = uuidv1();
        this.elements = elements;
        this.isModifiable = isModifiable;
    }
}

class VisualElement {
    constructor(updated, eltType, xPos, yPos, height, width, text, font, imageUrl) {
        this.uuid = uuidv1();
        this.updated = updated;
        this.eltType = eltType;
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.text = text;
        this.font = font;
        this.imageUrl = imageUrl;
    }
}

export {Card, Page, VisualElement, UNCHANGED, UPDATED, DELETED}
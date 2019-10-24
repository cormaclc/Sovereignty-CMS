// eslint-disable-next-line
import {Card, Page, VisualElement, UNCHANGED, UPDATED, DELETED} from './classes'

//const baseUrl = '...'
let testCards = JSON.stringify([
    new Card('Wedding', 'Sam', 'Landscape', 0,0,0,0),
    new Card('Birthday', 'Richard', 'Landscape', 0,0,0,0),
    new Card('Anniversary', 'Jack', 'Portrait', 0,0,0,0)
])

const createCard = (initCard) => {

    let frontPage = new Page([], true)
    let leftPage = new Page([], true)
    let rightPage = new Page([], true)
    let backPage = new Page([], false)
    let card = new Card(initCard.eventType, initCard.recipient, initCard.orientation, frontPage, leftPage, rightPage, backPage)

    let jsonCard = JSON.stringify(card)

    //TODO: post card to API
    console.log(jsonCard)

    //test code:
    let tCards = JSON.parse(testCards);
    tCards.push(card)
    testCards = JSON.stringify(tCards)
    console.log(tCards)
}

const getCards = () => {
    //TODO: get from API
    let cards = JSON.parse(testCards);
    
    console.log(cards)
    return cards;
}

const deleteCard = (uuid) => {
    //TODO: api call delete card

    //Test code:
    let tCards = JSON.parse(testCards)
    let filtered  = tCards.filter(function(value, index, arr) {
        return value.uuid !== uuid
    })
    testCards = JSON.stringify(filtered)
}

export {createCard, getCards, deleteCard}
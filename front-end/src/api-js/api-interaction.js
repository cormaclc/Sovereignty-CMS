// eslint-disable-next-line
import {Card, Page, VisualElement, UNCHANGED, UPDATED, DELETED} from './classes'
import uuidv1 from 'uuid/v1'

const baseUrl = 'https://ezsx1v4va5.execute-api.us-east-1.amazonaws.com/alpha'

// eslint-disable-next-line
let testCards = JSON.stringify([
    new Card('Wedding', 'Sam', 'Landscape', 0,0,0,0),
    new Card('Birthday', 'Richard', 'Landscape', 0,0,0,0),
    new Card('Anniversary', 'Jack', 'Portrait', 0,0,0,0)
])

let allCards = []

const createCard = (initCard) => {

    // let frontPage = new Page([], true)
    // let leftPage = new Page([], true)
    // let rightPage = new Page([], true)
    // let backPage = new Page([], false)
    // let card = new Card(initCard.eventType, initCard.recipient, initCard.orientation, frontPage, leftPage, rightPage, backPage)

    initCard.cardID = uuidv1().substring(0, 19)

    let jsonCard = JSON.stringify(initCard)
    console.log(jsonCard)

    var xhr = new XMLHttpRequest();
    xhr.open("POST", baseUrl+'/createCard', true);
  
    // send the collected data as JSON
    xhr.send(jsonCard);
  
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
      console.log(xhr);
      console.log(xhr.request);
      if (xhr.readyState === XMLHttpRequest.DONE) {
        console.log(xhr.responseText);
      } else {
        console.log('error');
      }
    };

    // //test code:
    // let tCards = JSON.parse(testCards);
    // tCards.push(card)
    // testCards = JSON.stringify(tCards)
    // console.log(tCards)
}

const getCards = () => {
    // let cards = JSON.parse(testCards);
    
    var xhr = new XMLHttpRequest();
    xhr.open("GET", baseUrl+'/allCards', true);
    xhr.send();
    
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            console.log (xhr.responseText);
            allCards = JSON.parse(xhr.responseText).cards
            console.log(allCards)
            return allCards;
        } else {
            console.log('error')
            return allCards;
        }
    };

    //return allCards;
}

const deleteCard = (cardID) => {
    console.log(cardID)
    var xhr = new XMLHttpRequest();
    xhr.open("POST", baseUrl+`/deleteCard/${cardID}`, true);

    // send the collected data as JSON
    xhr.send();

    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState === XMLHttpRequest.DONE) {
            console.log ("XHR:" + xhr.responseText);
        } else {
            console.log('error');
        }
    };

    // //Test code:
    // let tCards = JSON.parse(testCards)
    // let filtered  = tCards.filter(function(value, index, arr) {
    //     return value.uuid !== uuid
    // })
    // testCards = JSON.stringify(filtered)
}

export {createCard, getCards, deleteCard, baseUrl}
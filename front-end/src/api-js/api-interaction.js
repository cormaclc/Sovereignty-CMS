// eslint-disable-next-line
import {Card, Page, VisualElement, UNCHANGED, UPDATED, DELETED} from './classes'

//const baseUrl = '...'

const createCard = (initCard) => {

    let frontPage = new Page([], true)
    let leftPage = new Page([], true)
    let rightPage = new Page([], true)
    let backPage = new Page([], false)
    let card = new Card(initCard.eventType, initCard.recipient, initCard.orientation, frontPage, leftPage, rightPage, backPage)

    let jsonCard = JSON.stringify(card)

    //TODO: post card to API
    console.log(jsonCard)
}

export {createCard}
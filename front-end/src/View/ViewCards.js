import React from 'react'
import styled from 'styled-components';
import { deleteCard } from '../api-js/api-interaction'
import { Button } from 'react-bootstrap'
import AddCard from '../Add/AddCard'
import {Link} from 'react-router-dom'
import { baseUrl } from '../api-js/api-interaction'
import DuplicateCard from '../Add/DuplicateCard';

const ListItem = styled.div`
    
    background-color: ${props => props.selected ? `#343a40` : `none`}; 

    :hover {
        background-color: #343a40;
        cursor: pointer;
    }
`

function ViewCards() {

    const [cards, setCards] = React.useState([])
    const [selectedCard, setSelectedCard] = React.useState({cardID: ''})
    const [showDuplicate, setShowDuplicate] = React.useState(false)

    const handleDelete = (cardID) => {
        deleteCard(cardID)
    }

    React.useEffect(() => {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", baseUrl+'/allCards', true);
        xhr.send();
    
        // This will process results and update HTML as appropriate. 
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                let allCards = JSON.parse(xhr.responseText).cards
                setCards(allCards)
            } else {
                console.log('error')  
            }
        };
    }, [cards]);

    const duplicateCard = (c) => {
        // console.log(c)

        let jsonCard = JSON.stringify(c)

        var xhr = new XMLHttpRequest();
        xhr.open('POST', baseUrl+'/duplicateCard', true)
        xhr.send(jsonCard);
            
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                console.log(xhr.responseText);
            } else {
                console.log('error');
            }
        };
    }

    return (
        <div className='w-75'>
            <AddCard setCards={setCards}></AddCard>
            <DuplicateCard duplicateCard={duplicateCard} card={selectedCard} show={showDuplicate} setShow={setShowDuplicate}/>
            <div className='mt-4 row bg-dark font-weight-bold'>
                <div className='col-sm'>Recipient</div>
                <div className='col-sm'>Event Type</div>
                <div className='col-sm'>Orientation</div>
                {/* <div className='col-sm'></div> */}
            </div>
            {cards.map((c) => {
                return (
                    <ListItem selected={selectedCard.cardID === c.cardID} className='row p-1' key={c.cardID} onClick={() => {setSelectedCard(c)}}>
                        <div className='col-sm'>{c.recipient}</div>
                        <div className='col-sm'>{c.eventType}</div>
                        <div className='col-sm'>{c.orientation}</div>               
                        {/* <div className='col-sm'>
                            <Link to={`editCard/${c.cardID}`}>
                                <Button variant='outline-warning' className='mr-2'>Edit</Button>
                            </Link>
                            <Button variant='outline-success' className='mr-2' onClick={() => {alert(`${window.location.host}/viewCard/${c.cardID}`)}}>Link</Button>
                            <Button variant='outline-info' className='mr-2' onClick={() => duplicateCard(c)}>Duplicate</Button>
                            <Button variant='outline-danger' onClick={() => {if(window.confirm(`Are you sure you want to delete the ${c.eventType} card for ${c.recipient}?`)){handleDelete(c.cardID)}}}>Delete</Button>
                        </div> */}
                    </ListItem>
                )
            })}     
            <br/>
            <Link onClick={e => selectedCard.cardID === '' ? e.preventDefault() : ''} to={`editCard/${selectedCard.cardID}`}>
                <Button disabled={selectedCard.cardID === ''} variant='outline-warning' className='mr-2'>Edit</Button>
            </Link>
            <Button disabled={selectedCard.cardID === ''} variant='outline-success' className='mr-2' onClick={() => {alert(`${window.location.host}/viewCard/${selectedCard.cardID}`)}}>Link</Button>
            <Button disabled={selectedCard.cardID === ''} variant='outline-info' className='mr-2' onClick={() => setShowDuplicate(true)}>Duplicate</Button>
            <Button disabled={selectedCard.cardID === ''} variant='outline-danger' onClick={() => {if(window.confirm(`Are you sure you want to delete the ${selectedCard.eventType} card for ${selectedCard.recipient}?`)){handleDelete(selectedCard.cardID)}}}>Delete</Button>
        </div>
    )
}

export default ViewCards
import React from 'react'
import { deleteCard } from '../api-js/api-interaction'
import { Button } from 'react-bootstrap'
import AddCard from '../Add/AddCard'
import {Link} from 'react-router-dom'
import { baseUrl } from '../api-js/api-interaction'


function ViewCards() {

    const [cards, setCards] = React.useState([])

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
    }, []);

    return (
        <div className='container'>
            <AddCard setCards={setCards}></AddCard>
            <div className='mt-4 row bg-dark font-weight-bold'>
                <div className='col-sm'>Recipient</div>
                <div className='col-sm'>Event Type</div>
                <div className='col-sm'>Orientation</div>
                <div className='col-sm'></div>
            </div>
            {cards.map((c) => {
                return (
                    <div className='row p-1' key={c.cardID}>
                        <div className='col-sm'>{c.recipient}</div>
                        <div className='col-sm'>{c.eventType}</div>
                        <div className='col-sm'>{c.orientation}</div>               
                        <div className='col-sm'>
                            <Link to={`editCard/${c.cardID}`}>
                                <Button variant='outline-warning' className='mr-2'>Edit</Button>
                            </Link>
                            <Button variant='outline-danger' onClick={() => {if(window.confirm(`Are you sure you want to delete the ${c.eventType} card for ${c.recipient}?`)){handleDelete(c.cardID)}}}>Delete</Button>
                        </div>
                    </div>
                )
            })}            
        </div>
    )
}

export default ViewCards
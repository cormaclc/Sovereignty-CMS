import React from 'react'
import { getCards, deleteCard } from '../api-js/api-interaction'
import { Button } from 'react-bootstrap'
import AddCard from '../Add/AddCard'

function ViewCards() {

    const [cards, setCards] = React.useState(getCards)

    const handleDelete = (uuid) => {
        deleteCard(uuid)
        setCards(getCards)
    }

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
                    <div className='row p-1' key={c.uuid}>
                        <div className='col-sm'>{c.recipient}</div>
                        <div className='col-sm'>{c.eventType}</div>
                        <div className='col-sm'>{c.orientation}</div>
                        <div className='col-sm'>
                            <Button variant='outline-danger' onClick={() => handleDelete(c.uuid)}>Delete</Button>
                        </div>
                    </div>
                )
            })}            
        </div>
    )
}

export default ViewCards
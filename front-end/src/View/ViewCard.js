import React from 'react';
import { useParams } from 'react-router-dom'
import {Tabs, Tab} from 'react-bootstrap'
import { baseUrl } from '../api-js/api-interaction'
import ViewPage from './ViewPage.js'

const initialCard = {
    'frontPage': {
        'listVisualElements': []
    },
    'leftPage': {
        'listVisualElements': []
    },
    'rightPage': {
        'listVisualElements': []
    },
    'backPage': {
        'listVisualElements': []
    }
}

function ViewCard() {
    const {cardID} = useParams();
    const [card, setCard] = React.useState(initialCard)

    React.useEffect(() => {

        var xhr = new XMLHttpRequest();
        xhr.open("GET", `${baseUrl}/getCard/${cardID}`, true);
        xhr.send();
    
        // This will process results and update HTML as appropriate. 
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                let card = JSON.parse(xhr.responseText).card
                setCard(card)
            } else {
                console.log('error')  
            }
        };
        
    }, [cardID]) // only re-run if cardID changes

    return (
        <div className="card-wrapper">
            <h1>{card.recipient}: {card.eventType}</h1>
            <Tabs defaultActiveKey="front">
                <Tab eventKey="front" title="Front" className='w-100'>
                    <ViewPage page={card.frontPage} landscape={card.orientation} pageName='frontPage'/>
                </Tab>
                <Tab eventKey="left" title="Left" className="tab">
                    <ViewPage page={card.leftPage} landscape={card.orientation} pageName='leftPage'/>
                </Tab>
                <Tab eventKey="right" title="Right">
                    <ViewPage page={card.rightPage} landscape={card.orientation} pageName='rightPage'/>
                </Tab>
                <Tab eventKey="back" title="Back">
                    <ViewPage page={card.backPage} landscape={card.orientation} pageName='backPage'/>
                </Tab>
            </Tabs>
        </div>
    )   
}

export default ViewCard
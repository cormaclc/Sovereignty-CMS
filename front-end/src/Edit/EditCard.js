import React from 'react';
import { useParams } from 'react-router-dom'
import './EditCard.css'
import EditPage from './EditPage'
import {Tabs, Tab, Button} from 'react-bootstrap'
import AddCard from '../Add/AddElement'
import EditElement from './EditElement'
//eslint-disable-next-line      
import { baseUrl } from '../api-js/api-interaction'

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

function EditCard() {
    const {cardID} = useParams();
    const [card, setCard] = React.useState(initialCard)
    const [showAddElement, setShowAddElement] = React.useState(false)
    const [positionChanges, setPositionChanges] = React.useState({'frontPage': [], 'leftPage': [], 'rightPage': []})
    const [selectedElt, setSelectedElt] = React.useState({})
    const [update, setUpdate] = React.useState(0)

    React.useEffect(() => {

        var xhr = new XMLHttpRequest();
        xhr.open("GET", `${baseUrl}/getCard/${cardID}`, true);
        xhr.send();
    
        // This will process results and update HTML as appropriate. 
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                let card = JSON.parse(xhr.responseText).card
                setCard(card)
                setPositionChanges({
                    'frontPage': card.frontPage.listVisualElements.map(e => {return({eltID: e.eltID, x: 0, y: 0})}),
                    'leftPage': card.leftPage.listVisualElements.map(e => {return({eltID: e.eltID, x: 0, y: 0})}),
                    'rightPage': card.rightPage.listVisualElements.map(e => {return({eltID: e.eltID, x: 0, y: 0})}),
                })
            } else {
                console.log('error')  
            }
        };
    }, [cardID]) // only re-run if cardID changes

    const saveCard = () => {

        let c = card;

        const updatePositions = (page) => {
            let arr = []
            positionChanges[page].forEach(e => {
                let matching_el = c[page].listVisualElements.filter(function(el) { return el.eltID === e.eltID; })[0];
                if ((e.x !== 0 || e.y !== 0) && matching_el.updated !== 2) {
                    matching_el.xPosition = matching_el.xPosition + e.x;
                    matching_el.yPosition = matching_el.yPosition + e.y; 
                    matching_el.updated = 1;
                }
                arr.push(matching_el)
            })
            return arr;
        }
        
        c.frontPage.listVisualElements = updatePositions('frontPage');
        c.leftPage.listVisualElements = updatePositions('leftPage');
        c.rightPage.listVisualElements = updatePositions('rightPage');

        setCard(c)

        let jsonCard = JSON.stringify(card)

        var xhr = new XMLHttpRequest();
        xhr.open('POST', baseUrl+'/updateCard', true);
    
        // send the collected data as JSON
        xhr.send(jsonCard);
    
        // This will process results and update HTML as appropriate. 
        xhr.onloadend = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                console.log(xhr.responseText);
                setPositionChanges({
                    'frontPage': card.frontPage.listVisualElements.map(e => {return({eltID: e.eltID, x: 0, y: 0})}),
                    'leftPage': card.leftPage.listVisualElements.map(e => {return({eltID: e.eltID, x: 0, y: 0})}),
                    'rightPage': card.rightPage.listVisualElements.map(e => {return({eltID: e.eltID, x: 0, y: 0})}),
                })
                alert('Card Saved')
            } else {
                console.log('error');
            }
        };
    }

    const addElement = (eltInfo, page) => {

        let c = card;
        let pc = positionChanges;
        let p = ''
        if (page === 'Front Page') {
            c.frontPage.listVisualElements.push(eltInfo)
            p = 'frontPage'
        } else if (page === 'Left Page') {
            c.leftPage.listVisualElements.push(eltInfo)
            p = 'leftPage'
        } else {
            c.rightPage.listVisualElements.push(eltInfo)
            p = 'rightPage'
        }

        pc[p].push({
            eltID: eltInfo.eltID,
            x: 0,
            y: 0
        })

        setCard(c)
        setPositionChanges(pc)
        setShowAddElement(false)
    }

    const openAddElement = () => {
        setShowAddElement(true)
    }
    const closeAddElement = () => {
        setShowAddElement(false)
    }

    const updateElt = (elt) => {
        let c = card;
        c['frontPage'].listVisualElements.forEach(e => {
            if(e.eltID === elt.eltID){ 
                e = elt
            }
        })
        c['leftPage'].listVisualElements.forEach(e => {
            if(e.eltID === elt.eltID){ 
                e = elt 
            }
        })
        c['rightPage'].listVisualElements.forEach(e => {
            if(e.eltID === elt.eltID){ 
                e = elt 
            }
        })
        setCard(c)
        setUpdate(update + 1)
    } 
    const deleteElt = (eltID) => {
        let c = card;
        c['frontPage'].listVisualElements.forEach(e => {
            if(e.eltID === eltID){ 
                e.updated = 2;
            }
        })
        c['leftPage'].listVisualElements.forEach(e => {
            if(e.eltID === eltID){ 
                e.updated = 2;
            }
        })
        c['rightPage'].listVisualElements.forEach(e => {
            if(e.eltID === eltID){ 
                e.updated = 2;
            }
        })
        setCard(c)
        setUpdate(update + 1)
    }

    const updateEltPos = (elID, newCoords, pageName) => {

        let pc = positionChanges;
        pc[pageName].forEach(el => {
            console.log(elID)
            if(el.eltID === elID) {
                el.x = el.x + newCoords.x
                el.y = el.y + newCoords.y
                console.log()
            }
        });

        setPositionChanges(pc)
        newCoords = {}

    }

    const handleElementSelect = (elID, pageName) => {
        let elt = {}
        card[pageName].listVisualElements.forEach(e => {
            if(e.eltID === elID) {
                elt = e
            }
        })
        setSelectedElt(elt)
    }

    return (
        <div className="card-wrapper">
            <h1>{card.recipient}: {card.eventType}</h1>
            <Tabs defaultActiveKey="front">
                <Tab eventKey="front" title="Front" className='w-100'>
                    <EditPage selectedID={selectedElt.eltID} page={card.frontPage} update={update} landscape={card.orientation} pageName='frontPage' updatePosition={updateEltPos} handleElementSelect={handleElementSelect}/>
                </Tab>
                <Tab eventKey="left" title="Left" className="tab">
                    <EditPage selectedID={selectedElt.eltID} page={card.leftPage} update={update} landscape={card.orientation} pageName='leftPage' updatePosition={updateEltPos} handleElementSelect={handleElementSelect}/>
                </Tab>
                <Tab eventKey="right" title="Right">
                    <EditPage selectedID={selectedElt.eltID} page={card.rightPage} update={update} landscape={card.orientation} pageName='rightPage' updatePosition={updateEltPos} handleElementSelect={handleElementSelect}/>
                </Tab>
                <Tab eventKey="back" title="Back">
                    <EditPage selectedID={selectedElt.eltID} page={card.backPage} update={update} landscape={card.orientation} pageName='backPage' updatePosition={updateEltPos} handleElementSelect={handleElementSelect}/>
                </Tab>
            </Tabs>
            <div className="w-50 d-flex justify-content-between mb-2">
                <Button className="btn-success" onClick={saveCard}>Save Card</Button>
                <EditButton elt={selectedElt} updateElt={updateElt} deleteElt={deleteElt}/>
                <Button className="btn-success" onClick={openAddElement}>Add Element</Button>
            </div>
            <AddCard card={card} show={showAddElement} addElement={addElement} handleClose={closeAddElement}/>
        </div>
    )   
}

function EditButton(props) {
    const [show, setShow] = React.useState(false)

    const showEditModal = () => {
        setShow(true)
    }
    const hideEditModal = () => {
        setShow(false)
    }
    const updateElt = (elt) => {
        hideEditModal()
        props.updateElt(elt)
    }
    const deleteElt = (eltID) => {
        hideEditModal()
        props.deleteElt(eltID)
    }

    if(Object.entries(props.elt).length !== 0) {
        return (
            <div>
                <Button className='btn-warning' onClick={showEditModal}>Edit Element</Button>
                <EditElement show={show} hide={hideEditModal} elt={props.elt} updateElt={updateElt} deleteElt={deleteElt}/>
            </div>
        )
    } else {
        return (
            <div/>
        )  
    }
}

export default EditCard

import React from 'react';
import {Modal, Button, Form} from 'react-bootstrap'

const eventTypeOptions = ['Anniversary', 'Back to School', 'Baptism and Christening', 'Baby', 'Bar/Bat Mitzvah', 'Birthday',
    'Confirmation', 'Congratulations', 'Encouragement', 'First Communion', 'Get Well', 'Graduation', 'Retirement', 'Sympathy', 
    'Teacher Appreciation', 'Thank You', 'Wedding']

function DuplicateCard(props) {
    const [show, setShow] = React.useState(false)
    const [recipient, setRecipient] = React.useState('')
    const [eventType, setEventType] = React.useState('Anniversary')

    React.useEffect(() => {
        setRecipient(props.card.recipient)
        setEventType(props.card.eventType);
        setShow(props.show);
    }, [props.card, props.show])

    const handleClose = () => { setShow(false); props.setShow(false) }

    const handleRecipientChange = (e) => { setRecipient(e.target.value) }
    const handleEventChange = (e) => { setEventType(e.target.value) } 

    const handleDuplicateCard = () => {
        let c = props.card;
        c.recipient = recipient
        c.eventType = eventType
    
        props.duplicateCard(c);

        handleClose();

    }

    return (
        <div>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Duplicate Card</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="form-recipient">
                            <Form.Label>Recipient</Form.Label>
                            <Form.Control required value={recipient} onChange={handleRecipientChange}/>
                        </Form.Group>
                        
                        <Form.Group controlId="form-event-type">
                            <Form.Label>Event Type</Form.Label>
                            <Form.Control as="select" onChange={handleEventChange}>
                                {eventTypeOptions.map((eto) => {
                                    return (
                                        <option defaultValue={eto === eventType} key={eto}>{eto}</option>
                                    )
                                })}
                            </Form.Control>
                        </Form.Group>
                        
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='danger' onClick={handleClose}>Cancel</Button>
                    <Button variant='success' onClick={handleDuplicateCard}>Duplicate Card</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}

export default DuplicateCard;

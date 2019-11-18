import React from 'react'
import {Modal, Form, Button} from 'react-bootstrap'
import uuidv1 from 'uuid/v1'

function AddElement(props) {
    const [eltType, setEltType] = React.useState('Text')
    const [height, setHeight] = React.useState(0)
    const [width, setWidth] = React.useState(0)
    const [text, setText] = React.useState('')
    const [font, setFont] = React.useState('Times New Roman')
    const [imageUrl, setImageUrl] = React.useState('')
    const [page, setPage] = React.useState('Front Page')

    const handleEltTypeChange = (e) => { setEltType(e.target.value) }
    const handleHeightChange = (e) => { setHeight(e.target.value) }
    const handleWidthChange = (e) => { setWidth(e.target.value) }
    const handleTextChange = (e) => { setText(e.target.value) }
    const handleFontChange = (e) => { setFont(e.target.value) }
    const handleImageUrlChange = (e) => { setImageUrl(e.target.value) }
    const handlePageChange = (e) => { setPage(e.target.value) }

    const add = () => {
        
        let pageID = ''
        if (page === 'Front Page') {
            pageID = props.card.frontPage.pageID
        } else if (page === 'Left Page') {
            pageID = props.card.leftPage.pageID
        } else {
            pageID = props.card.rightPage.pageID
        }

        let eltInfo = {
            'eltID': uuidv1().substring(0, 19),
            'updated': 1,
            'eltType': eltType.toLowerCase(),
            'xPosition': 0,
            'yPosition': 0,
            'height': height,
            'width': width,
            'text': text,
            'font': font,
            'imageUrl': imageUrl,
            'pageID': pageID,
        }

        props.addElement(eltInfo, page)
    }

    return (
        <div>
            <Modal show={props.show} onHide={props.handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Element</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="form-elt-type">
                            <Form.Label>Element Type</Form.Label>
                            <Form.Control as="select" onChange={handleEltTypeChange}>
                                <option defaultValue>Text</option>
                                <option>Image</option>
                            </Form.Control>
                        </Form.Group>

                        <div className='d-flex justify-content-between'>
                            <Form.Group controlId="form-height">
                                <Form.Label>Height</Form.Label>
                                <Form.Control required placeholder='Height' onChange={handleHeightChange}/>
                            </Form.Group>

                            <Form.Group controlId="form-width">
                                <Form.Label>Width</Form.Label>
                                <Form.Control required placeholder="Width" onChange={handleWidthChange}/>
                            </Form.Group>
                        </div>

                        <ElementConditional eltType={eltType} handleTextChange={handleTextChange} handleFontChange={handleFontChange} handleImageUrlChange={handleImageUrlChange}/>

                        <Form.Group controlId="form-page">
                            <Form.Label>Page</Form.Label>
                            <Form.Control as="select" onChange={handlePageChange}>
                                <option defaultValue>Front Page</option>
                                <option>Left Page</option>
                                <option>Right Page</option>
                            </Form.Control>
                        </Form.Group>

                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='danger' onClick={props.handleClose}>Cancel</Button>
                    <Button variant='success' onClick={add}>Add Element</Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

function ElementConditional(props) {

    if(props.eltType.toLowerCase() === 'text') {
        return (
            <div>
                <Form.Group controlId="form-text">
                    <Form.Label>Text</Form.Label>
                    <Form.Control required placeholder="Text" onChange={props.handleTextChange}/>
                </Form.Group>

                <Form.Group controlId="form-font">
                    <Form.Label>Font</Form.Label>
                    <Form.Control as="select" onChange={props.handleFontChange}>
                        <option defaultValue>Times New Roman</option>
                        <option>Arial</option>
                        <option>Comic Sans MS</option>
                    </Form.Control>
                </Form.Group>
            </div>
        )
    } else {
        return (
            <div>
                Need to do Image Stuff
            </div>
        )
    }
}

export default AddElement
import React from 'react'
import { Modal, Form, Button } from 'react-bootstrap'

function EditElement(props) {
    const [height, setHeight] = React.useState(0)
    const [width, setWidth] = React.useState(0)
    const [text, setText] = React.useState('')
    const [font, setFont] = React.useState('Times New Roman')
    const [imageUrl, setImageUrl] = React.useState('')

    const handleHeightChange = (e) => { setHeight(e.target.value) }
    const handleWidthChange = (e) => { setWidth(e.target.value) }
    const handleTextChange = (e) => { setText(e.target.value) }
    const handleFontChange = (e) => { setFont(e.target.value) }
    const handleImageUrlChange = (e) => { setImageUrl(e.target.value) }

    React.useEffect(() => {
        setHeight(props.elt.height)
        setWidth(props.elt.width)
        setText(props.elt.text)
        setFont(props.elt.font)
        setImageUrl(props.elt.imageURL)
    }, [props.elt])

    const update = () => {
        let elt = props.elt;
        elt['height'] = parseFloat(height)
        elt['width'] = parseFloat(width)
        elt['text'] = text
        elt['font'] = font
        elt['imageUrl'] = imageUrl
        elt['updated'] = 1

        props.updateElt(elt)
    }

    return (
        <div>
            <Modal show={props.show} onHide={props.hide}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit Element</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className='d-flex justify-content-between'>
                        <Form.Group controlId="form-height">
                            <Form.Label>Height</Form.Label>
                            <Form.Control required value={height} onChange={handleHeightChange}/>
                        </Form.Group>

                        <Form.Group controlId="form-width">
                            <Form.Label>Width</Form.Label>
                            <Form.Control required value={width} onChange={handleWidthChange}/>
                        </Form.Group>
                    </div>

                    <DependentInput type={props.elt.eltType} text={text} font={font} imageUrl={imageUrl}
                        handleTextChange={handleTextChange} handleFontChange={handleFontChange} handleImageUrlChange={handleImageUrlChange}/>

                </Modal.Body>
                <Modal.Footer>
                    <Button variant='danger' onClick={() => {props.deleteElt(props.elt.eltID)}}>Delete Element</Button>
                    <Button variant='success' onClick={update}>Update Element</Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

function DependentInput(props) {

    if(props.type === 'text') {
        return (
            <div>
                <Form.Group controlId="form-text">
                    <Form.Label>Text</Form.Label>
                    <Form.Control required value={props.text} onChange={props.handleTextChange}/>
                </Form.Group>

                <Form.Group controlId="form-font">
                    <Form.Label>Font</Form.Label>
                    <Form.Control as="select" value={props.font} onChange={props.handleFontChange}>
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

export default EditElement;
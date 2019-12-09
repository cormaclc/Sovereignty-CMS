import React from 'react'
import {Modal, Form, Button} from 'react-bootstrap'
import uuidv1 from 'uuid/v1'
import {baseUrl} from '../api-js/api-interaction'

const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
});

function AddElement(props) {
    const [eltType, setEltType] = React.useState('Text')
    const [height, setHeight] = React.useState(0)
    const [width, setWidth] = React.useState(0)
    const [text, setText] = React.useState('')
    const [font, setFont] = React.useState('Times New Roman')
    const [image, setImage] = React.useState('')
    const [imageUrl, setImageUrl] = React.useState('')
    const [page, setPage] = React.useState('Front Page')

    const [enableButtons, setEnableButtons] = React.useState(true)

    const handleEltTypeChange = (e) => { setEltType(e.target.value) }
    const handleHeightChange = (e) => { setHeight(e.target.value) }
    const handleWidthChange = (e) => { setWidth(e.target.value) }
    const handleTextChange = (e) => { setText(e.target.value) }
    const handleFontChange = (e) => { setFont(e.target.value) }
    const handleImageChange = (e) => { setImage(e.target.files[0]); setImageUrl('') }
    const handleImageUrlChange = (e) => { setImageUrl(e.target.value) }

    const handlePageChange = (e) => { setPage(e.target.value) }

    const add = () => {

        setEnableButtons(false);
        
        if(eltType === 'Image' && image === '' && imageUrl === '') {
            alert('Invalid image, try again.')
            return;
        }

        let pageID = ''
        if (page === 'Front Page') {
            pageID = props.card.frontPage.pageID
        } else if (page === 'Left Page') {
            pageID = props.card.leftPage.pageID
        } else {
            pageID = props.card.rightPage.pageID
        }
        
        let imgUrl = ''
        if(imageUrl === '' && eltType === 'Image') {
            toBase64(image).then(res => {
                // console.log(res)
                let jsonImg = JSON.stringify({
                    imageName: image.name,
                    image64: res.substring(23, res.length),
                    imageID: uuidv1().substring(0, 19)
                })

                var xhr = new XMLHttpRequest();
                xhr.open('POST', baseUrl+'/uploadImage', true)
                xhr.send(jsonImg);
                    
                xhr.onloadend = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        console.log(xhr.responseText);
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
                            'imageURL': JSON.parse(xhr.response).imageURL,
                            'pageID': pageID,
                        }
                                
                        setPage('Front Page')
                        setEnableButtons(false);
                        setEltType('Text')
                        setImage('')
                        if(JSON.parse(xhr.response).code === 500) {
                            alert('Error uploading image')
                            props.addElement(false, page)
                        } else {
                            props.addElement(eltInfo, page)
                        }
                        setEnableButtons(true);
                    } else {
                        console.log('error');
                    }
                };
            })
        } else {
            imgUrl = imageUrl
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
                'imageURL': imgUrl,
                'pageID': pageID,
            }
    
            console.log(imgUrl)
    
            setPage('Front Page')
            setEnableButtons(false);
            setEltType('Text')
            props.addElement(eltInfo, page)
            setEnableButtons(true);
        }
        
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

                        <ElementConditional eltType={eltType} handleTextChange={handleTextChange} handleFontChange={handleFontChange} handleImageChange={handleImageChange} 
                            handleImageUrlChange={handleImageUrlChange} image={image}/>

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
                    <Button variant='danger' onClick={props.handleClose} disabled={!enableButtons}>Cancel</Button>
                    <Button variant='success' onClick={add} disabled={!enableButtons}>Add Element</Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

function ElementConditional(props) {

    const [imageSelection, setImageSelection] = React.useState(0)

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
            <div className='mb-3'>

                <Form.Group controlId='upload-type'>
                    <Form.Check inline label='Choose Existing Image' name='upload-type' type='switch' onChange={() => {imageSelection === 0 ? setImageSelection(1) : setImageSelection(0)}}/>
                </Form.Group>
                
                <ImageSelection imageSelection={imageSelection} image={props.image} handleImageChange={props.handleImageChange} handleImageUrlChange={props.handleImageUrlChange}/>
                
            </div>
        )
    }
}

function ImageSelection(props) {

    const [images, setImages] = React.useState([])

    React.useEffect(() => {
        let isSubscribed = true

        async function fetchData() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", baseUrl+'/allImages', true);
            xhr.send();
        
            // This will process results and update HTML as appropriate. 
            xhr.onloadend = function () {
                if (xhr.readyState === XMLHttpRequest.DONE && isSubscribed) {
                    let allImages = JSON.parse(xhr.responseText).images
                    setImages(allImages)
                } else if (!isSubscribed){
                    console.log('loading...')
                } else {
                    console.log('error')  
                }
            };
        }

        fetchData();
        return () => (isSubscribed = false)
    }, [images]);

    if(props.imageSelection === 0) {
        return(
            <div className="input-group">
                <div className="input-group-prepend">
                    <span className="input-group-text" id="inputGroupFileAddon01">
                    Upload Image
                    </span>
                </div>
                <div className="custom-file">
                    <input
                    type="file"
                    className="custom-file-input"
                    id="inputGroupFile01"
                    aria-describedby="inputGroupFileAddon01"
                    onChange={props.handleImageChange}
                    
                    />
                    <label className="custom-file-label" htmlFor="inputGroupFile01">
                        {props.image.name}
                    </label>
                </div>
            </div>
        )
    } else {
        return (
            <div>
                <Form.Group controlId="form-elt-type">
                    <Form.Label>Choose Image</Form.Label>
                    <Form.Control as="select" onChange={props.handleImageUrlChange}>
                        <option value=''>Select An Image</option>
                        {images.map(img => {
                            return(<option value={img.imageURL} key={img.imageURL}>{img.imageName}</option>)
                        })}
                    </Form.Control>
                </Form.Group>
            </div>
        )
    }
}

export default AddElement
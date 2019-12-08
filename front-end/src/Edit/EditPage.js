import React from 'react';
import styled from 'styled-components';
import Draggable from 'react-draggable'
import './EditPage.css'

const PageWrapper = styled.div`
    background-color: white
    width: ${props => props.landscape ? '900px' : "600px"};
    height: ${props => props.landscape ? '600px' : '900px'};
    margin-top: 1rem;
    margin-bottom: 1rem;
    color: black;
    position: relative;
`

const Element = styled.div`
    width: ${props => props.width};
    height: ${props => props.height};
    border: black solid 1px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: white;
    font-family: ${props => `"${props.font}"`};
    box-shadow: ${props => props.border ? '3px 3px 5px grey' : 'none'}

    :hover {
        cursor: pointer;
    }
`

const ImageElement = styled.img`
    width: ${props => props.width};
    height: ${props => props.height};
    border: black solid 1px;
    box-shadow: ${props => props.border ? '3px 3px 5px grey' : 'none'}

    :hover {
        cursor: pointer;
    }
`

function EditPage(props) {

    const [changedEl, setChangedEl] = React.useState({x: 0, y: 0})

    React.useEffect(() => {
        // Test Image Element
        // props.page.listVisualElements.push({
        //     eltID: "img_elt",
        //     eltType: "image",
        //     font: "font",
        //     height: 200,
        //     imageURL: "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
        //     pageID: "default_back",
        //     text: "Made by Sovereignty CMS",
        //     updated: 0,
        //     width: 200,
        //     xPosition: 100,
        //     yPosition: 300
        // })

    }, [props.page, props.update])

    const handleDrag = (e, ui) => {
        const {x, y} = changedEl;
        setChangedEl({
            x: x + ui.deltaX,
            y: y + ui.deltaY,
        });
    }

    const handleStop = (elID) => {
        props.updatePosition(elID, changedEl, props.pageName)
        props.handleElementSelect(elID, props.pageName)
        setChangedEl({x: 0, y: 0})
    }


    return (
        <>
            <PageWrapper landscape = {props.landscape === 'Landscape'}>
                {props.page.listVisualElements.map((el) => {
                    return (<ElementComponent key={el.eltID} el={el} selectedID={props.selectedID} page={props.page} handleStop={handleStop} handleDrag={handleDrag}/>)            
                })}
            </PageWrapper>
        </>
    )   
}

function ElementComponent(props) {
    if (props.el.eltType === 'text') {
        if (props.el.updated === 2) {
            return (<div key={props.el.eltID}></div>) 
        } else if (props.page.isModifiable) {
            return(
                <Draggable onStop={() => props.handleStop(props.el.eltID)} onDrag={props.handleDrag} defaultPosition={{x:props.el.xPosition, y:props.el.yPosition}} bounds={'parent'} key={props.el.eltID}>
                    <Element border={props.selectedID === props.el.eltID} font={props.el.font} width={`${props.el.width}px`} height={`${props.el.height}px`}>{props.el.text}</Element>
                </Draggable>
            )
        } else {
            return(
                <Draggable onStart={() => false} defaultPosition={{x:props.el.xPosition, y:props.el.yPosition}} bounds={'parent'} key={props.el.eltID}>
                    <Element font={props.el.font} width={`${props.el.width}px`} height={`${props.el.height}px`}>{props.el.text}</Element>
                </Draggable>
            )
        }
    } else {
        if (props.el.updated === 2) {
            return (<div key={props.el.eltID}></div>) 
        } else {
            return (
                <Draggable onStop={() => props.handleStop(props.el.eltID)} onDrag={props.handleDrag} defaultPosition={{x:props.el.xPosition, y:props.el.yPosition}} bounds={'parent'} key={props.el.eltID}>
                    <ImageElement border={props.selectedID === props.el.eltID} src={props.el.imageURL} height={props.el.height === 0 ? 'none' : `${props.el.height}px`} width={props.el.width === 0 ? 'none' : `${props.el.width}px`} draggable="false"></ImageElement>
                </Draggable>
            )
        }
        
    }
}

export default EditPage 
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

function EditPage(props) {

    // const [elements, setElements] = React.useState([])
    const [changedEl, setChangedEl] = React.useState({x: 0, y: 0})

    React.useEffect(() => {
        //setElements(props.page.elements)
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
                    if (el.updated === 2) {
                        return (<div key={el.eltID}></div>) 
                    } else if (props.page.isModifiable) {
                        return(
                            <Draggable onStop={() => handleStop(el.eltID)} onDrag={handleDrag} defaultPosition={{x:el.xPosition, y:el.yPosition}} bounds={'parent'} key={el.eltID}>
                                <Element border={props.selectedID === el.eltID} font={el.font} width={`${el.width}px`} height={`${el.height}px`}>{el.text}</Element>
                            </Draggable>
                        )
                    } else {
                        return(
                            <Draggable onStart={() => false} defaultPosition={{x:el.xPosition, y:el.yPosition}} bounds={'parent'} key={el.eltID}>
                                <Element font={el.font} width={`${el.width}px`} height={`${el.height}px`}>{el.text}</Element>
                            </Draggable>
                        )
                    }
                    
                })}
            </PageWrapper>
        </>
    )   
}

export default EditPage 
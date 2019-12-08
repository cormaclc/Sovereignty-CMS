import React from 'react';
import styled from 'styled-components';
import Draggable from 'react-draggable'

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
`

const ImageElement = styled.img`
    width: ${props => props.width};
    height: ${props => props.height};
    border: black solid 1px;
    box-shadow: ${props => props.border ? '3px 3px 5px grey' : 'none'}
`

function ViewPage(props) {
    React.useEffect(() => {
    }, [props.page])

    return (
        <>
            <PageWrapper landscape = {props.landscape === 'Landscape'}>
                {props.page.listVisualElements.map((el) => {
                    return (<ElementComponent key={el.eltID} el={el} page={props.page}/>)            
                })}
            </PageWrapper>
        </>
    )   
}

function ElementComponent(props) {
    if (props.el.eltType === 'text') {
        return(
            <Draggable onStart={() => false} defaultPosition={{x:props.el.xPosition, y:props.el.yPosition}} bounds={'parent'} key={props.el.eltID}>
                <Element border={false} font={props.el.font} width={`${props.el.width}px`} height={`${props.el.height}px`}>{props.el.text}</Element>
            </Draggable>
        )
    } else {
        return (
            <Draggable onStart={() => false} defaultPosition={{x:props.el.xPosition, y:props.el.yPosition}} bounds={'parent'} key={props.el.eltID}>
                <ImageElement border={false} src={props.el.imageURL} height={props.el.height === 0 ? 'none' : `${props.el.height}px`} width={props.el.width === 0 ? 'none' : `${props.el.width}px`} draggable="false"></ImageElement>
            </Draggable>
        )
    }
}

export default ViewPage 
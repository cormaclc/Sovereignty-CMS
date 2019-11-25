import React from 'react';
import './Home.css';
import ViewCards from './View/ViewCards';

function Home() {
    return (
        <div className="App">
            <header className="App-header">
                <p>
                Sovereignty Card Maker System
                </p>
                <ViewCards></ViewCards>
            </header>
        </div>
    )
}

export default Home
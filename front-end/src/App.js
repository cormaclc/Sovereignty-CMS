import React from 'react';
import './App.css';
import ViewCards from './View/ViewCards';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          Sovereignty Card Maker System
        </p>
        <ViewCards></ViewCards>
      </header>
    </div>
  );
}

export default App;

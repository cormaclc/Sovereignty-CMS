import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch,
} from "react-router-dom";
import Home from './Home'
import EditCard from './Edit/EditCard'

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Home}/>
        <Route path="/editCard/:cardID" children={<EditCard/>}/>
      </Switch>
    </Router>
  );
}

export default App;

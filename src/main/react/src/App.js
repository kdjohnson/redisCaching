import React, { Component } from 'react';
import './App.css';
import logo from './logo.svg'
import $ from 'jquery';

class App extends Component {

  /* global token */
  /* global ip */

  componentDidMount() {
    $.ajax({
      url: 'http://' + ip + '/status',
      type: 'POST',
      data: { "token": token },
      success: function(data) {
        console.log(data)
      },
      error: function (xhr, status, err) {
        console.error(status, err.toString());
      }
    });
  }

  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React</h2>
        </div>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
      </div>
    );
  }
}

export default App;

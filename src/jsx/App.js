/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-23 19:55:31
        Filename: src/jsx/App.js
        Description: Created by SpringHack using vim automatically.
**/
import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { AppRegistry, View, Text } from 'react-native';

// import style from '../style';

@observer
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
      <View>
        <Text>
          React Component
        </Text>
      </View>
    );
  }
}

AppRegistry.registerComponent('DTools', () => App);

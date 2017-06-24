/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-24 21:49:04
        Filename: src/jsx/App.js
        Description: Created by SpringHack using vim automatically.
**/
import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { NativeModules, AppRegistry, View, Text } from 'react-native';

// import style from '../style';

@observer
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  async componentDidMount() {
    const { DeviceOwnerManager } = NativeModules;
    console.log(await DeviceOwnerManager.getPackageList());
  }
  render() {
    return (
      <View style={{
        width: 1000,
        height: 1000
      }}>
        <Text>
          React Component
        </Text>
      </View>
    );
  }
}

AppRegistry.registerComponent('DTools', () => App);

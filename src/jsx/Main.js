/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-25 16:11:28
        Filename: Main.js
        Description: Created by SpringHack using vim automatically.
**/
import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Button, Text, View } from 'react-native';

@observer
class Main extends Component {
  static navigationOptions = {
    title: 'Main'
  }
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
      <View>
        <Text>
          {'//TODO 授权检测'}
        </Text>
        <Button onPress={() => this.props.navigation.navigate('AppList', {})} title='App列表' />
      </View>
    );
  }
}

export default Main;

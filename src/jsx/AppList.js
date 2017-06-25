/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-25 15:58:12
        Filename: AppList.js
        Description: Created by SpringHack using vim automatically.
**/
import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { NativeModules, ScrollView, Image, View, Text } from 'react-native';
import { List } from 'antd-mobile';

import style from '../style';

@observer
class AppList extends Component {
  static navigationOptions = {
    title: 'App List'
  }
  constructor(props) {
    super(props);
    this.state = {
      packageList: []
    };
  }
  componentDidMount() {
    this.updatePackageList();
  }
  async updatePackageList() {
    const { DeviceOwnerManager } = NativeModules;
    this.setState({
      packageList: await DeviceOwnerManager.getPackageList()
    });
  }
  render() {
    return (
      <View style={style.outer}>
        <ScrollView>
          <List renderHeader={<Text>User</Text>}>
            {
              this.state.packageList
              .filter(p => !p.systemApp)
              .sort((x, y) => (x.appName.localeCompare(y.appName)))
              .map(pack => (
                <List.Item
                  multipleLine
                  key={pack.packageName}
                  onClick={() => {}}
                  thumb={<Image
                    source={{ uri: `data:image/png;base64,${pack.appIcon}` }}
                    style={{
                      width: 40,
                      height: 40
                    }} />}>
                  {pack.appName}
                  <List.Item.Brief>{pack.packageName}</List.Item.Brief>
                </List.Item>
              ))
            }
          </List>
          <List renderHeader={<Text>System</Text>}>
            {
              this.state.packageList
              .filter(p => p.systemApp)
              .sort((x, y) => (x.appName.localeCompare(y.appName)))
              .map(pack => (
                <List.Item
                  multipleLine
                  key={pack.packageName}
                  onClick={() => {}}
                  thumb={<Image
                    source={{ uri: `data:image/png;base64,${pack.appIcon}` }}
                    style={{
                      width: 40,
                      height: 40
                    }} />}>
                  {pack.appName}
                  <List.Item.Brief>{pack.packageName}</List.Item.Brief>
                </List.Item>
              ))
            }
          </List>
        </ScrollView>
      </View>
    );
  }
}

export default AppList;

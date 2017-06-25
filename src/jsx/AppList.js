/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-25 22:38:23
        Filename: AppList.js
        Description: Created by SpringHack using vim automatically.
**/
import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { NativeModules, ScrollView, View, Text } from 'react-native';
import { RefreshControl, ActivityIndicator, List } from 'antd-mobile';

import AppIcon from './AppIcon';

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
    const packageList = await DeviceOwnerManager.getPackageList();
    packageList.sort((x, y) => {
      if (x.systemApp ^ y.systemApp) {
        return (x.systemApp ? 1 : -1);
      }
      return x.appName.localeCompare(y.appName);
    });
    this.setState({ packageList });
  }
  render() {
    let i = 0;
    const userApp = [];
    const systemApp = [];
    while (i < this.state.packageList.length && !this.state.packageList[i].systemApp) {
      userApp.push(
        <List.Item
          multipleLine
          key={this.state.packageList[i].packageName}
          onClick={() => {}}
          thumb={<AppIcon packageName={this.state.packageList[i].packageName} />}>
          {this.state.packageList[i].appName}
          <List.Item.Brief>{this.state.packageList[i].packageName}</List.Item.Brief>
        </List.Item>
      );
      ++i;
    }
    while (i < this.state.packageList.length && this.state.packageList[i].systemApp) {
      systemApp.push(
        <List.Item
          multipleLine
          key={this.state.packageList[i].packageName}
          onClick={() => {}}
          thumb={<AppIcon packageName={this.state.packageList[i].packageName} />}>
          {this.state.packageList[i].appName}
          <List.Item.Brief>{this.state.packageList[i].packageName}</List.Item.Brief>
        </List.Item>
      );
      ++i;
    }
    return (
      <View style={style.outer}>
        {
          this.state.packageList.length ? (
            <ScrollView
              refreshControl={<RefreshControl
                refreshing={false}
                onRefresh={() => this.updatePackageList()} />}>
              <List renderHeader={<Text>User</Text>}>
                {userApp}
              </List>
              <List renderHeader={<Text>System</Text>}>
                {systemApp}
              </List>
            </ScrollView>
          ) : (<ActivityIndicator toast text='Loading...' />)
        }
      </View>
    );
  }
}

export default AppList;

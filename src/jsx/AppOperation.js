/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-26 10:02:21
        Filename: AppOperation.js
        Description: Created by SpringHack using vim automatically.
**/
import React, { Component } from 'react';
import { NativeModules } from 'react-native';
import { observer } from 'mobx-react';
import { Modal, Switch } from 'antd-mobile';

@observer
class AppOperation extends Component {
  constructor(props) {
    super(props);
    const { packageInfo } = this.props;
    this.state = {
      visable: false,
      enabled: packageInfo.enabled
    };
  }
  async handleAppHideStateChange() {
    const { DeviceOwnerManager } = NativeModules;
    const { packageInfo } = this.props;
    try {
      const result = await DeviceOwnerManager.setPackageHideState(
        packageInfo.packageName,
        !this.state.enabled
      );
      if (result) {
        this.setState({ enabled: !this.state.enabled });
      } else {
        throw String('Set hide state failed!');
      }
    } catch (e) {
      Modal.alert('Error', 'Failed to set hidden state !');
    }
  }
  render() {
    return (
      <Switch
        onChange={() => this.handleAppHideStateChange()}
        checked={this.state.enabled} />
    );
  }
}

export default AppOperation;

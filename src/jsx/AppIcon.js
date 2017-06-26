/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-26 09:32:11
        Filename: AppIcon.js
        Description: Created by SpringHack using vim automatically.
**/
import React, { Component } from 'react';
import { NativeModules, Image } from 'react-native';

import style from '../style';

export default class AppIcon extends Component {
  constructor(props) {
    super(props);
    this.state = {
      uri: 'ic_launcher'
    };
  }
  componentDidMount() {
    // Make this happend next eventLoop
    setTimeout(() => {
      this.updateIcon();
    }, 0);
  }
  async updateIcon() {
    const { DeviceOwnerManager } = NativeModules;
    const { packageName } = this.props;
    const base64Icon = await DeviceOwnerManager.getAppIcon(packageName);
    this.setState({
      uri: `data:image/png;base64,${base64Icon}`
    });
  }
  render() {
    return <Image source={{ uri: this.state.uri }} style={style.appIcon} />;
  }
}

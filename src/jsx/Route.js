/**
        Author: SpringHack - springhack@live.cn
        Last modified: 2017-06-25 16:01:35
        Filename: Route.js
        Description: Created by SpringHack using vim automatically.
**/
import React from 'react';
import { StackNavigator } from 'react-navigation';

import Main from './Main';
import AppList from './AppList';

const Navigator = StackNavigator({
  Main: { screen: Main },
  AppList: { screen: AppList }
});

export default function Route(props) {
  return (<Navigator initialRouteName='Main' />);
}

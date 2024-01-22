/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {SafeAreaView, StyleSheet, Text} from 'react-native';

function App(): React.JSX.Element {
  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.text}>VW Marketing Cloud - Salesforce</Text>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#416aa0',
  },
  text: {
    fontSize: 20,
    color: '#a3bfc0',
    fontWeight: 'bold',
  },
});

export default App;

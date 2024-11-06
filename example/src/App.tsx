import { useState, useEffect } from 'react';
import { StyleSheet, View, Text } from 'react-native';
import { multiply, noSslApi } from 'no-ssl-api';

export default function App() {
  const [result, setResult] = useState<String>();

  useEffect(() => {
    //multiply(3, 7).then(setResult);
    noSslApi("https://uat.qaema.com/sdk/ob/accounts-information/v1/accounts-links?AccountsLinkId=&PSUId=255cc&FinancialInstitutionId=&FinancialInstitutionName=SAMA&Status=Active&page=1").then(setResult)
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});

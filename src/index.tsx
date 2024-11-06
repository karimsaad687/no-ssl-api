import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'no-ssl-api' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const NoSslApi = NativeModules.NoSslApi
  ? NativeModules.NoSslApi
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return NoSslApi.multiply(a, b);
}

export function noSslApi(url: string): Promise<string> {
  return NoSslApi.noSslApi(url);
}

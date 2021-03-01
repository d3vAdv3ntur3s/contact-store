# SSL Certificate Generation 

# Keystore & mkcert
- PKCS12 is the industry standard, however, java has a proprietary format specific for Java called JKS, we will stick with PKC12 for our keystore.
- Managing the generation of certs and trust store system wide, `mkcert` is an incredibly useful tool

## Install mkcert
```sh
brew install mkcert
```


## Generate cert  

### Option 1
keystore command generate :
```sh
keytool -genkeypair -alias localdev -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -storepass password
```

#### Command Breakdown
-  genkeypair: generates a key pair;
-  alias: the alias name for the item we are generating
-  keyalg: the cryptographic algorithm to generate the key pair
-  keysize: the size of the key. We have used 2048 bits (4096 for production)
-  storetype: the type of keystore
-  keystore: the name of the keystore
-  validity: validity number of days e.g. 10years
-  storepass: a password for the keystore.

#### Command Output

1. Will ask a series of basic questions to fill in the cert info, as this is for local development, we are not concerned, enter or skip.
2. after submitting cert data, there will be a *prompt you for a key password, this is different to your keystore password*, however, for the sake of ease of local development, *use the same as above*.
3. After enterting the key password, you will now have a keystore containing a new Cert. (see ##import cert)



### Option 2 (convenient)
mkcert:
```sh
mkcert localhost 127.0.0.1
```

Output:
```sh
Note: the local CA is not installed in the system trust store.
Note: the local CA is not installed in the Firefox trust store.
Note: the local CA is not installed in the Java trust store.
Run "mkcert -install" for certificates to be trusted automatically ⚠️

Created a new certificate valid for the following names 📜
 - "localhost"
 - "127.0.0.1"

The certificate is at "./localhost+1.pem" and the key at "./localhost+1-key.pem" ✅

It will expire on 20 May 2023 🗓
```

Install into trust store to make cert valid and recognised:
```sh
mkcert -install
```

Enter in your pw: password and the output should look as follows:
```sh
The local CA is now installed in the system trust store! ⚡️
The local CA is now installed in the Firefox trust store (requires browser restart)! 🦊
The local CA is now installed in Java's trust store! ☕️
```

convert the resulting .pem and key.pem files to a .p12 file using OpenSSL, enter a pw: password for simplicity sake in development
```sh
openssl pkcs12 -export -in localhost+1.pem -inkey localhost+1-key.pem -out keystore.p12 -name localdev
```
This will output the `keystore.p12` this will need to be in the location `src/main/resources`




## Import Cert into existing keystore
If we had a keystore already for other use, we would generate and import e.g. using [Let's Encrpt Authority](https://letsencrypt.org/docs/certificates-for-localhost/)

```sh
keytool -import -alias tomcat -file myCertificate.crt -keystore keystore.p12 -storepass password
```

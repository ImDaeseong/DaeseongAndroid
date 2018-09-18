package com.im.daeseong.fingerprintmanager_test;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintManagerHandler  {

    private static final String TAG = FingerprintManagerHandler.class.getSimpleName();

    private Context context;
    private FingerprintManager fingerprintManager;
    private FingerprintManager.CryptoObject cryptoObject;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Cipher cipher;

    private static final String skeyName = "password";
    private static final String skeyStore = "keyStore";

    public FingerprintManagerHandler(Context context) {
        this.context = context;
        fingerprintManager = (FingerprintManager)context.getSystemService(Context.FINGERPRINT_SERVICE);
    }

    //fingerprintManager callback 초기화
    public void FingerAuthinit(FingerprintManager.AuthenticationCallback callback) {

        initkeyStore();

        try {
            CancellationSignal signal = new CancellationSignal();
            fingerprintManager.authenticate(cryptoObject, signal, 0, callback, null);
        } catch (SecurityException e) {
            Log.e(TAG, "SecurityException:" + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception:" + e.getMessage());
        }
    }

    public void FingerAuthClear() {
        try {
            if(keyStore != null) {
                keyStore.deleteEntry(skeyStore);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception:" + e.getMessage());
        }
    }

    private void initkeyStore() {

        cryptoObject = new FingerprintManager.CryptoObject(cipher);

        try {
            keyStore = KeyStore.getInstance(skeyStore);
        }catch (KeyStoreException e){
            Log.e(TAG, "KeyStoreException:" + e.getMessage());
        }catch (Exception e){
            Log.e(TAG, "Exception:" + e.getMessage());
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, skeyStore);
        }catch (NoSuchAlgorithmException e){
            Log.e(TAG, "NoSuchAlgorithmException:" + e.getMessage());
        }catch (Exception e){
            Log.e(TAG, "Exception:" + e.getMessage());
        }

        try {
            keyGenerator.init(new KeyGenParameterSpec.Builder(skeyName, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        }catch (Exception e){
            Log.e(TAG, "Exception:" + e.getMessage());
        }

        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            Log.e(TAG, "NoSuchAlgorithmException|NoSuchPaddingException:" + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception:" + e.getMessage());
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(skeyName, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (KeyPermanentlyInvalidatedException e) {
            Log.e(TAG, "KeyPermanentlyInvalidatedException:" + e.getMessage());
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            Log.e(TAG, "KeyStoreException:" + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception:" + e.getMessage());
        }
    }

    //하드웨어 지원 가능
    public boolean isHardwareDetected() {
        try {
            return fingerprintManager.isHardwareDetected();
        }catch (Exception e){
            Log.e(TAG, "Exception:" + e.getMessage());
        }
        return false;
    }

    //기기에 지문 저장 여부
    public boolean hasEnrolledFingerprints() {
        try {
            return fingerprintManager.hasEnrolledFingerprints();
        }catch (Exception e){
            Log.e(TAG, "Exception:" + e.getMessage());
        }
        return false;
    }
}

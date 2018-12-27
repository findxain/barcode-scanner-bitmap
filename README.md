Cradit goes to  https://github.com/dm77/barcodescanner 

zbar removed and while scanning qrcode it give yo callback with an image Bitmap of scanned qrcode

Introduction
============

Android library projects that provides easy to use and extensible Barcode Scanner views based on ZXing.

=====

Installation
------------

Add the following dependency to your build.gradle file.

`implementation 'com.github.findxain.barcode-scanner-bitmap:zxing:latest_version'`

[![](https://jitpack.io/v/findxain/barcode-scanner-bitmap.svg)](https://jitpack.io/#findxain/barcode-scanner-bitmap)

Simple Usage
------------

1.) Add camera permission to your AndroidManifest.xml file:

```xml
<uses-permission android:name="android.permission.CAMERA" />
```

2.) A very basic activity would look like this:

```java
public class SimpleScannerActivity extends Activity implements QRCodeDialog.QrCodeScanResult {
    QRCodeDialog qrCodeDialog;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        qrCodeDialog = new QRCodeDialog(getActivity());// Programmatically initialize the scanner view
        qrCodeDialog.setResultListner(this);   
        qrCodeDialog.show();
    }
   @Override
    public void onQRCodeResult(String text, Bitmap bitmap) {
        //user theqrcode text and image
    }
    
}

```

For any issue kindly report on https://github.com/dm77/barcodescanner 

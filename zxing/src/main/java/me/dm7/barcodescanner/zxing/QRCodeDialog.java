package com.innexiv.hhu.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.Result;
import com.innexiv.hhu.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

//import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class QRCodeDialog extends Dialog implements ZXingScannerView.ResultHandler {


    @BindView(R.id.textViewText)
    TextView textViewText;
    @BindView(R.id.buttonOK)
    Button buttonOK;
    @BindView(R.id.buttonRescan)
    Button buttonRescan;
    //    @BindView(R.id.frameLayout)
//    FrameLayout frameLayout;
    private QrCodeScanResult qrCodeScanResult;
    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.qrdecoderView)
    ZXingScannerView qrdecoderView;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.imageViewPreview)
    ImageView imageViewPreview;
    private Bitmap bitmap;


    public QRCodeDialog(@NonNull Activity context) {
        super(context);
        init(getContext());


    }


    private void init(@NonNull Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.qrcode_dialog, null, false);
        setContentView(view);
        ButterKnife.bind(this);


    }

    @SuppressLint("MissingPermission")
    @Override
    public void show() {
        super.show();
        if (qrdecoderView != null) {
            qrdecoderView.setResultHandler(this);
            qrdecoderView.startCamera();
        }

    }

    @Override
    public void dismiss() {
        super.dismiss();


        if (qrdecoderView != null) {
            qrdecoderView.stopCamera();
        }
    }


    public QRCodeDialog setTextViewTitle(int titleId) {
        super.setTitle(titleId);
        String title = getContext().getResources().getString(titleId);
        setTextViewTitle(title);
        return this;
    }


    public QRCodeDialog setTextViewTitle(@Nullable CharSequence title) {
        this.textViewTitle.setText(title);
        return this;

    }

    public void setResultListner(QrCodeScanResult resultListner) {
        this.qrCodeScanResult = resultListner;
    }


    @OnClick(R.id.buttonOK)
    public void onButtonOKClicked() {
        qrCodeScanResult.onQRCodeResult(textViewText.getText().toString(),bitmap);
    }

    @OnClick(R.id.buttonRescan)
    public void onButtonRescanClicked() {

        constraintLayout.setVisibility(View.GONE);
        qrdecoderView.resumeCameraPreview(this);

    }

    @Override
    public void handleResult(Result result, Bitmap bitmap) {


        if (qrdecoderView != null) {
            textViewText.setText(result.getText());
            constraintLayout.setVisibility(View.VISIBLE);

        }
        this.bitmap = bitmap;

    }

    public interface QrCodeScanResult {
        void onQRCodeResult(String text, Bitmap bitmap);
    }

}

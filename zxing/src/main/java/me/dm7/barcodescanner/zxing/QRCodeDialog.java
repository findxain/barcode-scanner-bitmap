package me.dm7.barcodescanner.zxing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.Result;


public class QRCodeDialog extends Dialog implements ZXingScannerView.ResultHandler {


    TextView textViewText;
    Button buttonOK;
    Button buttonRescan;

    private QrCodeScanResult qrCodeScanResult;
    TextView textViewTitle;
    ZXingScannerView qrdecoderView;

    RelativeLayout constraintLayout;

//    ImageView imageViewPreview;
    private Bitmap bitmap;


    public QRCodeDialog(@NonNull Activity context) {
        super(context);
        init(getContext());


    }


    private void init(@NonNull Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.qrcode_layout, null, false);
        setContentView(view);
        textViewText = (TextView) view.findViewById(R.id.textViewText);
        buttonOK= (Button) view.findViewById(R.id.buttonOK);
        buttonRescan= (Button) view.findViewById(R.id.buttonRescan);
        constraintLayout= (RelativeLayout) view.findViewById(R.id.constraintLayout);

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


    public void onButtonOKClicked() {
        qrCodeScanResult.onQRCodeResult(textViewText.getText().toString(), bitmap);
    }

    public void onButtonRescanClicked() {

        constraintLayout.setVisibility(View.GONE);
        qrdecoderView.resumeCameraPreview(this);

    }

    @Override
    public void handleResult(Result result,Bitmap bitmap) {


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

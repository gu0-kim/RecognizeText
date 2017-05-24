package com.gu.recognizetext;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.LanguageCodes;
import com.microsoft.projectoxford.vision.contract.Line;
import com.microsoft.projectoxford.vision.contract.OCR;
import com.microsoft.projectoxford.vision.contract.Region;
import com.microsoft.projectoxford.vision.contract.Word;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * @author gu
 * @version 1.0
 * @date 2017/5/24
 */

public class PostDataTask extends AsyncTask<String, String, String> {
    private Exception e;
    private EditText mEditText;
    private String filePath;
    private Context context;
    private FloatingActionButton btn1, btn2, btn3;
    private VisionServiceClient client;

    public PostDataTask(Context context, String filePath, EditText resultText, FloatingActionButton btn1, FloatingActionButton btn2, FloatingActionButton btn3) {
        mEditText = resultText;
        this.filePath = filePath;
        this.context = context;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        client = new VisionServiceRestClient(context.getString(R.string.subscription_key));
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            return process(filePath);
        } catch (Exception e) {
            this.e = e;    // Store error
        }
        return null;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);
        // Display based on error existence

        if (e != null) {
            mEditText.setText("Error: " + e.getMessage());
            this.e = null;
        } else if (data != null) {
            Gson gson = new Gson();
            OCR r = gson.fromJson(data, OCR.class);

            String result = "";
            for (Region reg : r.regions) {
                for (Line line : reg.lines) {
                    for (Word word : line.words) {
                        result += word.text + " ";
                    }
                    result += "\n";
                }
                result += "\n\n";
            }
            mEditText.setText(result);
        }
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
    }

    private String process(String filePath) throws VisionServiceException, IOException {

        Bitmap bitmap = ImageHelper.loadSizeLimitedBitmapFromUri(ImageHelper.path2Uri(filePath), context.getContentResolver());
        if (bitmap != null) {
            Gson gson = new Gson();
            // Put the image into an input stream for detection.
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());

            OCR ocr;
            ocr = client.recognizeText(inputStream, LanguageCodes.AutoDetect, true);

            String result = gson.toJson(ocr);
            if (!bitmap.isRecycled())
                bitmap.recycle();
            return result;
        }
        return null;
    }
}

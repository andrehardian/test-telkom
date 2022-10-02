package app.test.testtelkom.manager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import lombok.Setter;

public class DialogManager {
    private final Context context;
    public MutableLiveData<Boolean> showHideLoading = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    @Setter
    private DialogListener dialogListener;

    public DialogManager(Context context) {
        this.context = context;
        showHideLoading();
    }

    public void showPopup(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Dialog dialog = builder.setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    if (dialogListener != null)
                        dialogListener.okClick();
                }).create();
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    private void showHideLoading() {
        if (context instanceof AppCompatActivity)
            showHideLoading.observe(((AppCompatActivity) context), aBoolean -> {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(context);
                    progressDialog.setCancelable(false);
                }
                progressDialog.setMessage("loading...");
                if (!progressDialog.isShowing() && aBoolean) {
                    progressDialog.show();
                } else if (progressDialog.isShowing() && !aBoolean) {
                    progressDialog.dismiss();
                }
            });
    }

    public void showError(String error) {
        showHideLoading.postValue(false);
        showPopup(error);
    }


    public interface DialogListener {
        void okClick();
    }
}


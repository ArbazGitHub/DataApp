package com.example.dataapp.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import com.example.dataapp.R;

public class AppDialog {

    //App dialog with-one button
    public static void showAlertDialog(Context _context, String _title,
                                       String _message, String _positiveText,
                                       DialogInterface.OnClickListener _onPositiveClick) {
        AlertDialog dialog = new AlertDialog.Builder(_context).create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogAnimation;
        if (_title != null && _title.length() > 0) {
            dialog.setTitle(_title);
        } else {
            dialog.setTitle(_context.getString(R.string.app_name));
        }
        dialog.setMessage(_message);
        dialog.setButton(Dialog.BUTTON_POSITIVE, _positiveText,
                _onPositiveClick);
        dialog.setCancelable(false);
        dialog.show();
        Button bq = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        bq.setTextColor(Color.BLACK);
    }

    //App dialog with-two buttons
    public static void showAlertDialog(Context _context, String _title,
                                       String _message, String _positiveText, String _negativeText,
                                       DialogInterface.OnClickListener _onPositiveClick,
                                       DialogInterface.OnClickListener _onNegativeClick) {
        AlertDialog dialog = new AlertDialog.Builder(_context).create();
        if (_title != null && _title.length() > 0) {
            dialog.setTitle(_title);
        } else {
            dialog.setTitle(_context.getString(R.string.app_name));
        }
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogAnimation;
        dialog.setMessage(_message);
        dialog.setButton(Dialog.BUTTON_POSITIVE, _positiveText,
                _onPositiveClick);
        dialog.setButton(Dialog.BUTTON_NEGATIVE, _negativeText,
                _onNegativeClick);
        dialog.setCancelable(false);
        dialog.show();
        Button bPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button bNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        bPositive.setTextColor(Color.BLACK);
        bNegative.setTextColor(Color.BLACK);
    }

    //App dialog with-three buttons
    public static void showAlertDialog(Context _context, String _title,
                                       String _message, String _positiveText, String _negativeText, String _neutralText,
                                       DialogInterface.OnClickListener _onPositiveClick,
                                       DialogInterface.OnClickListener _onNegativeClick, Dialog.OnClickListener _onNeutralClick) {
        AlertDialog dialog = new AlertDialog.Builder(_context).create();
        if (_title != null && _title.length() > 0) {
            dialog.setTitle(_title);
        } else {
            dialog.setTitle(_context.getString(R.string.app_name));
        }
        dialog.getWindow().getAttributes().windowAnimations = R.style.AppDialogAnimation;
        dialog.setMessage(_message);
        dialog.setButton(Dialog.BUTTON_POSITIVE, _positiveText,
                _onPositiveClick);
        dialog.setButton(Dialog.BUTTON_NEGATIVE, _negativeText,
                _onNegativeClick);
        dialog.setButton(Dialog.BUTTON_NEUTRAL, _neutralText,
                _onNeutralClick);
        dialog.setCancelable(false);
        dialog.show();
        Button bPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button bNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button bNeutral = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        bPositive.setTextColor(Color.BLACK);
        bNegative.setTextColor(Color.BLACK);
        bNeutral.setTextColor(Color.BLACK);
    }


    //Application Exit Dialog
    public static void exitButtonDialog(final Context _context) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(_context);
        alertDialog.setTitle(R.string.app_name);
        alertDialog.setMessage(R.string.app_exit_message);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity) _context).finish();
                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();

    }


}



package com.example.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HighScoreDialog extends AppCompatDialogFragment {
    public EditText edittext;
    public HighScoreDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog builder = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.highscorenamedialog, null);

        builder.setView(view);
        builder.setTitle("New High Score!");
        builder.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edittext.getText().toString();
                        listener.setHighScoreName(name);

                    }
                });

        edittext = view.findViewById(R.id.highscorename);

        return builder;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (HighScoreDialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement HighScoreDialogListener.");
        }
    }

    public interface HighScoreDialogListener{
        void setHighScoreName(String name);
    }
}

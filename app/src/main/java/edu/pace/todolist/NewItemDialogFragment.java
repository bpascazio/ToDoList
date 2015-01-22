package edu.pace.todolist;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NewItemDialogFragment extends DialogFragment {

	static NewItemDialogFragment newInstance() {
		NewItemDialogFragment f = new NewItemDialogFragment();
		return f;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Dialog);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
		View v = inflater.inflate(R.layout.fragment_dialog, container, false);

        Button button = (Button)v.findViewById(R.id.button1);
        final EditText newTodoText = (EditText)v.findViewById(R.id.editText1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaceSQLiteHelper helper = new PaceSQLiteHelper(getActivity(),
                        "todo", null, 1);
                Log.d("database", "new todo item "+newTodoText.getText().toString());
                helper.addTodo(newTodoText.getText().toString());
                getDialog().dismiss();
            }
        });

		return v;
	}

}

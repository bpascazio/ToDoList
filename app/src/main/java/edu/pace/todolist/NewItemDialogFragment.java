package edu.pace.todolist;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewItemDialogFragment extends DialogFragment {

	static NewItemDialogFragment newInstance() {
		NewItemDialogFragment f = new NewItemDialogFragment();
		return f;
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Dialog);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
		View v = inflater.inflate(R.layout.fragment_dialog, container, false);
		return v;
	}

}

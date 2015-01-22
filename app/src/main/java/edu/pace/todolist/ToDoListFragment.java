package edu.pace.todolist;

import edu.pace.todolist.MainActivity.PlaceholderFragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ToDoListFragment extends ListFragment {

    public enum ToDoListType {
        OPEN, COMPLETED, DELETED, DATABASE
    }

    String[] todos_text1 = new String[]{"feed the cat", "pay parking ticket",
            "do laundry"};
    String[] todos_text2 = new String[]{"buy milk", "pay rent"};
    String[] todos_text3 = new String[]{"fill up car with gas",
            "mop apartment"};

    ToDoListType listType;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static ToDoListFragment newInstance(ToDoListType type) {
        ToDoListFragment fragment = new ToDoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.listType = type;
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        PaceSQLiteHelper helper = new PaceSQLiteHelper(getActivity(),
                "todo", null, 1);

        /////add this first/////
        ArrayList<String> databaseList = null;
        //////////////////////
        if (helper != null) {
            /////add this second /////
            databaseList = helper.getAllTodos();
            //////////////////////////
        } else {
            Log.d("database", "failed to get db helper");
        }

        String[] sarray = null;

        switch (listType) {
            case OPEN:
                sarray = todos_text1;
                break;
            case COMPLETED:
                sarray = todos_text2;
                break;
            case DELETED:
                sarray = todos_text3;
                break;
            case DATABASE:
                /////add this third /////
                sarray = databaseList.toArray(new String[databaseList.size()]);
                ////////////////////////
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), R.layout.todo_item, R.id.label,
                sarray);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}

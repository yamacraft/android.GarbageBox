package dev.yamacraft.garbagebox.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import dev.yamacraft.garbagebox.app.R;

/**
 * Created by wataru.yamada on 2014/05/07.
 */
public class IntentCheckFragment extends Fragment {

    private final static int REQUEST_CAMERA = 0;

    private final static String[] mIntentLists = {
            "camera",
            " - "
    };

    @InjectView(R.id.intentcheck_list)
    ListView mIntentcheckList;

    @InjectView(R.id.console_text)
    TextView mConsoleText;

    @OnItemClick(R.id.intentcheck_list)
    void onMenuItemClicked(int position) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public IntentCheckFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intentcheck, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_main_menu,mIntentLists);
        mIntentcheckList.setAdapter(arrayAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultCodeText = "OK";
        if(resultCode == Activity.RESULT_CANCELED){
            resultCodeText = "CANCEL";
        }else if(resultCode == Activity.RESULT_FIRST_USER){
            resultCodeText = "RESULT_FIRST_USER";
        }

        String dataText = " - ";
        if(requestCode == REQUEST_CAMERA){
            dataText = data.getExtras().toString();
        }

        String log = "requestCode:" + requestCode
                + " resultCode:" + resultCodeText
                + " data:" + dataText;
        mConsoleText.setText(log);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

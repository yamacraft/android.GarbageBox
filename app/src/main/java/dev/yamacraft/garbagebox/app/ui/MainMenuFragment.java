package dev.yamacraft.garbagebox.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import dev.yamacraft.garbagebox.app.R;

/**
 * Created by wataru.yamada on 2014/04/28.
 */
public class MainMenuFragment extends Fragment {

    private String[] mMenuLists;

    @InjectView(R.id.main_menu_list)
    ListView mMainMenuList;

    @OnItemClick(R.id.main_menu_list)
    void onMenuItemClicked(int position) {
        String menuName = mMenuLists[position];
        if (menuName.equals(getResources().getString(R.string.main_menu_scribe))) {
            Intent intent = new Intent(getActivity(), ScribeActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }else if(menuName.equals(getResources().getString(R.string.main_menu_navigation_drawer))){
            Intent intent = new Intent(getActivity(), NavigationDrawerActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }else if(menuName.equals(getResources().getString(R.string.main_menu_andengine))){
            Intent intent = new Intent(getActivity(), AndEngineActicity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }else if(menuName.equals(getResources().getString(R.string.main_menu_intent))){
            Intent intent = new Intent(getActivity(), IntentCheckActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }
    }

    public MainMenuFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMenuLists = getResources().getStringArray(R.array.main_menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_main_menu,mMenuLists);
        mMainMenuList.setAdapter(arrayAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
